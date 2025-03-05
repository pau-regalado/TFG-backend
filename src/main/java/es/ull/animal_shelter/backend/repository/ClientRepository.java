package es.ull.animal_shelter.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import es.ull.animal_shelter.backend.model.Client;
import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByUsername(String username);
    Optional<Client> findByUsernameAndPassword(String username, String password);
}
