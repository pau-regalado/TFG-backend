package es.ull.animal_shelter.backend.model;

import lombok.Builder;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
//@Builder
@Data
public class User {
	private String id;
	private String username;
	private String password;
	private String email;
}

