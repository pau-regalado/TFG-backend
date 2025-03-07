package es.ull.animal_shelter.backend.controller;

import java.util.List;

import es.ull.animal_shelter.backend.controller.dto.LoginRequest;
import es.ull.animal_shelter.backend.controller.dto.RegisterClientRequest;
import org.springframework.web.bind.annotation.*;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.service.ClientService;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping
	public Client save(@RequestBody Client client) {
		clientService.save(client);
		return client;
	}
	
	@GetMapping
	public List<Client> findAll() {
		clientService.findAll().stream().map(a -> a)
		.forEach(a -> System.out.print(a.toString()));
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

}
