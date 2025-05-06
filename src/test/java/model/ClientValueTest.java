package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.ull.animal_shelter.backend.model.ClientValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientValueTest {

    @Test
    void testBuilderAndGetters() {
        ClientValue cv = ClientValue.builder()
                .value("Excellent service")
                .stars(5)
                .build();

        assertEquals("Excellent service", cv.getValue());
        assertEquals(5, cv.getStars());
    }

    @Test
    void testAllArgsConstructor() {
        ClientValue cv = new ClientValue("Good communication", 4);

        assertEquals("Good communication", cv.getValue());
        assertEquals(4, cv.getStars());
    }

    @Test
    void testJsonSerializationIncludesAllFields() throws Exception {
        ClientValue cv = ClientValue.builder()
                .value(" prompt response ")
                .stars(3)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cv);

        assertTrue(json.contains("prompt response"));
        assertTrue(json.contains("stars"));
    }

    @Test
    void testJsonSerializationNullStars() throws Exception {
        ClientValue cv = ClientValue.builder()
                .value("Needs improvement")
                .stars(null)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cv);

        assertTrue(json.contains("Needs improvement"));
        assertTrue(json.contains("stars"));
    }
}
