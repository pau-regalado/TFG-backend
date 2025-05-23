package es.ull.animal_shelter.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Value {
    @Nullable
    private ClientValue clientValue;
    @Nullable
    private AnimalShelterValue animalShelterValue;
}
