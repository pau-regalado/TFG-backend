package es.ull.animal_shelter.backend.repository;

import es.ull.animal_shelter.backend.model.Value;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ValueRepository extends MongoRepository<Value, String>  {
    Value findByAdoptionId(String id);
}
