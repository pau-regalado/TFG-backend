package model;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.model.Chat;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

class ChatTest {
    private Chat chat1;
    private Chat chat2;
    private Animal dummyAnimal;
    private AnimalShelter dummyShelter;
    private Client dummyClient;
    private Message dummyMessage1;
    private Message dummyMessage2;

    @BeforeEach
    void setUp() {
        // Instanciando un Animal dummy
        dummyAnimal = Animal.builder()
                .id("a1")
                .name("Buddy")
                .color("brown")
                .size("medium")
                .race("labrador")
                .description("Friendly dog")
                .birth_date("01-01-2020")
                .entryDate("02-02-2020")
                .sex("Macho")
                .age(3)
                .sterile(true)
                .disability(false)
                .imageUrl("http://example.com/buddy.jpg")
                .build();

        // Instanciando un AnimalShelter dummy
        dummyShelter = AnimalShelter.builder()
                .id("s1")
                .username("shelterUser")
                .password("password")
                .email("shelter@example.com")
                .name("Animal Shelter")
                .location("City")
                .numberPhone(123456789)
                .imageUrl("http://example.com/shelter.jpg")
                .animalWL(List.of())
                .build();

        // Instanciando un Client dummy (se asume estructura similar a las otras clases)
        dummyClient = Client.builder()
                .id("c1")
                .username("clientUser")
                .password("clientPass")
                .email("client@example.com")
                .name("Client Name")
                .build();

        // Instanciando mensajes dummy
        dummyMessage1 = Message.builder()
                .id("m1")
                .date(LocalDateTime.of(2024, 7, 1, 12, 0))
                .message("Hi!")
                .userType(true)
                .build();

        dummyMessage2 = Message.builder()
                .id("m2")
                .date(LocalDateTime.of(2024, 7, 1, 12, 5))
                .message("Hello!")
                .userType(false)
                .build();

        // Creando instancias de Chat
        chat1 = Chat.builder()
                .id("chat1")
                .animal(dummyAnimal)
                .animalShelter(dummyShelter)
                .client(dummyClient)
                .messages(List.of(dummyMessage1))
                .build();

        chat2 = Chat.builder()
                .id("chat2")
                .animal(dummyAnimal)
                .animalShelter(dummyShelter)
                .client(dummyClient)
                .messages(List.of(dummyMessage1, dummyMessage2))
                .build();
    }

    @Test
    void testGetters() {
        // Verificar los getters de chat1
        assertEquals("chat1", chat1.getId());
        assertEquals(dummyAnimal, chat1.getAnimal());
        assertEquals(dummyShelter, chat1.getAnimalShelter());
        assertEquals(dummyClient, chat1.getClient());
        assertEquals(1, chat1.getMessages().size());
        assertTrue(chat1.getMessages().contains(dummyMessage1));

        // Verificar los getters de chat2
        assertEquals("chat2", chat2.getId());
        assertEquals(2, chat2.getMessages().size());
        assertTrue(chat2.getMessages().contains(dummyMessage2));
    }

    @Test
    void testSetters() {
        // Modificar atributos de chat1
        chat1.setId("chat1_updated");
        chat1.setMessages(List.of(dummyMessage1, dummyMessage2));

        assertEquals("chat1_updated", chat1.getId());
        assertEquals(2, chat1.getMessages().size());
        assertTrue(chat1.getMessages().contains(dummyMessage2));
    }

    @Test
    void testEqualsAndHashCode() {
        Chat sameAsChat1 = Chat.builder()
                .id("chat1")
                .animal(dummyAnimal)
                .animalShelter(dummyShelter)
                .client(dummyClient)
                .messages(List.of(dummyMessage1))
                .build();

        assertEquals(chat1, sameAsChat1);
        assertEquals(chat1.hashCode(), sameAsChat1.hashCode());

        assertNotEquals(chat1, chat2);
        assertNotEquals(chat1.hashCode(), chat2.hashCode());
    }

    /*@Test
    void testToString() {
        String chat1String = chat1.toString();
        // Se verifica que la representación en cadena incluya algunos identificadores clave
        assertTrue(chat1String.contains("chat1"));
        assertTrue(chat1String.contains(dummyAnimal.getId()));
        assertTrue(chat1String.contains(dummyShelter.getId()));
        assertTrue(chat1String.contains(dummyClient.getId()));
        assertTrue(chat1String.contains("m1")); // Se espera que aparezca el id del mensaje
    }*/
}

