package es.ull.animal_shelter.backend.service;

import java.util.List;

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
}


