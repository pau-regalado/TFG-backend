package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.ull.animal_shelter.backend.controller.dto.RegisterUserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterUserRequestTest {

    @Test
    void testBuilderAndGetters() {
        RegisterUserRequest request = RegisterUserRequest.builder()
                .username("user123")
                .password("securePass")
                .name("John")
                .email("user@example.com")
                .build();

        assertEquals("user123", request.getUsername());
        assertEquals("securePass", request.getPassword());
        assertEquals("John", request.getName());
        assertEquals("user@example.com", request.getEmail());
    }

}
