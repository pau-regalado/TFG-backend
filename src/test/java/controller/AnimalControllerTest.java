/*package controller;

import es.ull.animal_shelter.backend.BackendApplication;
import es.ull.animal_shelter.backend.controller.AnimalController;
import es.ull.animal_shelter.backend.model.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
public class AnimalControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AnimalController animalController;

    @Test
    void testGetAllAnimals() {
        ResponseEntity<Animal[]> response = restTemplate.getForEntity("/api/v1/animals", Animal[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Animal[] animals = response.getBody();
        assertNotNull(animals);
        assertTrue(animals.length > 0);
    }

    @Test
    void testGetAnimalById() {
        // Se usa un animal presembrado: Brenda (id "128")
        ResponseEntity<Animal> response = restTemplate.getForEntity("/api/v1/animals/128", Animal.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Animal animal = response.getBody();
        assertNotNull(animal);
        assertEquals("Brenda", animal.getName());
    }

    @Test
    void testCreateAnimal() {
        Animal newAnimal = Animal.builder()
                .name("Test Animal")
                .color("Blue")
                .size("Small")
                .race("Test Race")
                .description("Test Description")
                .birth_date("01-01-2020")
                .entryDate("02-02-2021")
                .sex("Macho")
                .age(3)
                .sterile(false)
                .disability(false)
                .imageUrl("Max.jpg")
                .build();
        ResponseEntity<Animal> response = restTemplate.postForEntity("/api/v1/animals", newAnimal, Animal.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Animal savedAnimal = response.getBody();
        assertNotNull(savedAnimal);
        assertEquals("Test Animal", savedAnimal.getName());
        assertNotNull(savedAnimal.getId());
    }

    @Test
    void testDeleteAnimal() {
        // Se crea un animal nuevo para eliminar
        Animal newAnimal = Animal.builder()
                .name("Animal to Delete")
                .color("Green")
                .size("Medium")
                .race("Test Race")
                .description("To be deleted")
                .birth_date("01-01-2021")
                .entryDate("02-02-2022")
                .sex("Hembra")
                .age(2)
                .sterile(true)
                .disability(false)
                .imageUrl("Max.jpg")
                .build();
        Animal savedAnimal = animalController.save(newAnimal);

        // Se elimina el animal
        restTemplate.delete("/api/v1/animals/145");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            animalController.findById("145");
        });
        assertEquals("404 NOT_FOUND \"Animal no encontrado con ID: 145\"", exception.getMessage());
    }
}*/
