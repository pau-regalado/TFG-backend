package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.ull.animal_shelter.backend.controller.dto.AnimalLikes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalLikesTest {

    @Test
    void testBuilderAndGetters() {
        AnimalLikes likes = AnimalLikes.builder()
                .animalId("A123")
                .likes(42)
                .build();

        assertEquals("A123", likes.getAnimalId());
        assertEquals(42, likes.getLikes());
    }

    @Test
    void testAllArgsConstructor() {
        AnimalLikes likes = new AnimalLikes("B456", 7);

        assertEquals("B456", likes.getAnimalId());
        assertEquals(7, likes.getLikes());
    }

    @Test
    void testJsonSerializationWithoutNulls() throws Exception {
        // se crea un objeto con likes en null
        AnimalLikes likes = new AnimalLikes("C789", null);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(likes);

        // Verificamos que el JSON contiene "animalId" pero no "likes"
        assertTrue(json.contains("C789"));
        assertFalse(json.contains("likes"));
    }
}
