package service;

import es.ull.animal_shelter.backend.BackendApplication;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.service.AnimalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
class AnimalServiceTest {

    @Autowired
    private AnimalService animalService;

    @Test
    void testSaveAnimal() {
        Animal animal = Animal.builder()
                .id("128")
                .name("Brenda")
                .color("marrón y blanco")
                .size("mediano")
                .race("bull terrier")
                .description("Muy carinosa")
                .birth_date("02-02-2020")
                .entryDate("17-10-2024")
                .sex("Hembra")
                .age(5)
                .sterile(true)
                .disability(false)
                .imageUrl("Brenda,jpg")
                .build();

        Animal savedAnimal = animalService.save(animal);
        assertNotNull(savedAnimal);
        assertEquals("mediano", savedAnimal.getSize());
    }

    @Test
    void testFindAllAnimals() {
        List<Animal> animals = animalService.findAll();
        assertNotNull(animals);
        assertFalse(animals.isEmpty());
        assertEquals(22, animals.size());
    }

    @Test
    void testFindById() {
        Animal animal = animalService.findById("139");
        assertNotNull(animal);
        assertEquals("139", animal.getId());
        assertEquals("Kai", animal.getName());

        assertThrows(ResponseStatusException.class, () -> {
            animalService.findById("0"); // This should throw 404
        });
    }

    @Test
    void testDeleteById() {
        // First, make sure the animal exists
        Animal animal = animalService.findById("139");
        assertNotNull(animal);
        assertEquals("139", animal.getId());
        assertEquals("Kai", animal.getName());

        // Delete the animal
        animalService.deleteById("139");

        // Now, trying to find it should throw a 404 NOT FOUND exception
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            animalService.findById("139");
        });

        // Assert the reason (error message)
        assertEquals("Animal no encontrado con ID: 139", exception.getReason());
    }



}
