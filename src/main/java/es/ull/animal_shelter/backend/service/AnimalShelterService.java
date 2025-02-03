package es.ull.animal_shelter.backend.service;

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

}
