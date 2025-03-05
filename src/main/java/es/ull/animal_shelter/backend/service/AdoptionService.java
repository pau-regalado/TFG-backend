package es.ull.animal_shelter.backend.service;

import es.ull.animal_shelter.backend.controller.dto.AdoptionDetails;
import es.ull.animal_shelter.backend.model.*;
import es.ull.animal_shelter.backend.repository.AdoptionRepository;
import org.apache.logging.log4j.LogManager;
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
                .id(UUID.randomUUID().toString())
                .animal(animal)
                .client(client)
                .animalShelter(animalShelter)
                .date(LocalDateTime.now())
                .status(AdoptionStatus.PENDING)
                .build();
        LogManager.getLogger(this.getClass()).warn(adoption.toString());
        return adoptionRepository.save(adoption);
    }

    public Adoption confirmAdoptionRequest(String id) {
        Adoption adoption = this.findById(id);
        adoption.setStatus(AdoptionStatus.ACCEPTED); // El estado de la adopción comienza como PENDIENTE
        adoption.setDate(LocalDateTime.now()); // Fecha de la solicitud
        Adoption savedAdoption = adoptionRepository.save(adoption);
        animalShelterService.deleteByAnimalId(savedAdoption.getAnimal().getId());
        return savedAdoption;
    }

    public Adoption rejectAdoptionRequest(String id) {
        Adoption adoption = this.findById(id);
        adoption.setStatus(AdoptionStatus.REJECTED); // El estado de la adopción comienza como PENDIENTE
        adoption.setDate(LocalDateTime.now()); // Fecha de la solicitud
        return adoptionRepository.save(adoption);
    }

    public List<Adoption> findAll() {
        return adoptionRepository.findAll();
    }

    public Adoption findById(String id) {
        return adoptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Adoption no encontrado con ID: " + id));
    }

    public List<Adoption> findByAnimalShelterId(String id) {
        return adoptionRepository.findByAnimalShelterId(id);
    }
}
