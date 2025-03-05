package es.ull.animal_shelter.backend.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterUserRequest {
    private String username;
    private String password;
    private String name;
    private String email;
}