package es.ull.animal_shelter.backend.service;

import es.ull.animal_shelter.backend.controller.dto.ChatCreation;
import es.ull.animal_shelter.backend.model.*;
import es.ull.animal_shelter.backend.repository.ChatRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatService {
    @Autowired
    private AnimalService animalService;
    @Autowired
    private AnimalShelterService animalShelterService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRepository chatRepository;

    public Chat save(ChatCreation chatCreation) {
        Animal animal = animalService.findById(chatCreation.getAnimalId());
        AnimalShelter animalShelter = animalShelterService.findByAnimal(animal);
        Client client = clientService.findById(chatCreation.getClientId());

        Optional<Chat> existingChat = this.checkIfChatIsAlreadyOpened(animal, client, animalShelter);

        if (existingChat.isPresent()) {
            return existingChat.get();
        }

        Chat chat = Chat.builder()
                .animal(animal)
                .client(client)
                .animalShelter(animalShelter)
                .messages(List.of())
                .build();
        return chatRepository.save(chat);
    }

    private Optional<Chat> checkIfChatIsAlreadyOpened(Animal animal, Client client, AnimalShelter animalShelter) {
        return this.chatRepository.findChatByAnimalAndClientAndAnimalShelter(
                animal,
                client,
                animalShelter
        );
    }

    public List<Chat> findByClientId(String clientId) {
        Client client = clientService.findById(clientId);
        LogManager.getLogger(this.getClass()).warn(
                chatRepository.findChatByClient(client)
        );
        return chatRepository.findChatByClient(client);
    }

    public List<Chat> findByAnimalShelter(String animalShelterId) {
        return chatRepository.findChatByAnimalShelterId(animalShelterId);
    }

    public List<Chat> findByClientAndAnimalShelter(String clientId, String animalShelterId) {
        return chatRepository.findChatByAnimalShelterIdAndClientId(animalShelterId, clientId);
    }

    public Chat findById(String id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat no encontrado con ID: " + id));
    }

    public Chat sendMessage(String id, Message message) {
        Chat chat = this.findById(id);
        Message newMessage = Message.builder()
                .date(LocalDateTime.now())
                .message(message.getMessage())
                .userType(message.isUserType())
                .build();
        Message createMessage = this.messageService.save(newMessage);
        chat.getMessages().add(createMessage);
        return chatRepository.save(chat);
    }
}
