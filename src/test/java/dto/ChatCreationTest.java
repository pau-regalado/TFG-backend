package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.ull.animal_shelter.backend.controller.dto.ChatCreation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatCreationTest {

    @Test
    void testBuilderAndGetters() {
        ChatCreation chat = ChatCreation.builder()
                .animalId("A789")
                .clientId("C123")
                .build();

        assertEquals("A789", chat.getAnimalId());
        assertEquals("C123", chat.getClientId());
    }

    @Test
    void testAllArgsConstructor() {
        ChatCreation chat = new ChatCreation("A789", "C123");

        assertEquals("A789", chat.getAnimalId());
        assertEquals("C123", chat.getClientId());
    }

    @Test
    void testJsonSerializationWithoutNulls() throws Exception {
        // Se crea un objeto con clientId en null
        ChatCreation chat = new ChatCreation("A789", null);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(chat);

        // Verificamos que el JSON contiene "animalId" pero no "clientId"
        assertTrue(json.contains("A789"));
        assertFalse(json.contains("clientId"));
    }
}

