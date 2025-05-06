package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.ull.animal_shelter.backend.controller.dto.RegisterAnimalShelterRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterAnimalShelterRequestTest {

    @Test
    void testBuilderAndGetters() {
        RegisterAnimalShelterRequest request = RegisterAnimalShelterRequest.builder()
                .username("shelter123")
                .password("securePass")
                .email("shelter@example.com")
                .location("123 Main Street")
                .numberPhone(123456789)
                .imageUrl("http://example.com/image.jpg")
                .latitude(28.1234)
                .longitude(-15.4321)
                .build();

        assertEquals("shelter123", request.getUsername());
        assertEquals("securePass", request.getPassword());
        assertEquals("shelter@example.com", request.getEmail());
        assertEquals("123 Main Street", request.getLocation());
        assertEquals(123456789, request.getNumberPhone());
        assertEquals("http://example.com/image.jpg", request.getImageUrl());
        assertEquals(28.1234, request.getLatitude());
        assertEquals(-15.4321, request.getLongitude());
    }

    @Test
    void testJsonSerializationWithoutNulls() throws Exception {
        RegisterAnimalShelterRequest request = RegisterAnimalShelterRequest.builder()
                .username("shelter123")
                .password("securePass")
                .email("shelter@example.com")
                .location(null) // Valor nulo para comprobar exclusión en JSON
                .numberPhone(123456789)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(request);

        // Verificamos que el JSON contiene los campos con valores y no incluye `location`
        assertTrue(json.contains("shelter123"));
        assertTrue(json.contains("securePass"));
        assertTrue(json.contains("shelter@example.com"));
        assertTrue(json.contains("123456789"));
        assertFalse(json.contains("location"));
    }
}
