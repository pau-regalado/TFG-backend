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

}
