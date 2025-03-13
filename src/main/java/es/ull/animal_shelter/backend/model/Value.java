package es.ull.animal_shelter.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Value {
    private Integer stars;
    private String valueClient;
    private String valueAnimalShelter;
    private Adoption adoption;
}
