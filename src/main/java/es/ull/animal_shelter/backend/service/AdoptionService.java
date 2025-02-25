package es.ull.animal_shelter.backend.service;

import es.ull.animal_shelter.backend.controller.dto.AdoptionDetails;
import es.ull.animal_shelter.backend.model.Adoption;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.repository.AdoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AdoptionService {
    @Autowired
    private AdoptionRepository adoptionRepository;
    @Autowired
    private AnimalService animalService;
    @Autowired
    private AnimalShelterService animalShelterService;
    @Autowired
    private ClientService clientService;

    public Adoption save(AdoptionDetails adoptionDetails) {
        Animal animal = animalService.findById(adoptionDetails.getAnimalId());
        AnimalShelter animalShelter = animalShelterService.findByAnimal(animal);
        Client client = clientService.findById(adoptionDetails.getClientId());
        Adoption adoption = Adoption.builder()
                .animal(animal).client(client).animalShelter(animalShelter)
                .build();
        adoption.setId(UUID.randomUUID().toString());
        adoption.setDate(LocalDateTime.now());
        Adoption savedAdoption = adoptionRepository.save(adoption);
        animalShelterService.deleteAnimal(animalShelter.getId(), animal.getId());
        return savedAdoption;
    }

    public List<Adoption> findAll() {
        return adoptionRepository.findAll();
    }

    public Adoption findById(String id) {
        return adoptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Adoption no encontrado con ID: " + id));
    }
}
