package es.ull.animal_shelter.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ull.animal_shelter.backend.service.UserService;

@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

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
