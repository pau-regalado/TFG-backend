package es.ull.animal_shelter.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	/*@GetMapping("/{id}")
	public Client findByUsername(@PathVariable String id) {
		return clientService.findByUsername(id);
	}*/
	
	@GetMapping
	public List<Client> findAll() {
		clientService.findAll().stream().map(a -> a)
		.forEach(a -> System.out.print(a.toString()));
		return clientService.findAll();
	}
	
	/*public List<Animal> giveLike(String id) {
		return clientService.giveLike(id);
	}*/

}
