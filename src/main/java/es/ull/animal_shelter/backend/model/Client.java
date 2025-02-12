package es.ull.animal_shelter.backend.model;

import java.util.List;

import es.ull.animal_shelter.backend.controller.dto.RegisterClientRequest;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Document (collection = "clients")
public class Client extends User  {
	private String name;
	private String lastName;
	@DBRef
	private List<Animal> animalWL;

	public Client fromRegisterClientRequest(RegisterClientRequest request) {
		return Client.builder().name(request.getName())
				.lastName(request.getLastName())
				.username(request.getUsername())
				.password(request.getPassword())
				.email(request.getEmail())
				.animalWL(animalWL)
				.build();
	}

}
