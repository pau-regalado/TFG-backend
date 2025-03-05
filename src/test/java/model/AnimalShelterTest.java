package model;

import es.ull.animal_shelter.backend.controller.dto.RegisterAnimalShelterRequest;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class AnimalShelterTest {
    private AnimalShelter shelter1;
    private AnimalShelter shelter2;

    @BeforeEach
    void setUp() {
        // Creando instancias de AnimalShelter
        shelter1 = AnimalShelter.builder()
                .id("A001")
                .username("shelterUser1")
                .password("password1")
                .email("shelter1@example.com")
                .name("Rescue Center 1")
                .location("Santa Cruz")
                .numberPhone(123456789)
                .animalWL(List.of()) // Lista vacía de animales
                .build();

        shelter2 = AnimalShelter.builder()
                .id("A002")
                .username("shelterUser2")
                .password("password2")
                .email("shelter2@example.com")
                .name("Rescue Center 2")
                .location("La Laguna")
                .numberPhone(987654321)
                .animalWL(List.of()) // Lista vacía de animales
                .build();
    }

    @Test
    void testGettersAndSetters() {
        assertEquals("A001", shelter1.getId());
        assertEquals("shelterUser1", shelter1.getUsername());
        assertEquals("password1", shelter1.getPassword());
        assertEquals("shelter1@example.com", shelter1.getEmail());
        assertEquals("Rescue Center 1", shelter1.getName());
        assertEquals("Santa Cruz", shelter1.getLocation());
        assertEquals(123456789, shelter1.getNumberPhone());
        assertTrue(shelter1.getAnimalWL().isEmpty());
    }

    /*@Test
    void testToString() {
        // Obtener la representación en cadena del primer shelter
        String animalShelter1ToString = shelter1.toString();

        // Verificar que los valores están presentes en la cadena
        assertTrue(animalShelter1ToString.contains("username=shelterUser1"));
        assertTrue(animalShelter1ToString.contains("email=shelter1@example.com"));
        assertTrue(animalShelter1ToString.contains("location=Santa Cruz"));
        assertTrue(animalShelter1ToString.contains("numberPhone=123456789"));
        assertTrue(animalShelter1ToString.contains("name=Rescue Center 1"));

        // Obtener la representación en cadena del segundo shelter
        String animalShelter2ToString = shelter2.toString();

        // Verificar que los valores están presentes en la cadena
        assertTrue(animalShelter2ToString.contains("username=shelterUser2"));
        assertTrue(animalShelter2ToString.contains("email=shelter2@example.com"));
        assertTrue(animalShelter2ToString.contains("location=La Laguna"));
        assertTrue(animalShelter2ToString.contains("numberPhone=987654321"));
        assertTrue(animalShelter2ToString.contains("name=Rescue Center 2"));
    }*/

    @Test
    void testEqualsAndHashCode() {
        AnimalShelter sameShelter = AnimalShelter.builder()
                .id("A001")
                .username("shelterUser1")
                .password("password1")
                .email("shelter1@example.com")
                .name("Rescue Center 1")
                .location("Santa Cruz")
                .numberPhone(123456789)
                .animalWL(List.of())
                .build();

        assertEquals(shelter1, sameShelter);
        assertEquals(shelter1.hashCode(), sameShelter.hashCode());

        assertNotEquals(shelter1, shelter2);
        assertNotEquals(shelter1.hashCode(), shelter2.hashCode());
    }

    /*@Test
    void testFromRegisterAnimalShelterRequest() {
        RegisterAnimalShelterRequest request = new RegisterAnimalShelterRequest(
                "New Location", 123123123
        );

        AnimalShelter newShelter = shelter1.fromRegisterAnimalShelterRequest(request);

        assertEquals("New Shelter", newShelter.getName());
        assertEquals("newUser", newShelter.getUsername());
        assertEquals("newPassword", newShelter.getPassword());
        assertEquals("newEmail@example.com", newShelter.getEmail());
        assertEquals("New Location", newShelter.getLocation());
        assertEquals(123123123, newShelter.getNumberPhone());
        assertTrue(newShelter.getAnimalWL().isEmpty());
    }*/
}
