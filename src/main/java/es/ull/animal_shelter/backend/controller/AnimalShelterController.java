package es.ull.animal_shelter.backend.controller;

import es.ull.animal_shelter.backend.controller.dto.LoginRequest;
import es.ull.animal_shelter.backend.controller.dto.RegisterAnimalShelterRequest;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.service.AnimalShelterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/animal-shelters")
public class AnimalShelterController {

    private final AnimalShelterService animalShelterService;

    @PostMapping
    public AnimalShelter save(@RequestBody AnimalShelter animalShelter) {
        animalShelterService.save(animalShelter);
        return animalShelter;
    }

    @PostMapping("/register")
    public AnimalShelter register(@RequestBody RegisterAnimalShelterRequest registerAnimalShelterRequest) {
        return animalShelterService.register(registerAnimalShelterRequest);
    }

    @GetMapping
    public List<AnimalShelter> findAll() {
        return animalShelterService.findAll();
    }

    @GetMapping("/{id}")
    public AnimalShelter findById(@PathVariable String id) {
        return animalShelterService.findById(id);
    }

    // --- Ahora se recibe un JSON en lugar de multipart/form-data ---
    @PostMapping("/{id}/addAnimal")
    public Animal addAnimal(@PathVariable String id, @RequestBody Animal animal) {
        return animalShelterService.addAnimal(id, animal);
    }
    // --- Fin de la modificación ---

    @DeleteMapping("/{shelterId}/animals/{animalId}")
    public ResponseEntity<AnimalShelter> deleteAnimal(@PathVariable String shelterId, @PathVariable String animalId) {
        return ResponseEntity.ok(animalShelterService.deleteAnimal(shelterId, animalId));
    }

    @PostMapping("/login")
    public AnimalShelter login(@RequestBody LoginRequest loginRequest) {
        return animalShelterService.login(loginRequest);
    }
}
