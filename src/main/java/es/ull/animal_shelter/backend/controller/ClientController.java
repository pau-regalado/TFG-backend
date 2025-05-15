package es.ull.animal_shelter.backend.controller;

import java.util.List;

import es.ull.animal_shelter.backend.controller.dto.AnimalLikes;
import es.ull.animal_shelter.backend.controller.dto.LoginRequest;
import es.ull.animal_shelter.backend.controller.dto.RegisterClientRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.service.ClientService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

	private final ClientService clientService;

	@PostMapping
	public Client save(@RequestBody Client client) {
		clientService.save(client);
		return client;
	}
	
	@GetMapping
	public List<Client> findAll() {
		return clientService.findAll();
	}

	@PostMapping("/login")
	public Client login(@RequestBody LoginRequest loginRequest) {
		return clientService.login(loginRequest);
	}

	@PostMapping("/register")
	public Client register(@RequestBody RegisterClientRequest registerClientRequest) {
		return clientService.register(registerClientRequest);
	}

	@PostMapping("/{clientId}/wishList/{animalId}")
	public Client addAnimalToWishList(@PathVariable String clientId, @PathVariable String animalId) {
		return clientService.addAnimalToWishList(clientId, animalId);
	}

	@GetMapping("/{clientId}/viewWishList")
	public List<Animal> viewAnimals(@PathVariable String clientId) {
		return clientService.viewAnimals(clientId);
	}

	@DeleteMapping("/{clientId}/wishList/{animalId}")
	public Animal deleteAnimalFromWishList(@PathVariable String clientId, @PathVariable String animalId) {
		return clientService.deleteAnimalFromWishList(clientId, animalId);
	}

	@GetMapping("/{clientId}/recommendations")
	public List<Animal> getRecommendations(@PathVariable String clientId) {
		return clientService.getRecommendations(clientId);
	}

	@GetMapping("/likes")
	public List<AnimalLikes> getAnimalLikes() {
		return clientService.getAnimalLikes();
	}

}
