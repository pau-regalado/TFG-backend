package es.ull.animal_shelter.backend.service;

import java.util.List;

import es.ull.animal_shelter.backend.controller.dto.LoginRequest;
import es.ull.animal_shelter.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.model.User;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    
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
    
    /*public List<Animal> giveLike(String id) {
    	
        Animal animal = animalRepository.findById(id);
        Client client = clientRepository.findById(id);

        if (!client.getAnimalWL().contains(animal)) {
        	client.getAnimalWL().add(animal);
        }

        clientRepository.save(client);

        return client.getAnimalWL();
    }*/


}


