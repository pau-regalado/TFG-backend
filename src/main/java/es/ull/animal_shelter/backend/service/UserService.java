package es.ull.animal_shelter.backend.service;

import java.util.Optional;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    ClientRepository clientRepository;

    public boolean authenticate(String username, String password) {
    	Optional<Client> client = clientRepository.findByUsername(username);
        return client.isPresent() && client.get().getPassword().equals(password);
    }

}
