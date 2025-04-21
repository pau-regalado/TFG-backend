package es.ull.animal_shelter.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Value {
    private Integer starsClient;
    private Integer starsAnimalShelter;
    private String valueClient;
    private String valueAnimalShelter;
}
