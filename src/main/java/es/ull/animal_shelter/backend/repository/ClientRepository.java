package es.ull.animal_shelter.backend.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.ull.animal_shelter.backend.model.Client;

public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByName(String client);
}
