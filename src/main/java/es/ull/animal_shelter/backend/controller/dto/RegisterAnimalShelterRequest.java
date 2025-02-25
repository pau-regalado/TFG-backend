package es.ull.animal_shelter.backend.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterAnimalShelterRequest extends RegisterUserRequest {
    private String location;
    private Integer numberPhone;
}