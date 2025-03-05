package es.ull.animal_shelter.backend.service;

import es.ull.animal_shelter.backend.controller.dto.LoginRequest;
import es.ull.animal_shelter.backend.controller.dto.RegisterAnimalShelterRequest;
import es.ull.animal_shelter.backend.controller.dto.RegisterClientRequest;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.repository.AnimalShelterRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service 
public class AnimalShelterService {

    @Autowired
    private AnimalShelterRepository animalShelterRepository;

    @Autowired
    private AnimalService animalService;
    
    public void save(AnimalShelter shelter) {
        animalShelterRepository.save(shelter);
    }

    public AnimalShelter findByName(String name) {
        return animalShelterRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal Shelter not found with name: " + name));
    }

    public List<AnimalShelter> findAll() {
        return animalShelterRepository.findAll();
    }

    public AnimalShelter login(LoginRequest loginRequest) {
        return animalShelterRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal Shelter not found"));
    }

    public AnimalShelter findById(String idAnimalShelter) {
        return animalShelterRepository.findById(idAnimalShelter)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal Shelter not found"));
    }

    public Animal addAnimal(String id, Animal animalData) {
        Animal animal = this.animalService.save(animalData);
        AnimalShelter animalShelter = findById(id);
        animalShelter.getAnimalWL().add(animal);
        this.animalShelterRepository.save(animalShelter);
        return animal;
    }

    public AnimalShelter deleteAnimal(String shelterId, String animalId) {
        AnimalShelter animalShelter = findById(shelterId);  // Obtener refugio
        animalShelter.getAnimalWL().removeIf(animal -> animal.getId().equals(animalId));  // Eliminar el animal
        AnimalShelter animalShelterUpdate = animalShelterRepository.save(animalShelter);
        this.animalService.deleteById(animalId);
        return animalShelterUpdate;
    }

    public AnimalShelter deleteByAnimalId(String animalId) {
        AnimalShelter animalShelter = this.findByAnimalId(animalId);  // Obtener refugio
        animalShelter.getAnimalWL().removeIf(animal -> animal.getId().equals(animalId));  // Eliminar el animal
        AnimalShelter animalShelterUpdate = animalShelterRepository.save(animalShelter);
        this.animalService.deleteById(animalId);
        return animalShelterUpdate;
    }

    public AnimalShelter findByAnimal(Animal animal) {
        return animalShelterRepository.findAll().stream()
                .filter(shelter -> shelter.getAnimalWL().contains(animal)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal Shelter not found"));
    }

    public AnimalShelter findByAnimalId(String animalId) {
        return animalShelterRepository.findAll().stream()
                .filter(shelter -> shelter.getAnimalWL().stream()
                        .anyMatch(animal -> animal.getId().equals(animalId)))  // Filtrar por animalId
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal Shelter not found"));
    }

    public AnimalShelter register(RegisterAnimalShelterRequest registerAnimalShelterRequest) {
        AnimalShelter animalShelter = new AnimalShelter().fromRegisterAnimalShelterRequest(registerAnimalShelterRequest);
        return animalShelterRepository.save(animalShelter);
    }
}
