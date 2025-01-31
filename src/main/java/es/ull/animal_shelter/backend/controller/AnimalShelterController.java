package es.ull.animal_shelter.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.service.AnimalService;
import es.ull.animal_shelter.backend.service.AnimalShelterService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/animalshelters")
public class AnimalShelterController {
	
    @Autowired
    private AnimalShelterService animalShelterService;

    @PostMapping("/{idAS}/animals")
    public Animal addProfile(@PathVariable String shelterId, @RequestBody Animal animal) {
        animalService.save(animal);
        return animal;
    }

    @DeleteMapping("/{idAS}/animals/{idAnimal}")
    public void deleteProfile(@PathVariable String shelterId, @PathVariable String animalId) {
        animalService.deleteById(animalId);
    }

    @PostMapping("/{idAS}/adoptions/accept/{idAnimal}")
    public String acceptAdoption(@PathVariable String shelterId, @PathVariable String animalId) {
        Animal animal = animalService.findById(animalId);
        if (animal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal no encontrado");
        }
        return "Adopción aceptada para el animal con ID: " + animalId;
    }

    @PostMapping("/{idAS}/adoptions/decline/{idAnimal}")
    public String declineAdoption(@PathVariable String shelterId, @PathVariable String animalId) {
        Animal animal = animalService.findById(animalId);
        if (animal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal no encontrado");
        }
        return "Adopción rechazada para el animal con ID: " + animalId;
    }

    @PostMapping("/{idAS}/clients/{idClient}/rate")
    public String valueClient(@PathVariable String shelterId, @PathVariable String idClient, @RequestParam int rating) {
        return "Cliente con ID " + idClient + " ha sido valorado con " + rating + " estrellas.";
    }
}
