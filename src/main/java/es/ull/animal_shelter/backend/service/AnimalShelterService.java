package es.ull.animal_shelter.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.repository.AnimalShelterRepository;

@Service 
public class AnimalShelterService {

    @Autowired
    private AnimalShelterRepository animalShelterRepository;
    
    public void save(AnimalShelter shelter) {
        animalShelterRepository.save(shelter);
    }
}
