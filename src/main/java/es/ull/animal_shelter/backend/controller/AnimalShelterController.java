package es.ull.animal_shelter.backend.controller;

import es.ull.animal_shelter.backend.controller.dto.LoginRequest;
import es.ull.animal_shelter.backend.model.Animal;
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

    @GetMapping
    public List<AnimalShelter> findAll() {
        animalShelterService.findAll().stream().map(a -> a)
                .forEach(a -> System.out.print(a.toString()));
        return animalShelterService.findAll();
    }

    @GetMapping("/{id}")
    public AnimalShelter findById(@PathVariable String id) {
        return animalShelterService.findById(id);
    }

    @PostMapping("/{id}/addAnimal")
    public Animal addAnimal(@PathVariable String id, @RequestBody Animal animalData) {
        return animalShelterService.addAnimal(id, animalData);
    }

    @PostMapping("/login")
    public AnimalShelter login(@RequestBody LoginRequest loginRequest) {
        return animalShelterService.login(loginRequest);
    }

}
