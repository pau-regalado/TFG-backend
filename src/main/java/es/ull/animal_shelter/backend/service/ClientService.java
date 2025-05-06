package es.ull.animal_shelter.backend.service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import es.ull.animal_shelter.backend.controller.dto.AnimalLikes;
import es.ull.animal_shelter.backend.controller.dto.LoginRequest;
import es.ull.animal_shelter.backend.controller.dto.RegisterClientRequest;
import es.ull.animal_shelter.backend.repository.AnimalRepository;
import es.ull.animal_shelter.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.model.Animal;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AnimalRepository animalRepository;
    
    public Client save(Client client) {
		return clientRepository.save(client);	
	}
    
    public Client findByUsername(String username) {
        return clientRepository.findByUsername(username)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with username: " + username));
    }

    public Client findById(String id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with id: " + id));
    }
    
    public List<Client> findAll() {
        return clientRepository.findAll();
    }



    public Client login(LoginRequest loginRequest) {
        return clientRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    public Client register(RegisterClientRequest registerClientRequest) {
        Client client = new Client().fromRegisterClientRequest(registerClientRequest);
        return this.save(client);
    }

    public Client addAnimalToWishList(String clientId, String animalId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with ID: " + clientId));

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found with ID: " + animalId));

        if (!client.getAnimalWL().contains(animal)) {
            client.getAnimalWL().add(animal);
        }
        return clientRepository.save(client);
    }


    public List<Animal> viewAnimals(String clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with ID: " + clientId));
        return client.getAnimalWL();
    }

    public Animal deleteAnimalFromWishList(String clientId, String animalId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with ID: " + clientId));

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found with ID: " + animalId));

        client.getAnimalWL().remove(animal);
        clientRepository.save(client);
        return animal;
    }

    public List<Animal> getRecommendations(String clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        List<Animal> likedAnimals = client.getAnimalWL(); // Lista de animales a los que le dio "me gusta"

        if (likedAnimals.isEmpty()) {
            return Collections.emptyList(); // Si no ha dado "me gusta", no hay recomendaciones
        }

        //  Calcular características promedio
        Animal averageAnimal = calculateAverageAnimal(likedAnimals);

        //  Obtener todos los animales
        List<Animal> allAnimals = animalRepository.findAll();

        //  Calcular similitud y ordenar
        return allAnimals.stream()
                .filter(animal -> !likedAnimals.contains(animal)) // Excluir los que ya le gustan
                .sorted(Comparator.comparingDouble(a -> calculateSimilarity((Animal) a, averageAnimal)).reversed())
                .limit(5) // Devolver solo los 5 más parecidos
                .collect(Collectors.toList());
    }

    private Animal calculateAverageAnimal(List<Animal> animals) {
        int totalAge = 0;
        Map<String, Integer> colorCount = new HashMap<>();
        Map<String, Integer> sizeCount = new HashMap<>();
        Map<String, Integer> raceCount = new HashMap<>();

        for (Animal animal : animals) {
            totalAge += animal.getAge();

            colorCount.put(animal.getColor(), colorCount.getOrDefault(animal.getColor(), 0) + 1);
            sizeCount.put(animal.getSize(), sizeCount.getOrDefault(animal.getSize(), 0) + 1);
            raceCount.put(animal.getRace(), raceCount.getOrDefault(animal.getRace(), 0) + 1);
        }

        int averageAge = totalAge / animals.size();
        String mostCommonColor = getMostCommon(colorCount);
        String mostCommonSize = getMostCommon(sizeCount);
        String mostCommonRace = getMostCommon(raceCount);

        return new Animal(null, null, mostCommonColor, mostCommonSize, mostCommonRace, null, null, null, null, averageAge, null, null, null);
    }

    private String getMostCommon(Map<String, Integer> map) {
        return map.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private double calculateSimilarity(Animal a, Animal reference) {
        double ageSimilarity = 1.0 - (Math.abs(a.getAge() - reference.getAge()) / 10.0); // Normalizar edad
        double colorSimilarity = a.getColor().equals(reference.getColor()) ? 1.0 : 0.0;
        double sizeSimilarity = a.getSize().equals(reference.getSize()) ? 1.0 : 0.0;
        double raceSimilarity = a.getRace().equals(reference.getRace()) ? 1.0 : 0.0;

        return (ageSimilarity * 0.4) + (colorSimilarity * 0.2) + (sizeSimilarity * 0.2) + (raceSimilarity * 0.2);
    }

    public List<AnimalLikes> getAnimalLikes() {
        Map<String, Long> counts = clientRepository.findAll().stream()
                .filter(c -> c.getAnimalWL() != null)
                .flatMap(c -> c.getAnimalWL().stream())
                .map(Animal::getId)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // 3. Convertimos cada entrada (animalId, count) en un AnimalLikes
        return counts.entrySet().stream()
                .map(e -> AnimalLikes.builder()
                        .animalId(e.getKey())
                        .likes(e.getValue().intValue())
                        .build())
                .collect(Collectors.toList());
    }


}


