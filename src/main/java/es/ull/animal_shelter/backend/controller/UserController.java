package es.ull.animal_shelter.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.User;
import es.ull.animal_shelter.backend.service.UserService;

public class UserController {

	@Autowired
    private UserService userService;

    @PostMapping("/login")
    public boolean logIn(@RequestParam String username, @RequestParam String password) {
        return userService.authenticate(username, password);
    }
    
    @PostMapping("/users")
	public User save(@RequestBody User user) {
    	userService.save(user);
		return user;
	}

    @GetMapping("/profile")
    public User viewProfile(@RequestParam String username) {
        return userService.findByUsername(username); 
    }
}
