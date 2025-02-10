package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.repository.AnimalRepository;
import es.ull.animal_shelter.backend.service.AnimalService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    private Animal animal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        animal = new Animal();
        animal.setId("124");
        animal.setName("John");
        animal.setColor("negro");
        animal.setDescription(null);
        animal.setBirth_date("20-03-2020");
        animal.setEntryDate("07-07-2024");
        animal.setSex("Macho");
        animal.setAge(5);
        animal.setSterile(true);
        animal.setDisability(false);

        when(animalRepository.findById("124")).thenReturn(Optional.of(animal));
        when(animalRepository.findAll()).thenReturn(List.of(animal));
        when(animalRepository.save(any(Animal.class))).thenReturn(animal);
        doNothing().when(animalRepository).deleteById("124");
    }

    @Test
    void testFindAllAnimals() {
        List<Animal> animals = animalService.findAll();
        assertNotNull(animals);
        assertFalse(animals.isEmpty());
        assertEquals(1, animals.size());
    }

    @Test
    void testFindById() {
        Animal result = animalService.findById("124");
        assertNotNull(result);
        assertEquals("124", result.getId());
    }

    @Test
    void testDeleteById() {
        assertDoesNotThrow(() -> animalService.deleteById("124"));
        verify(animalRepository, times(1)).deleteById("124");
    }

    @Test
    void testUpdateAnimal() {
        Animal updatedAnimal = new Animal();
        updatedAnimal.setId("124");
        updatedAnimal.setName("John");

        Animal result = animalService.save(updatedAnimal);
        assertNotNull(result);
        assertEquals("John", result.getName());
    }
}
