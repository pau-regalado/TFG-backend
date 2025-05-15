package es.ull.animal_shelter.backend.controller;

import es.ull.animal_shelter.backend.controller.dto.ChatCreation;
import es.ull.animal_shelter.backend.model.Chat;
import es.ull.animal_shelter.backend.model.Message;
import es.ull.animal_shelter.backend.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/chats")
public class ChatController {

    private ChatService chatService;

    @PostMapping
    public Chat save(@RequestBody ChatCreation chatCreation) {
        return chatService.save(chatCreation);
    }

    @GetMapping("/{id}")
    public Chat findById(@PathVariable String id) {
        return chatService.findById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Chat> findByClientId(@PathVariable String clientId) {
        return chatService.findByClientId(clientId);
    }

    @GetMapping("/animal-shelter/{animalShelterId}")
    public List<Chat> findByAnimalShelter(@PathVariable String animalShelterId) {
        return chatService.findByAnimalShelter(animalShelterId);
    }

    @GetMapping("/{animalShelterId}/{clientId}")
    public List<Chat> findByAnimalShelterAndClient(@PathVariable String animalShelterId, @PathVariable String clientId) {
        return chatService.findByClientAndAnimalShelter(clientId, animalShelterId);
    }

    @PostMapping("/{id}/send")
    public ResponseEntity<Chat> sendMessage(@PathVariable String id, @RequestBody Message message) {
        return ResponseEntity.ok(chatService.sendMessage(id, message));
    }

}
