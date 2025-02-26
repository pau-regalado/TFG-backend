package es.ull.animal_shelter.backend.repository;

import es.ull.animal_shelter.backend.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
