package es.ull.animal_shelter.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Adoption {
    @Id
    private String id;
    private Animal animal;
    private AnimalShelter animalShelter;
    private Client client;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private AdoptionStatus status;
    @Nullable
    private Value value;
}