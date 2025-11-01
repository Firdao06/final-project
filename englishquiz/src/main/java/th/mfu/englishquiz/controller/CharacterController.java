package th.mfu.englishquiz.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.englishquiz.entity.GameCharacter;
import th.mfu.englishquiz.repository.CharacterRepository;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping   //Retrive all characteers
    public ResponseEntity<Collection<GameCharacter>> listGameCharacters(){
        List<GameCharacter> characters = characterRepository.findAll();
        return new ResponseEntity<>(characters,HttpStatus.OK);
    }
    

    /*@PostMapping
     public ResponseEntity<GameCharacter> createCharacters(@RequestBody GameCharacter character){
        
         characterRepository.save(character);
       
        return new ResponseEntity<>(HttpStatus.CREATED);
    } */
    

    @GetMapping("/{id}")
    public ResponseEntity<GameCharacter> getCharacterById(@PathVariable Long id){
        if(!characterRepository.existsById(id)){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        characterRepository.findById(id);
        return new ResponseEntity<>( HttpStatus.OK);

    }
    
    /*@PutMapping("/{id}")
    public Character updateCharacter(@PathVariable Long id, @RequestBody Character updatedCharacter) {
        Character character = characterRepository.findById(id).orElse(null);
        if (character != null) {
            character.setName(updatedCharacter.getName());
            character.setLevel(updatedCharacter.getLevel());
            character.setExperience(updatedCharacter.getExperience());
            return characterRepository.save(character);
        }
        return null;
    } */

     /*@DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable Long id) {
        characterRepository.deleteById(id);
    } */
}
