package es.ull.animal_shelter.backend.repository;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.model.Chat;
import es.ull.animal_shelter.backend.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ChatRepository extends MongoRepository<Chat, String> {
    List<Chat> findChatByClient(Client client);
    List<Chat> findChatByAnimalShelterId(String animalShelterId);
    List<Chat> findChatByAnimalShelterIdAndClientId(String animalShelterId, String clientId);
    Optional<Chat> findChatByAnimalAndClientAndAnimalShelter(Animal animal, Client client, AnimalShelter animalShelter);
}
