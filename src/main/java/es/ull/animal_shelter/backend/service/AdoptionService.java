package es.ull.animal_shelter.backend.service;

import es.ull.animal_shelter.backend.controller.dto.AdoptionDetails;
import es.ull.animal_shelter.backend.model.*;
import es.ull.animal_shelter.backend.repository.AdoptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdoptionService {
    private final AdoptionRepository adoptionRepository;
    private final AnimalService animalService;
    private final AnimalShelterService animalShelterService;
    private final ClientService clientService;

    public Adoption save(AdoptionDetails adoptionDetails) {
        boolean alreadyRequested = adoptionRepository.existsByClientIdAndAnimalIdAndStatus(
                adoptionDetails.getClientId(),
                adoptionDetails.getAnimalId(),
                AdoptionStatus.PENDING
        );
        if (alreadyRequested) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya has enviado una solicitud para este animal y está pendiente de aprobación."
            );
        }
        var animal = animalService.findById(adoptionDetails.getAnimalId());
        var shelter = animalShelterService.findByAnimal(animal);
        var client = clientService.findById(adoptionDetails.getClientId());
        var adoption = Adoption.builder()
                .id(UUID.randomUUID().toString())
                .animal(animal)
                .animalShelter(shelter)
                .client(client)
                .date(LocalDateTime.now())
                .status(AdoptionStatus.PENDING)
                .build();
        return adoptionRepository.save(adoption);
    }

    @Transactional
    public Adoption confirmAdoptionRequest(String id) {
        var adoption = findById(id);
        adoption.setStatus(AdoptionStatus.ACCEPTED);
        adoption.setDate(LocalDateTime.now());
        var saved = adoptionRepository.save(adoption);
        animalShelterService.deleteByAnimalId(saved.getAnimal().getId());
        var pending = adoptionRepository.findByAnimalAndStatus(saved.getAnimal(), AdoptionStatus.PENDING);
        pending.forEach(a -> a.setStatus(AdoptionStatus.REJECTED));
        adoptionRepository.saveAll(pending);
        return saved;
    }

    @Transactional
    public Adoption rejectAdoptionRequest(String id) {
        var adoption = findById(id);
        adoption.setStatus(AdoptionStatus.REJECTED);
        adoption.setDate(LocalDateTime.now());
        return adoptionRepository.save(adoption);
    }

    public List<Adoption> findAll() {
        return adoptionRepository.findAll();
    }

    public Adoption findById(String id) {
        return adoptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Adoption no encontrado con ID: " + id
                ));
    }

    public List<Adoption> findByAnimalShelterId(String id) {
        return adoptionRepository.findByAnimalShelterId(id);
    }

    public List<Adoption> findByClientId(String id) {
        return adoptionRepository.findByClientId(id);
    }

    @Transactional
    public Adoption updateClientEvaluation(String id, ClientValue value) {
        Adoption adoption = findById(id);
        adoption.getValue().setClientValue(value);
        return adoptionRepository.save(adoption);
    }


    @Transactional
    public Adoption updateAnimalShelterEvaluation(String id, AnimalShelterValue value) {
        Adoption adoption = findById(id);
        adoption.getValue().setAnimalShelterValue(value);
        return adoptionRepository.save(adoption);
    }

}