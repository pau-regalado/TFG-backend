package es.ull.animal_shelter.backend.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterClientRequest extends RegisterUserRequest {
    private String lastName;
}