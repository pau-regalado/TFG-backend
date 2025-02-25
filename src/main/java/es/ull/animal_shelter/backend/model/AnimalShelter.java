package es.ull.animal_shelter.backend.model;


import es.ull.animal_shelter.backend.controller.dto.RegisterAnimalShelterRequest;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Document (collection = "animal-shelters")
public class AnimalShelter extends User {
	private String name;
	private String location;
	private Integer numberPhone;
	@DBRef
	private List<Animal> animalWL;


	public AnimalShelter fromRegisterAnimalShelterRequest(RegisterAnimalShelterRequest request) {
		return AnimalShelter.builder().name(request.getName())
				.username(request.getUsername())
				.password(request.getPassword())
				.email(request.getEmail())
				.location(request.getLocation())
				.numberPhone(request.getNumberPhone())
				.animalWL(List.of())
				.build();
	}
}