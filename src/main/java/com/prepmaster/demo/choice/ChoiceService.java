package com.prepmaster.demo.choice;

import com.prepmaster.demo.exception.NotFoundException;
import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.question.QuestionRepository;
import com.prepmaster.demo.question.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor //LOMBOK SEE VIDEO
@Slf4j // so we can use log variable
public class ChoiceService {
    private final ChoiceRepository choiceRepository;
        private final QuestionService questionService;
        private final QuestionRepository questionRepository;
    public List<Choice> getChoices() {
        return choiceRepository.findAll();
    }

    public Choice getChoice(Long id) {
        log.info("Getting choice {}", id);
        Choice choice = choiceRepository
                .findById(id)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("choice with ID " + id + " not found");
                            log.error("error choice {} not found", id , notFoundException);
                            return notFoundException;
                        }
                );
        log.info("Got choice {}", choice.getId());
        return choice;
    }

    public void createNewChoice(ChoiceRequestBody choiceRequestBody) {
        Choice choice = choiceRequestBody.getChoice();
        log.info("creating choice {}",choice);
        extracted(choiceRequestBody, choice);
        choiceRepository.save(choice);
        log.info("Created choice {} successfully", choice.getId());
    }

    public void deleteChoice(Long id) {
        log.info("Deleting course {}", id);
        if (!choiceRepository.existsById(id)) {
            NotFoundException notFoundException = new NotFoundException("choice with ID " + id + " not found");
            log.error("error choice {} not found could not delete a non existing tuple", id , notFoundException);
            return;
        }
        choiceRepository.deleteById(id);
        log.info("Deleted choice {} successfully", id);
    }

    public void updateChoice(ChoiceRequestBody choiceRequestBody) {
        Choice choice = choiceRequestBody.getChoice();
        log.info("Updating choice {}",choice);
        extracted(choiceRequestBody, choice);
        if (!choiceRepository.existsById(choice.getId())) {
            NotFoundException notFoundException = new NotFoundException("choice with ID " + choice.getId() + " not found");
            log.error("error choice {} not found could not update a non existing tuple", choice.getId() , notFoundException);
            return;
        }
        choiceRepository.save(choice);
        log.info("Updated choice {} successfully", choice.getId());
    }


    private void extracted(ChoiceRequestBody choiceRequestBody, Choice choice) {
        Question question = questionService.getQuestion(choiceRequestBody.getQuestionId());
        choice.setQuestion(question);
    }
}
