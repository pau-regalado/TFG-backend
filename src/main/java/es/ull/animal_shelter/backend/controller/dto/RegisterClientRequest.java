package es.ull.animal_shelter.backend.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterClientRequest {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String email;
}