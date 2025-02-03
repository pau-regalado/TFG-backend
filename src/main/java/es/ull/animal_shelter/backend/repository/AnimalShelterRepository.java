package es.ull.animal_shelter.backend.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import es.ull.animal_shelter.backend.model.AnimalShelter;

@Repository
public interface AnimalShelterRepository extends MongoRepository<AnimalShelter, String> {
	Optional<AnimalShelter> findByName(String name);
}
