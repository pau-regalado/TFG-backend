package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.ull.animal_shelter.backend.model.AnimalShelterValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalShelterValueTest {

    @Test
    void testBuilderAndGetters() {
        AnimalShelterValue asv = AnimalShelterValue.builder()
                .value("Very responsive shelter")
                .stars(5)
                .build();

        assertEquals("Very responsive shelter", asv.getValue());
        assertEquals(5, asv.getStars());
    }

    @Test
    void testAllArgsConstructor() {
        AnimalShelterValue asv = new AnimalShelterValue("Clean facilities", 4);

        assertEquals("Clean facilities", asv.getValue());
        assertEquals(4, asv.getStars());
    }

    @Test
    void testJsonSerializationIncludesAllFields() throws Exception {
        AnimalShelterValue asv = AnimalShelterValue.builder()
                .value(" prompt communication ")
                .stars(3)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(asv);

        assertTrue(json.contains("prompt communication"));
        assertTrue(json.contains("stars"));
    }

    @Test
    void testJsonSerializationNullValue() throws Exception {
        AnimalShelterValue asv = AnimalShelterValue.builder()
                .value(null)
                .stars(2)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(asv);

        // value field should be absent, stars should be present
        assertFalse(json.contains("value"));
        assertTrue(json.contains("stars"));
    }

    @Test
    void testJsonSerializationNullStars() throws Exception {
        AnimalShelterValue asv = AnimalShelterValue.builder()
                .value("Friendly staff")
                .stars(null)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(asv);

        assertTrue(json.contains("Friendly staff"));
        assertFalse(json.contains("stars"));
    }
}
