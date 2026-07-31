package Controllers;

import Modelos.Titulo;
import Services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/characters")
@CrossOrigin(origins = "*")
public class CharacterController {
    
    @Autowired
    private CharacterService characterService;
    
    @GetMapping
    public ResponseEntity<?> getCharacter(@RequestParam String name) {
        try {
            Titulo character = characterService.fetchCharacter(name);
            return ResponseEntity.ok(character);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"erro\": \"" + e.getMessage() + "\"}");
        }
    }
}
