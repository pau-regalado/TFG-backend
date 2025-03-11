package es.ull.animal_shelter.backend.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterAnimalShelterRequest extends RegisterUserRequest {
    private String location;
    private Integer numberPhone;
    private String imageUrl;
    private Double latitude;
    private Double longitude;
}