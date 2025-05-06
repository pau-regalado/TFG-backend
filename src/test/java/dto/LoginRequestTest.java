package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.ull.animal_shelter.backend.controller.dto.LoginRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void testBuilderAndGetters() {
        LoginRequest loginRequest = LoginRequest.builder()
                .username("user123")
                .password("pass456")
                .build();

        assertEquals("user123", loginRequest.getUsername());
        assertEquals("pass456", loginRequest.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        LoginRequest loginRequest = new LoginRequest("user123", "pass456");

        assertEquals("user123", loginRequest.getUsername());
        assertEquals("pass456", loginRequest.getPassword());
    }

    @Test
    void testJsonSerializationWithoutNulls() throws Exception {
        // Se crea un objeto con password en null
        LoginRequest loginRequest = new LoginRequest("user123", null);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(loginRequest);

        // Verificamos que el JSON contiene "username" pero no "password"
        assertTrue(json.contains("user123"));
        assertFalse(json.contains("password"));
    }
}
