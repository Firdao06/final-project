package th.mfu.englishquiz.controller;


import java.util.Collection;
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

import th.mfu.englishquiz.entity.User;
import th.mfu.englishquiz.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET all users
    @GetMapping
    public ResponseEntity<Collection<User>> listUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    //GET user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        if(!userRepository.existsById(id)){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.findById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    //POST new user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User newuser) {
        userRepository.save(newuser);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    //PUT update user
    @PutMapping("/{id}")
    /* 
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setLevel(updatedUser.getLevel());
            user.setExperiencePoint(updatedUser.getExperiencePoint());
            user.setTotalScore(updatedUser.getTotalScore());
            return userRepository.save(user);
        }
        return null;
    } */

    // DELETE user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        if(!userRepository.existsById(id)){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
