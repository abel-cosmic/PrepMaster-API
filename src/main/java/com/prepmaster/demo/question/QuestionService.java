package com.prepmaster.demo.question;

import com.prepmaster.demo.bundle.Bundle;
import com.prepmaster.demo.bundle.BundleService;
import com.prepmaster.demo.exception.ApiRequestException;
import com.prepmaster.demo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor //LOMBOK SEE VIDEO
@Slf4j // so we can use log variable
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final BundleService bundleService;
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestion(Long id) {
        log.info("Getting course {}", id);
        Question question = questionRepository
                .findById(id)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Admin with ID " + id + " not found");
                            log.error("error question {} not found", id , notFoundException);
                            return notFoundException;
                        }
                );
        log.info("Got question {}", question.getId());
        return question;
    }

    public void createNewQuestion(QuestionRequestBody questionRequestBody) {
        Question question = questionRequestBody.getQuestion();
        log.info("creating question {}",question);
        extracted(questionRequestBody, question);
        questionRepository.save(question);
        log.info("Created course {} successfully", question.getId());
    }

    public void deleteQuestion(Long id) {
        log.info("Deleting course {}", id);
        if (!questionRepository.existsById(id)) {
            NotFoundException notFoundException = new NotFoundException("Course with ID " + id + " not found");
            log.error("error course {} not found could not delete a non existing tuple", id , notFoundException);
            return;
        }
        questionRepository.deleteById(id);
        log.info("Deleted course {} successfully", id);
    }

    public void updateQuestion(QuestionRequestBody questionRequestBody) {
        //TODO ADD THE CHOICES TO THE UPDATED OBJECT
        Question question = questionRequestBody.getQuestion();
        log.info("creating question {}",question);
        extracted(questionRequestBody, question);
        if (!questionRepository.existsById(question.getId())) {
            NotFoundException notFoundException = new NotFoundException("question with ID " + question.getId() + " not found");
            log.error("error question {} not found could not update a non existing tuple", question.getId() , notFoundException);
            return;
        }
        questionRepository.save(question);
        log.info("Created question {} successfully", question.getId());
    }
    private void extracted(QuestionRequestBody questionRequestBody, Question question) {
        Bundle bundle = bundleService.getBundle(questionRequestBody.getBundleId());
        question.setBundle(bundle);
        questionRequestBody.getChoices().forEach(
                question::addChoice
        );
    }
     String getAnswerText(Long id){
        log.info("Getting answer text for question {}",id);
        Optional<String> answerText = questionRepository.findChoiceTextByQuestionIdAndAnswer(id);
        if (answerText.isEmpty()){
            ApiRequestException apire = new ApiRequestException("answer choice text for question with ID "+ id + " not found");
            log.error("error fetching answer text for question {}", id, apire);
            throw apire;
        }
        log.info("Getting answer text for question {}",id);
        return answerText.get();
    }
}
