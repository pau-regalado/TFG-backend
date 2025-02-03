package es.ull.animal_shelter.backend.controller;

import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.service.AnimalShelterService;
import es.ull.animal_shelter.backend.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animal-shelters")
public class AnimalShelterController {

    private final AnimalShelterService animalShelterService;

    public AnimalShelterController(AnimalShelterService animalShelterService) {
        this.animalShelterService = animalShelterService;
    }

    @PostMapping
    public AnimalShelter save(@RequestBody AnimalShelter animalShelter) {
        animalShelterService.save(animalShelter);
        return animalShelter;
    }

	/*@GetMapping("/{id}")
	public Client findByUsername(@PathVariable String id) {
		return clientService.findByUsername(id);
	}*/

    @GetMapping
    public List<AnimalShelter> findAll() {
        animalShelterService.findAll().stream().map(a -> a)
                .forEach(a -> System.out.print(a.toString()));
        return animalShelterService.findAll();
    }

}
