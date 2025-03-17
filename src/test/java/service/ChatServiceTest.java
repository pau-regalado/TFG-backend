package service;

import es.ull.animal_shelter.backend.BackendApplication;
import es.ull.animal_shelter.backend.controller.dto.ChatCreation;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.model.Chat;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.model.Message;
import es.ull.animal_shelter.backend.service.AnimalService;
import es.ull.animal_shelter.backend.service.AnimalShelterService;
import es.ull.animal_shelter.backend.service.ChatService;
import es.ull.animal_shelter.backend.service.ClientService;
import es.ull.animal_shelter.backend.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("dev")
class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalShelterService animalShelterService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MessageService messageService;

    /**
     * Utiliza un animal presembrado (por ejemplo, Brenda, id "128") que ya forma parte de un refugio (Rescue1)
     * y crea un cliente nuevo con id "c_chat1" para formar el chat.
     */
    @Test
    void testSaveChat() {
        // Se recupera el animal ya sembrado (Brenda, id "128")
        Animal animal = animalService.findById("128");
        // Se obtiene el refugio asociado (según lo sembrado en DatabaseTest, Rescue1 contiene a "128")
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 128");

        // Se crea un cliente nuevo con id fijo
        Client client = Client.builder()
                .id("c_chat1")
                .name("Alice")
                .build();
        clientService.save(client);

        // Se forma el DTO para crear el chat usando los datos existentes
        ChatCreation chatCreation = new ChatCreation(animal.getId(), client.getId());
        Chat chat = chatService.save(chatCreation);

        assertNotNull(chat);
        assertEquals(animal.getId(), chat.getAnimal().getId());
        assertEquals(client.getId(), chat.getClient().getId());
        // El refugio se determina a partir del animal
        assertEquals(shelter.getId(), chat.getAnimalShelter().getId());
        assertTrue(chat.getMessages().isEmpty());
    }

    /**
     * Se prueba que, al crear un chat con la misma combinación de animal y cliente,
     * se retorne el chat ya existente.
     * En este test se utiliza el animal con id "142" (Chispa, según la semilla) y se crea un cliente nuevo.
     */
    @Test
    void testSaveChat_ExistingChat() {
        Animal animal = animalService.findById("142");
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 142");

        Client client = Client.builder()
                .id("c_chat2")
                .name("Bob")
                .build();
        clientService.save(client);

        ChatCreation chatCreation = new ChatCreation(animal.getId(), client.getId());
        Chat chat1 = chatService.save(chatCreation);
        Chat chat2 = chatService.save(chatCreation);

        // Se espera que se retorne el mismo chat
        assertEquals(chat1.getId(), chat2.getId());
    }

    /**
     * Se prueba la consulta de chats por id de cliente.
     * Se utiliza el animal con id "130" (Dodo, presembrado) y se crea un cliente nuevo.
     */
    @Test
    void testFindByClientId() {
        Animal animal = animalService.findById("130");
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 130");

        Client client = Client.builder()
                .id("c_chat3")
                .name("Carol")
                .build();
        clientService.save(client);

        ChatCreation chatCreation = new ChatCreation(animal.getId(), client.getId());
        chatService.save(chatCreation);

        List<Chat> chats = chatService.findByClientId(client.getId());
        assertNotNull(chats);
        assertFalse(chats.isEmpty());
        chats.forEach(chat -> assertEquals(client.getId(), chat.getClient().getId()));
    }

    /**
     * Se prueba la consulta de chats por id de refugio.
     * Se utiliza el animal con id "131" (Freddy, presembrado) y se crea un cliente nuevo.
     */
    @Test
    void testFindByAnimalShelter() {
        Animal animal = animalService.findById("131");
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 131");

        Client client = Client.builder()
                .id("c_chat4")
                .name("David")
                .build();
        clientService.save(client);

        ChatCreation chatCreation = new ChatCreation(animal.getId(), client.getId());
        chatService.save(chatCreation);

        List<Chat> chats = chatService.findByAnimalShelter(shelter.getId());
        assertNotNull(chats);
        assertFalse(chats.isEmpty());
        chats.forEach(chat -> assertEquals(shelter.getId(), chat.getAnimalShelter().getId()));
    }

    /**
     * Se prueba la consulta de chats por cliente y refugio.
     * Se utiliza el animal con id "128" (Brenda) para obtener su refugio y se crea un cliente nuevo.
     */
    @Test
    void testFindByClientAndAnimalShelter() {
        Animal animal = animalService.findById("128");
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 128");

        Client client = Client.builder()
                .id("c_chat5")
                .name("Eva")
                .build();
        clientService.save(client);

        ChatCreation chatCreation = new ChatCreation(animal.getId(), client.getId());
        chatService.save(chatCreation);

        List<Chat> chats = chatService.findByClientAndAnimalShelter(client.getId(), shelter.getId());
        assertNotNull(chats);
        assertFalse(chats.isEmpty());
        chats.forEach(chat -> {
            assertEquals(client.getId(), chat.getClient().getId());
            assertEquals(shelter.getId(), chat.getAnimalShelter().getId());
        });
    }

    /**
     * Se prueba la consulta de un chat por su id.
     * Se utiliza el animal con id "129" (Dakota) y se crea un cliente nuevo.
     */
    @Test
    void testFindById() {
        Animal animal = animalService.findById("129");
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 129");

        Client client = Client.builder()
                .id("c_chat6")
                .name("Frank")
                .build();
        clientService.save(client);

        ChatCreation chatCreation = new ChatCreation(animal.getId(), client.getId());
        Chat chat = chatService.save(chatCreation);

        Chat foundChat = chatService.findById(chat.getId());
        assertNotNull(foundChat);
        assertEquals(chat.getId(), foundChat.getId());
    }

    /**
     * Se prueba que la consulta de un chat inexistente lanza la excepción esperada.
     */
    @Test
    void testFindById_NotFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            chatService.findById("nonexistent");
        });
        assertEquals("404 NOT_FOUND \"Chat no encontrado con ID: nonexistent\"", exception.getMessage());
    }

    /**
     * Se prueba el envío de un mensaje en un chat.
     * Se utiliza el animal con id "130" (Dodo) y se crea un cliente nuevo.
     */
    @Test
    void testSendMessage() {
        Animal animal = animalService.findById("130");
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 130");

        Client client = Client.builder()
                .id("c_chat7")
                .name("Grace")
                .build();
        clientService.save(client);

        ChatCreation chatCreation = new ChatCreation(animal.getId(), client.getId());
        Chat chat = chatService.save(chatCreation);

        Message message = Message.builder()
                .message("Hello, this is a test message.")
                .userType(true)
                .build();

        Chat chatAfterMessage = chatService.sendMessage(chat.getId(), message);
        assertNotNull(chatAfterMessage);
        assertFalse(chatAfterMessage.getMessages().isEmpty());
        assertEquals("Hello, this is a test message.", chatAfterMessage.getMessages().get(0).getMessage());
    }
}
