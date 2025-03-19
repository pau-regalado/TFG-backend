package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.ull.animal_shelter.backend.controller.dto.RegisterClientRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterClientRequestTest {

    @Test
    void testBuilderAndGetters() {
        RegisterClientRequest request = RegisterClientRequest.builder()
                .username("client123")
                .password("securePass")
                .email("client@example.com")
                .lastName("Doe")
                .build();

        assertEquals("client123", request.getUsername());
        assertEquals("securePass", request.getPassword());
        assertEquals("client@example.com", request.getEmail());
        assertEquals("Doe", request.getLastName());
    }

    @Test
    void testJsonSerializationWithoutNulls() throws Exception {
        RegisterClientRequest request = RegisterClientRequest.builder()
                .username("client123")
                .password("securePass")
                .email("client@example.com")
                .lastName(null) // Campo nulo para verificar que no aparece en el JSON
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(request);

        // Verificamos que el JSON contiene los campos con valores y no incluye `lastName`
        assertTrue(json.contains("client123"));
        assertTrue(json.contains("securePass"));
        assertTrue(json.contains("client@example.com"));
        assertFalse(json.contains("lastName"));
    }
}

