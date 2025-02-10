package es.ull.animal_shelter.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.ull.animal_shelter.backend.model.AnimalShelter;

import java.util.Optional;

public interface AnimalShelterRepository extends MongoRepository<AnimalShelter, String> {
	Optional<AnimalShelter> findByName(String name);

    Optional<AnimalShelter> findByUsernameAndPassword(String username, String password);
}
