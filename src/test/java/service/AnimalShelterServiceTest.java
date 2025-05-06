package service;

import es.ull.animal_shelter.backend.BackendApplication;
import es.ull.animal_shelter.backend.controller.dto.LoginRequest;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.service.AnimalService;
import es.ull.animal_shelter.backend.service.AnimalShelterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
class AnimalShelterServiceTest {

    @Autowired
    private AnimalShelterService animalShelterService;

    @Autowired
    private AnimalService animalService;

    @Test
    void testFindAllAnimalShelters() {
        List<AnimalShelter> animalShelters = animalShelterService.findAll();
        assertNotNull(animalShelters);
        assertFalse(animalShelters.isEmpty());
        assertEquals("Rescue1", animalShelters.get(0).getName());
    }

    @Test
    void testSaveAnimalShelter() {
        AnimalShelter shelter = AnimalShelter.builder()
                .id("s1")
                .name("Happy Paws")
                .username("happypaws")
                .password("securepass")
                .animalWL(Collections.emptyList())
                .build();

        AnimalShelter savedShelter = animalShelterService.save(shelter);
        assertNotNull(savedShelter);
        assertEquals("happypaws", savedShelter.getUsername());
    }

    @Test
    void testFindByName() {
        AnimalShelter shelter = AnimalShelter.builder()
                .id("s2")
                .name("Safe Haven")
                .username("safehaven")
                .password("password123")
                .animalWL(Collections.emptyList())
                .build();

        animalShelterService.save(shelter);

        AnimalShelter foundShelter = animalShelterService.findByName("Safe Haven");
        assertNotNull(foundShelter);
        assertEquals("Safe Haven", foundShelter.getName());
    }

    @Test
    void testFindById() {
        AnimalShelter shelter = AnimalShelter.builder()
                .id("s3")
                .name("Animal Love")
                .username("animallove")
                .password("pass")
                .animalWL(Collections.emptyList())
                .build();

        animalShelterService.save(shelter);

        AnimalShelter foundShelter = animalShelterService.findById("s3");
        assertNotNull(foundShelter);
        assertEquals("animallove", foundShelter.getUsername());
    }

    @Test
    void testLogin() {
        AnimalShelter shelter = AnimalShelter.builder()
                .id("s4")
                .name("Home for Pets")
                .username("homepets")
                .password("mypassword")
                .animalWL(Collections.emptyList())
                .build();

        animalShelterService.save(shelter);

        LoginRequest loginRequest = new LoginRequest("homepets", "mypassword");
        AnimalShelter loggedShelter = animalShelterService.login(loginRequest);
        assertNotNull(loggedShelter);
        assertEquals("homepets", loggedShelter.getUsername());
    }

    @Test
    void testAddAnimal() {
        AnimalShelter shelter = AnimalShelter.builder()
                .id("s5")
                .name("Rescue Center")
                .username("rescuecenter")
                .password("12345")
                .animalWL(Collections.emptyList())
                .build();
        animalShelterService.save(shelter);

        Animal animal = new Animal();
        animal.setId("a1");
        animal.setName("Luna");
        animal.setColor("Black");
        animal.setSize("Medium");
        animal.setRace("Dog");
        animal.setDescription("Friendly dog");
        animal.setBirth_date("01-01-2022");
        animal.setEntryDate("02-02-2023");
        animal.setSex("Female");
        animal.setAge(2);
        animal.setSterile(true);
        animal.setDisability(false);
        animal.setImageUrl("http://example.com/luna.jpg");

        Animal addedAnimal = animalShelterService.addAnimal("s5", animal);
        assertNotNull(addedAnimal);
        assertEquals("Luna", addedAnimal.getName());
    }

    @Test
    void testFindByAnimalId() {
        AnimalShelter shelter = AnimalShelter.builder()
                .id("s1")
                .name("Test Shelter")
                .username("testuser")
                .password("testpass")
                .animalWL(Collections.emptyList())
                .build();

        animalShelterService.save(shelter);  // Ensure the shelter is saved

        // Now try to find the shelter by ID
        AnimalShelter foundShelter = animalShelterService.findById("s1");
        assertNotNull(foundShelter);
        assertEquals("Test Shelter", foundShelter.getName());

        // Check if the correct 404 exception is thrown for nonexistent shelter
        assertThrows(ResponseStatusException.class, () -> {
            animalShelterService.findById("nonexistent-id");
        });
    }

    @Test
    void testAnimalShelterNotFound() {
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            animalShelterService.findById("nonexistent");
        });
        assertTrue(exception.getMessage().contains("Animal Shelter with ID not found"));
    }
}