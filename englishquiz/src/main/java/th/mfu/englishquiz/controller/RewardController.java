package th.mfu.englishquiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.englishquiz.entity.GameCharacter;
import th.mfu.englishquiz.entity.Quest;
import th.mfu.englishquiz.entity.Reward;
import th.mfu.englishquiz.repository.RewardRepository;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    private RewardRepository rewardRepository;

   /*  @GetMapping
    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    } */

    @PostMapping
    public ResponseEntity<String> createReward(@RequestBody Reward newreward) {
        rewardRepository.save(newreward);
        return new ResponseEntity<String>("Reward created",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reward> getRewardById(@PathVariable Long id){
        if(!rewardRepository.existsById(id)){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        rewardRepository.findById(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
    
    /*@PutMapping("/{id}")
    public Reward updateReward(@PathVariable Long id, @RequestBody Reward updatedReward) {
        Reward reward = rewardRepository.findById(id).orElse(null);
        if (reward != null) {
            reward.setRewardName(updatedReward.getRewardName());
            reward.setPointRequired(updatedReward.getPointRequired());
            return rewardRepository.save(reward);
        }
        return null;
    } */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReward(@PathVariable Long id) {
         if(!rewardRepository.existsById(id)){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

        rewardRepository.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
