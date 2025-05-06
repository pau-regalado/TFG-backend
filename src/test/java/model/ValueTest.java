package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.ull.animal_shelter.backend.model.AnimalShelterValue;
import es.ull.animal_shelter.backend.model.ClientValue;
import es.ull.animal_shelter.backend.model.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueTest {

    @Test
    void testBuilderAndGetters() {
        ClientValue clientVal = ClientValue.builder()
                .value("Great experience")
                .stars(5)
                .build();
        AnimalShelterValue shelterVal = AnimalShelterValue.builder()
                .value("Excellent client")
                .stars(4)
                .build();

        Value value = Value.builder()
                .clientValue(clientVal)
                .animalShelterValue(shelterVal)
                .build();

        assertEquals(clientVal, value.getClientValue());
        assertEquals(shelterVal, value.getAnimalShelterValue());
    }

    @Test
    void testAllArgsConstructor() {
        ClientValue clientVal = new ClientValue("Good service", 3);
        AnimalShelterValue shelterVal = new AnimalShelterValue("Polite client", 5);

        Value value = new Value(clientVal, shelterVal);

        assertEquals("Good service", value.getClientValue().getValue());
        assertEquals(3, value.getClientValue().getStars());
        assertEquals("Polite client", value.getAnimalShelterValue().getValue());
        assertEquals(5, value.getAnimalShelterValue().getStars());
    }

    @Test
    void testJsonSerializationClientNull() throws Exception {
        AnimalShelterValue shelterVal = AnimalShelterValue.builder()
                .value("Responsive and caring")
                .stars(4)
                .build();
        Value value = new Value(null, shelterVal);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(value);

        assertTrue(json.contains("Responsive and caring"));
        assertFalse(json.contains("clientValue"));
    }

    @Test
    void testJsonSerializationShelterNull() throws Exception {
        ClientValue clientVal = ClientValue.builder()
                .value("Smooth process")
                .stars(5)
                .build();
        Value value = new Value(clientVal, null);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(value);

        assertTrue(json.contains("Smooth process"));
        assertFalse(json.contains("animalShelterValue"));
    }
}