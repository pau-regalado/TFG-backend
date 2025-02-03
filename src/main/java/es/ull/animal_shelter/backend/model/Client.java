package es.ull.animal_shelter.backend.model;

import java.util.List;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document (collection = "clients")
public class Client extends User  {
	private String name;
	private String lastName;
	@DBRef
	private List<Animal> animalWL;

}
