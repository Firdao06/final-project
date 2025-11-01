package th.mfu.englishquiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import th.mfu.englishquiz.entity.Quiz;
import th.mfu.englishquiz.entity.Quest;
import th.mfu.englishquiz.repository.QuestRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import th.mfu.englishquiz.repository.QuizRepository;


@RestController
public class QuestController {

    @Autowired
    private QuestRepository questRepository;
    @Autowired
    private QuizRepository quizRepository;

    
    @GetMapping("/quests")   //GET all quest
    public ResponseEntity<Collection<Quest>> listQuest(){
        List<Quest>  Quests = questRepository.findAll();
        return new ResponseEntity<>(Quests,HttpStatus.OK);
    }

    @PostMapping("/quests") 
    public ResponseEntity<String> createQuest(@RequestBody Quest newquest) {
        questRepository.save(newquest);
        return new ResponseEntity<String>("Quest created",HttpStatus.CREATED);
    }

    
    @PutMapping("/quests/{id}")
    public ResponseEntity<String> updateQuest(@PathVariable Long id, @RequestBody Quest updatedQuest) {
        Quest quest = questRepository.findById(id).orElse(null);
        if (quest != null) {
            quest.setQuestName(updatedQuest.getQuestName());
            quest.setDescription(updatedQuest.getDescription());
            // quest.setActive(updatedQuest.isActive());
            questRepository.save(quest);
            return new ResponseEntity<>("Quest updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/quests/{id}")
    public ResponseEntity<String> deleteQuest(@PathVariable Long id) {
        if(!questRepository.existsById(id)){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        questRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/quests/{questId}/quiz")
    public ResponseEntity<Collection<Quiz>> listQuiz(@PathVariable Long questId){
        Optional<Quest> questOp = questRepository.findById(questId);
        if(questOp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Collection<Quiz> quizzes = quizRepository.findByQuestId(questId);
        return new ResponseEntity<>(quizzes,HttpStatus.OK);
    }

    @PostMapping("/quests/{questId}/quiz")
    public ResponseEntity<String> listQuiz(@RequestBody Quiz newquiz ,@PathVariable Long questId){
        Optional<Quest> questOp = questRepository.findById(questId);
        if(!questOp.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //find quest by Id
        Quest quest = questOp.get();
        //set quest to the new quiz
        newquiz.setQuest(quest);
        //save new quiz
        quizRepository.save(newquiz);

        return new ResponseEntity<String>("Quiz saved",HttpStatus.OK);
    }

}
