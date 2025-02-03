package es.ull.animal_shelter.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ull.animal_shelter.backend.model.User;
import es.ull.animal_shelter.backend.service.UserService;


@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
    private UserService userService;

	@PostMapping("/sign")
    public boolean signUp(@RequestParam String username, @RequestParam String password) {
        return userService.authenticate(username, password);
    }
	
    @PostMapping("/login")
    public boolean logIn(@RequestParam String username, @RequestParam String password) {
        return userService.authenticate(username, password);
    }

}
