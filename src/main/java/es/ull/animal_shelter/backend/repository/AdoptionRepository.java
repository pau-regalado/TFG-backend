package es.ull.animal_shelter.backend.repository;

import es.ull.animal_shelter.backend.model.Adoption;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AdoptionRepository extends MongoRepository<Adoption, String> {
    Optional<Adoption> findAdoptionByAnimalIdAndClientId(String animalId, String clientId);
    List<Adoption> findByAnimalShelterId(String id);
}
