package es.ull.animal_shelter.backend.controller;

import es.ull.animal_shelter.backend.controller.dto.LoginRequest;
import es.ull.animal_shelter.backend.controller.dto.RegisterAnimalShelterRequest;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.service.AnimalShelterService;
import org.bson.types.Binary;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
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

    // --- Endpoint modificado para recibir multipart/form-data ---
    @PostMapping(value = "/{id}/addAnimal", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Animal addAnimal(
            @PathVariable String id,
            @RequestParam("name") String name,
            @RequestParam("color") String color,
            @RequestParam("size") String size,
            @RequestParam("race") String race,
            @RequestParam("description") String description,
            @RequestParam("birth_date") String birthDate,
            @RequestParam("entryDate") String entryDate,
            @RequestParam("sex") String sex,
            @RequestParam("age") Integer age,
            @RequestParam("sterile") Boolean sterile,
            @RequestParam("disability") Boolean disability,
            @RequestParam("image") MultipartFile image
    ) {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setColor(color);
        animal.setSize(size);
        animal.setRace(race);
        animal.setDescription(description);
        animal.setBirth_date(birthDate);
        animal.setEntryDate(entryDate);
        animal.setSex(sex);
        animal.setAge(age);
        animal.setSterile(sterile);
        animal.setDisability(disability);
        try {
            animal.setImage(new Binary(image.getBytes()));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error procesando la imagen", e);
        }
        return animalShelterService.addAnimal(id, animal);
    }
    // --- Fin endpoint modificado ---

    @DeleteMapping("/{shelterId}/animals/{animalId}")
    public ResponseEntity<AnimalShelter> deleteAnimal(@PathVariable String shelterId, @PathVariable String animalId) {
        return ResponseEntity.ok(animalShelterService.deleteAnimal(shelterId, animalId));
    }

    @PostMapping("/login")
    public AnimalShelter login(@RequestBody LoginRequest loginRequest) {
        return animalShelterService.login(loginRequest);
    }
}
