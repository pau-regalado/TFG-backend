package es.ull.animal_shelter.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalShelterValue {
    private String value;
    private Integer stars;
}
