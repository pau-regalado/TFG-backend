package es.ull.animal_shelter.backend.repository;

import es.ull.animal_shelter.backend.model.Adoption;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Adoption, String> {
}
