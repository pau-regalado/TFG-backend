package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.ull.animal_shelter.backend.controller.dto.AdoptionDetails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdoptionDetailsTest {

    @Test
    void testBuilderAndGetters() {
        AdoptionDetails details = AdoptionDetails.builder()
                .animalId("A123")
                .clientId("C456")
                .build();

        assertEquals("A123", details.getAnimalId());
        assertEquals("C456", details.getClientId());
    }

    @Test
    void testAllArgsConstructor() {
        AdoptionDetails details = new AdoptionDetails("A123", "C456");

        assertEquals("A123", details.getAnimalId());
        assertEquals("C456", details.getClientId());
    }

    @Test
    void testJsonSerializationWithoutNulls() throws Exception {
        // Se crea un objeto donde clientId es null
        AdoptionDetails details = new AdoptionDetails("A123", null);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(details);

        // El JSON debe incluir animalId pero no clientId
        assertTrue(json.contains("A123"));
        assertFalse(json.contains("clientId"));
    }
}
