package es.ull.animal_shelter.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.model.User;
import es.ull.animal_shelter.backend.repository.ClientRepository;
import es.ull.animal_shelter.backend.repository.UserRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public boolean authenticate(String username, String password) {
    	Optional<Client> user = clientRepository.findByName(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }
    
    public Client save(Client client) {
		return clientRepository.save(client);	
	}
    
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findByName(String username) {
        return clientRepository.findByName(username)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: " + username));
    }

	public void deleteById(String id) {
		clientRepository.deleteById(id);
		
	}

}


