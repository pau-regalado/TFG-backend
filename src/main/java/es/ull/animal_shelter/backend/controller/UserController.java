package es.ull.animal_shelter.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.ull.animal_shelter.backend.model.User;
import es.ull.animal_shelter.backend.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
    private UserService userService;

    @PostMapping("/login")
    public boolean logIn(@RequestParam String username, @RequestParam String password) {
        return userService.authenticate(username, password);
    }
    
    @PostMapping
	public User save(@RequestBody User user) {
    	userService.save(user);
		return user;
	}

    @GetMapping("/profile")
    public User viewProfile(@RequestParam String username) {
        return userService.findByUsername(username); 
    }
    
    @GetMapping("/{id}")
	public User findByUsername(@PathVariable String id) {
		return userService.findByUsername(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable String id) {
		userService.deleteById(id);
	}
	
	@PutMapping
	public void update(@RequestBody User user) {
		userService.save(user);
	}
}
