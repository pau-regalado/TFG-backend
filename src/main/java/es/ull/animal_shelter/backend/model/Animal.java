package es.ull.animal_shelter.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
public class Animal {
	@Id
	private String id;
	private String name;
	private String color;
	private String size;
	private String race;
	private String description;
	private String birth_date;
	private String entryDate;
	private String sex;
	private Integer age;
	private Boolean sterile;
	private Boolean disability;
}
