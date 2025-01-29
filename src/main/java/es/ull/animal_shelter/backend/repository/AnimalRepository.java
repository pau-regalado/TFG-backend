package es.ull.animal_shelter.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.ull.animal_shelter.backend.model.Animal;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {
	
}
