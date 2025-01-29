package es.ull.animal_shelter.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import es.ull.animal_shelter.backend.model.User;
import es.ull.animal_shelter.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
    	Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }
    
    public User save(User user) {
		return userRepository.save(user);	
	}

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: " + username));
    }

	
}
