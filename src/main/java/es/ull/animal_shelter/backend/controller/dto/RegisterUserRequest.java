package es.ull.animal_shelter.backend.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterUserRequest {
    private String username;
    private String password;
    private String name;
    private String email;
}