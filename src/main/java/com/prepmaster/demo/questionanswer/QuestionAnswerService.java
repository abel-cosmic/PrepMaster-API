package com.prepmaster.demo.questionanswer;

import com.prepmaster.demo.bundle.Bundle;
import com.prepmaster.demo.exception.NotFoundException;
import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.question.QuestionRepository;
import com.prepmaster.demo.test.Test;
import com.prepmaster.demo.test.TestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionAnswerService {
    private final QuestionAnswerRepository questionAnswerRepository;
    private final TestService testService;
    private final QuestionRepository questionRepository;

    public QuestionAnswer getQuestionAnswer(QuestionAnswerID id){
        log.info("Getting question answer {}", id);
        Question question = questionRepository.findById(id.getQuestionId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("question with ID " + id.getQuestionId().toString() + " not found");
                            log.error("error question {} not found", id.getQuestionId().toString() , notFoundException);
                            return notFoundException;
                        }
                );
        QuestionAnswer questionAnswer = questionAnswerRepository
                .findByTestAndQuestion(testService.getTest(id.getTestId()),question);
        log.info("Got question answer {}", questionAnswer.getId());
        return questionAnswer;
    }
    void createQuestionAnswer(QuestionAnswerRequestBody questionAnswerRequestBody){
        QuestionAnswer questionAnswer = questionAnswerRequestBody.getQuestionAnswer();
        log.info("Creating question answer {}", questionAnswer);
        Test test = testService.getTest(questionAnswerRequestBody.getTestId());
        Long questionId = questionAnswerRequestBody.getQuestionId();
        Question question = questionRepository.findById(questionId)
                        .orElseThrow(
                                () -> {
                                    NotFoundException notFoundException = new NotFoundException("question with ID " + questionId + " not found");
                                    log.error("error question {} not found", questionId , notFoundException);
                                    return notFoundException;
                                }
                        );
        QuestionAnswerID questionAnswerID = new QuestionAnswerID(test.getId(),question.getId());
        questionAnswer.setId(questionAnswerID);
        questionAnswer.setTest(test);
        questionAnswer.setQuestion(question);
        //TODO check if this already exists before saving
        questionAnswerRepository.save(questionAnswer);
        log.info("Created question answer {} successfully", questionAnswer.getId());
    }
    void updateQuestionAnswer(QuestionAnswerRequestBody questionAnswerRequestBody){
        QuestionAnswer questionAnswer = questionAnswerRequestBody.getQuestionAnswer();
        log.info("Updating question answer {}", questionAnswer);
        Test test = testService.getTest(questionAnswerRequestBody.getTestId());
        Long questionId = questionAnswerRequestBody.getQuestionId();
        Question question = questionRepository.findById(questionId)
                        .orElseThrow(
                                () -> {
                                    NotFoundException notFoundException = new NotFoundException("question with ID " + questionId + " not found");
                                    log.error("error question {} not found", questionId , notFoundException);
                                    return notFoundException;
                                }
                        );
        QuestionAnswerID questionAnswerID = new QuestionAnswerID(test.getId(),question.getId());
        questionAnswer.setId(questionAnswerID);
        questionAnswer.setTest(test);
        questionAnswer.setQuestion(question);
        questionAnswerRepository.save(questionAnswer);
        log.info("Updated question answer {} successfully", questionAnswer.getId());
    }

    void deleteQuestionAnswer(QuestionAnswerID id){
        log.info("Deleting question answer {}", id);
        //TODO CHECK IF IT EXISTS USING CUSTOM QUERY
//        if (!questionAnswerRepository.existsById(id)) {
//            NotFoundException notFoundException = new NotFoundException("Bundle with ID " + id + " not found");
//            log.error("error bundle {} not found could not delete a non existing tuple", id , notFoundException);
//            return;
//        }
        questionAnswerRepository.deleteById(id);
        log.info("Deleted question answer {} successfully", id);
    }

    List<QuestionAnswer> getQuestionAnswers(){
        log.info("Getting all question answers");
        List<QuestionAnswer> questionAnswers = questionAnswerRepository.findAll();
        log.info("Got all question answers");
        return questionAnswers;
    }
}
