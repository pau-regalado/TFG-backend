package es.ull.animal_shelter.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Chat {
    @Id
    private String id;
    @DBRef
    private Animal animal;
    @DBRef
    private AnimalShelter animalShelter;
    @DBRef
    private Client client;
    @DBRef
    private List<Message> messages;

}
