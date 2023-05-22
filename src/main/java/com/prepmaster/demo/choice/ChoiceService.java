package com.prepmaster.demo.choice;

import com.prepmaster.demo.bundle.Bundle;
import com.prepmaster.demo.exception.NotFoundException;
import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.question.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor //LOMBOK SEE VIDEO
@Slf4j // so we can use log variable
public class ChoiceService {
    private final ChoiceRepository choiceRepository;
    private final QuestionRepository questionRepository;
    public List<Choice> getChoices() {
        return choiceRepository.findAll();
    }

    public Choice getChioce(Long id) {
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
        Long questionId = choiceRequestBody.getQuestionId();
        Question question = questionRepository.findById(questionId)
                .orElseThrow(
                        ()->{
                            NotFoundException notFoundException = new NotFoundException("choice with ID " + questionId + " not found");
                            log.error("error choice {} not found", questionId , notFoundException);
                            return notFoundException;
                        }
                );
        List<Choice> choices = new ArrayList<>();
        choices.add(choice);
        question.setChoices(choices);
        questionRepository.save(question);
        log.info("Created choice {} successfully", question.getId());
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

    public void createUpdateChoice(ChoiceRequestBody choiceRequestBody) {
        Choice choice = choiceRequestBody.getChoice();
        log.info("creating choice {}",choice);
        Long questionId = choiceRequestBody.getQuestionId();
        Question question = questionRepository.findById(questionId)
                .orElseThrow(
                        ()->{
                            NotFoundException notFoundException = new NotFoundException("choice with ID " + questionId + " not found");
                            log.error("error choice {} not found", questionId , notFoundException);
                            return notFoundException;
                        }
                );
        List<Choice> choices = new ArrayList<>();
        choices.add(choice);
        question.setChoices(choices);
        if (!choiceRepository.existsById(choice.getId())) {
            NotFoundException notFoundException = new NotFoundException("choice with ID " + choice.getId() + " not found");
            log.error("error choice {} not found could not update a non existing tuple", choice.getId() , notFoundException);
            return;
        }
        questionRepository.save(question);
        log.info("Created choice {} successfully", question.getId());
    }
}
