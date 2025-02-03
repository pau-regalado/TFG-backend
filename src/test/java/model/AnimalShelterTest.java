package model;

import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.repository.AnimalShelterRepository;
import es.ull.animal_shelter.backend.service.AnimalShelterService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AnimalShelterTest {

    @Mock
    private AnimalShelterRepository clientRepository;

    @InjectMocks
    private AnimalShelterService clientService;

    private AnimalShelter animalShelter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        animalShelter = new AnimalShelter();
        animalShelter.setName("Rescue");
        animalShelter.setLocation("La Laguna");
        animalShelter.setNumberPhone(678901234);
    }
}
