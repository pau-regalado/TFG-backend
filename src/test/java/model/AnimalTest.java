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
        animal.setId("123");
        animal.setName("John");
        animal.setColor("negro");
        animal.setDescription(null);

        when(animalRepository.findById("123")).thenReturn(Optional.of(animal));
        when(animalRepository.findAll()).thenReturn(List.of(animal));
        when(animalRepository.save(any(Animal.class))).thenReturn(animal);
        doNothing().when(animalRepository).deleteById("123");
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
        Animal result = animalService.findById("123");
        assertNotNull(result);
        assertEquals("123", result.getId());
    }

    @Test
    void testDeleteById() {
        assertDoesNotThrow(() -> animalService.deleteById("123"));
        verify(animalRepository, times(1)).deleteById("123");
    }

    @Test
    void testUpdateAnimal() {
        Animal updatedAnimal = new Animal();
        updatedAnimal.setId("123");
        updatedAnimal.setName("John");

        Animal result = animalService.save(updatedAnimal);
        assertNotNull(result);
        assertEquals("John", result.getName());
    }
}
