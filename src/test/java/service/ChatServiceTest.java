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

import java.util.ArrayList;
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

    @Test
    void testSaveChat() {
        // Crear un animal nuevo para el chat
        Animal animal = Animal.builder()
                .id("chat_animal_1")
                .name("Leo")
                .color("gris")
                .size("mediano")
                .race("perro")
                .description("Perro amigable")
                .birth_date("01-01-2020")
                .entryDate("01-02-2021")
                .sex("Macho")
                .age(3)
                .sterile(true)
                .disability(false)
                .imageUrl("http://example.com/leo.jpg")
                .build();
        animalService.save(animal);

        // Crear un refugio y asociarle el animal
        AnimalShelter shelter = AnimalShelter.builder()
                .id("shelter_chat_1")
                .name("Shelter Chat 1")
                .username("shelterchat1")
                .password("admin")
                .animalWL(new ArrayList<>())
                .build();
        animalShelterService.save(shelter);
        shelter.getAnimalWL().add(animal);
        animalShelterService.save(shelter);

        // Crear un cliente
        Client client = Client.builder()
                .id("c_chat1")
                .name("Alice")
                .build();
        clientService.save(client);

        // Crear el chat
        ChatCreation chatCreation = new ChatCreation(animal.getId(), client.getId());
        Chat chat = chatService.save(chatCreation);

        assertNotNull(chat);
        assertEquals(animal.getId(), chat.getAnimal().getId());
        assertEquals(client.getId(), chat.getClient().getId());
        assertEquals(shelter.getId(), chat.getAnimalShelter().getId());
        assertTrue(chat.getMessages().isEmpty());
    }

    @Test
    void testSaveChat_ExistingChat() {
        // Crear un animal nuevo
        Animal animal = Animal.builder()
                .id("chat_animal_2")
                .name("Mia")
                .color("negro")
                .size("pequeño")
                .race("gato")
                .description("Gato juguetón")
                .birth_date("05-05-2020")
                .entryDate("10-05-2021")
                .sex("Hembra")
                .age(2)
                .sterile(true)
                .disability(false)
                .imageUrl("http://example.com/mia.jpg")
                .build();
        animalService.save(animal);

        // Crear un refugio para el animal
        AnimalShelter shelter = AnimalShelter.builder()
                .id("shelter_chat_2")
                .name("Shelter Chat 2")
                .username("shelterchat2")
                .password("admin")
                .animalWL(new ArrayList<>())
                .build();
        animalShelterService.save(shelter);
        shelter.getAnimalWL().add(animal);
        animalShelterService.save(shelter);

        // Crear un cliente
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

    @Test
    void testFindByClientId() {
        // Crear un animal nuevo
        Animal animal = Animal.builder()
                .id("chat_animal_3")
                .name("Dodo")
                .color("marrón")
                .size("grande")
                .race("perro")
                .description("Animal amistoso")
                .birth_date("10-10-2020")
                .entryDate("15-10-2021")
                .sex("Macho")
                .age(4)
                .sterile(true)
                .disability(false)
                .imageUrl("http://example.com/dodo.jpg")
                .build();
        animalService.save(animal);

        // Crear un refugio y asociar el animal
        AnimalShelter shelter = AnimalShelter.builder()
                .id("shelter_chat_3")
                .name("Shelter Chat 3")
                .username("shelterchat3")
                .password("admin")
                .animalWL(new ArrayList<>())
                .build();
        animalShelterService.save(shelter);
        shelter.getAnimalWL().add(animal);
        animalShelterService.save(shelter);

        // Crear un cliente
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

    @Test
    void testFindByAnimalShelter() {
        // Crear un animal nuevo
        Animal animal = Animal.builder()
                .id("chat_animal_4")
                .name("Simba")
                .color("dorado")
                .size("mediano")
                .race("león")
                .description("Animal majestuoso")
                .birth_date("12-12-2019")
                .entryDate("01-01-2020")
                .sex("Macho")
                .age(5)
                .sterile(true)
                .disability(false)
                .imageUrl("http://example.com/simba.jpg")
                .build();
        animalService.save(animal);

        // Crear un refugio y asociar el animal
        AnimalShelter shelter = AnimalShelter.builder()
                .id("shelter_chat_4")
                .name("Shelter Chat 4")
                .username("shelterchat4")
                .password("admin")
                .animalWL(new ArrayList<>())
                .build();
        animalShelterService.save(shelter);
        shelter.getAnimalWL().add(animal);
        animalShelterService.save(shelter);

        // Crear un cliente
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

    @Test
    void testFindByClientAndAnimalShelter() {
        // Crear un animal nuevo
        Animal animal = Animal.builder()
                .id("chat_animal_5")
                .name("Luna")
                .color("blanco")
                .size("pequeño")
                .race("gato")
                .description("Gata dulce")
                .birth_date("20-06-2020")
                .entryDate("25-06-2021")
                .sex("Hembra")
                .age(3)
                .sterile(true)
                .disability(false)
                .imageUrl("http://example.com/luna.jpg")
                .build();
        animalService.save(animal);

        // Crear un refugio y asociar el animal
        AnimalShelter shelter = AnimalShelter.builder()
                .id("shelter_chat_5")
                .name("Shelter Chat 5")
                .username("shelterchat5")
                .password("admin")
                .animalWL(new ArrayList<>())
                .build();
        animalShelterService.save(shelter);
        shelter.getAnimalWL().add(animal);
        animalShelterService.save(shelter);

        // Crear un cliente
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

    @Test
    void testFindById() {
        // Crear un animal nuevo
        Animal animal = Animal.builder()
                .id("chat_animal_6")
                .name("Oscar")
                .color("verde")
                .size("mediano")
                .race("iguana")
                .description("Animal exótico")
                .birth_date("15-03-2020")
                .entryDate("20-03-2021")
                .sex("Macho")
                .age(4)
                .sterile(true)
                .disability(false)
                .imageUrl("http://example.com/oscar.jpg")
                .build();
        animalService.save(animal);

        // Crear un refugio y asociar el animal
        AnimalShelter shelter = AnimalShelter.builder()
                .id("shelter_chat_6")
                .name("Shelter Chat 6")
                .username("shelterchat6")
                .password("admin")
                .animalWL(new ArrayList<>())
                .build();
        animalShelterService.save(shelter);
        shelter.getAnimalWL().add(animal);
        animalShelterService.save(shelter);

        // Crear un cliente
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

    @Test
    void testFindById_NotFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            chatService.findById("nonexistent");
        });
        assertEquals("404 NOT_FOUND \"Chat no encontrado con ID: nonexistent\"", exception.getMessage());
    }

    @Test
    void testSendMessage() {
        // Crear un animal nuevo
        Animal animal = Animal.builder()
                .id("chat_animal_7")
                .name("Ginger")
                .color("anaranjado")
                .size("mediano")
                .race("gato")
                .description("Gata simpática")
                .birth_date("11-11-2020")
                .entryDate("15-11-2021")
                .sex("Hembra")
                .age(3)
                .sterile(true)
                .disability(false)
                .imageUrl("http://example.com/ginger.jpg")
                .build();
        animalService.save(animal);

        // Crear un refugio y asociar el animal
        AnimalShelter shelter = AnimalShelter.builder()
                .id("shelter_chat_7")
                .name("Shelter Chat 7")
                .username("shelterchat7")
                .password("admin")
                .animalWL(new ArrayList<>())
                .build();
        animalShelterService.save(shelter);
        shelter.getAnimalWL().add(animal);
        animalShelterService.save(shelter);

        // Crear un cliente
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
