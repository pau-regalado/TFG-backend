package es.ull.animal_shelter.backend.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterAnimalShelterRequest extends RegisterUserRequest {
    private String location;
    private Integer numberPhone;
    private String imageUrl;
    private Double latitude;
    private Double longitude;

}