package es.ull.animal_shelter.backend.service;

import java.util.Optional;

import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import es.ull.animal_shelter.backend.model.User;

@Service
public class UserService {

    @Autowired
    ClientRepository clientRepository;

    public boolean authenticate(String username, String password) {
    	Optional<Client> client = clientRepository.findByUsername(username);
        return client.isPresent() && client.get().getPassword().equals(password);
    }
    
    public Client save(Client client) {
		return clientRepository.save(client);
	}
    
    public Client findById(String id) {
        return clientRepository.findById(id)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
    }

    public Client findByUsername(String username) {
        return clientRepository.findByUsername(username)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: " + username));
    }

	public void deleteById(String id) {
        clientRepository.deleteById(id);
	}

}
