package es.ull.animal_shelter.backend.model;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Document (collection = "animal-shelters")
public class AnimalShelter extends User {
	private String name;
	private String location;
	private Integer numberPhone;
}