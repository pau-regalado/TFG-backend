package model;

import es.ull.animal_shelter.backend.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class MessageTest {
    private Message message1;
    private Message message2;

    @BeforeEach
    void setUp() {
        message1 = Message.builder()
                .id("msg1")
                .date(LocalDateTime.of(2024, 7, 1, 12, 0))
                .message("Hello, world!")
                .userType(true)
                .build();

        message2 = Message.builder()
                .id("msg2")
                .date(LocalDateTime.of(2024, 7, 2, 13, 30))
                .message("Goodbye!")
                .userType(false)
                .build();
    }

    @Test
    void testGetters() {
        // Verificando los getters para message1
        assertEquals("msg1", message1.getId());
        assertEquals(LocalDateTime.of(2024, 7, 1, 12, 0), message1.getDate());
        assertEquals("Hello, world!", message1.getMessage());
        assertTrue(message1.isUserType());

        // Verificando los getters para message2
        assertEquals("msg2", message2.getId());
        assertEquals(LocalDateTime.of(2024, 7, 2, 13, 30), message2.getDate());
        assertEquals("Goodbye!", message2.getMessage());
        assertFalse(message2.isUserType());
    }

    @Test
    void testSetters() {
        // Modificando atributos de message1
        message1.setId("msg1_updated");
        message1.setDate(LocalDateTime.of(2024, 7, 3, 14, 0));
        message1.setMessage("Updated message");
        message1.setUserType(false);

        assertEquals("msg1_updated", message1.getId());
        assertEquals(LocalDateTime.of(2024, 7, 3, 14, 0), message1.getDate());
        assertEquals("Updated message", message1.getMessage());
        assertFalse(message1.isUserType());
    }

    @Test
    void testEqualsAndHashCode() {
        Message sameAsMessage1 = Message.builder()
                .id("msg1")
                .date(LocalDateTime.of(2024, 7, 1, 12, 0))
                .message("Hello, world!")
                .userType(true)
                .build();

        // Se espera que sean iguales
        assertEquals(message1, sameAsMessage1);
        assertEquals(message1.hashCode(), sameAsMessage1.hashCode());

        // Comparación entre objetos diferentes
        assertNotEquals(message1, message2);
        assertNotEquals(message1.hashCode(), message2.hashCode());
    }

    @Test
    void testToString() {
        String message1String = message1.toString();
        assertTrue(message1String.contains("id=msg1"));
        assertTrue(message1String.contains("message=Hello, world!"));
        assertTrue(message1String.contains("2024")); // Verificamos que la fecha contenga "2024"
        assertTrue(message1String.contains("userType=true"));
    }
}
