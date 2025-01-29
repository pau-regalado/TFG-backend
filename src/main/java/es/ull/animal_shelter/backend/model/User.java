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
@Document
@Data
public class User {
	@Id
	private String name;
	private String username;
	private String password;
	public String getPassword() {
		return password;
	}

}

