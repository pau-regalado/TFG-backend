package es.ull.animal_shelter.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Document
public class Recommended {
    private String name;
    private String location;
    private Integer numberPhone;
    @DBRef
    private List<Animal> animalWL;
}
