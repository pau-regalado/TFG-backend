package es.ull.animal_shelter.backend.model;


import es.ull.animal_shelter.backend.controller.dto.RegisterAnimalShelterRequest;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper=false)
@Document (collection = "animal-shelters")
public class AnimalShelter extends User {
	private String name;
	private String location;
	private Integer numberPhone;
	private Double latitude;
	private Double longitude;
	@DBRef
	private List<Animal> animalWL;
	private String imageUrl;


	public static AnimalShelter fromRegisterAnimalShelterRequest(RegisterAnimalShelterRequest request) {
		return AnimalShelter.builder().name(request.getName())
				.username(request.getUsername())
				.password(request.getPassword())
				.email(request.getEmail())
				.location(request.getLocation())
				.numberPhone(request.getNumberPhone())
				.animalWL(List.of())
				.imageUrl(request.getImageUrl())
				.longitude(request.getLongitude())
				.latitude(request.getLatitude())
				.build();
	}
}