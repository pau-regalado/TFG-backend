package es.ull.animal_shelter.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class AnimalShelter {
	@Id
	private String id;
	private String name;
	private String location;
	private Integer numberPhone;
	
}