package es.ull.animal_shelter.backend.model;

import java.util.List;

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
//@Document
public class Client extends User {
	@Id
	private String id;
	private String name;
	private String location;
	//@DBRef
	//private List<Animal> animalWL;

	/*public Client(String id, String name, String username, String password, String location, List<Animal> wishList) {
        super(id, name, username, password, UserRole.CLIENT);
        this.location = location;
        this.wishList = wishList;
    }*/
}