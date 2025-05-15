package model;

import es.ull.animal_shelter.backend.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdoptionTest {

    private AnimalShelter animalShelter1;
    private Client client1;
    private Animal animal1;
    private Adoption adoption1;

    @BeforeEach
    void setUp() {
        // Inicializamos los objetos necesarios
        animalShelter1 = AnimalShelter.builder()
                .username("shelterUser1")
                .password("password1")
                .email("shelter1@example.com")
                .name("Rescue Center 1")
                .location("Santa Cruz")
                .numberPhone(123456789)
                .animalWL(List.of()) // Lista vacía de animales
                .build();

        client1 = Client.builder()
                .username("client1")
                .password("clientPassword")
                .email("client1@example.com")
                .name("Carlos")
                .lastName("Perez")
                .animalWL(List.of()) // Lista vacía de animales adoptados
                .build();

        animal1 = Animal.builder()
                .id("A001")
                .name("Lola")
                .color("marrón")
                .size("mediano")
                .race("mestizo")
                .description("Muy cariñosa")
                .birth_date("02-02-2020")
                .entryDate("17-10-2024")
                .sex("Hembra")
                .age(5)
                .sterile(true)
                .disability(false)
                .build();

        // Creamos una adopción
        adoption1 = Adoption.builder()
                .id("AD001")
                .animal(animal1)
                .animalShelter(animalShelter1)
                .client(client1)
                .date(LocalDateTime.of(2025, 3, 5, 14, 30))
                .status(AdoptionStatus.PENDING)
                .build();
    }



    @Test
    void testSuperBuilder() {
        // Verificación de los valores del Builder
        assertEquals("AD001", adoption1.getId());
        assertEquals(animal1, adoption1.getAnimal());
        assertEquals(animalShelter1, adoption1.getAnimalShelter());
        assertEquals(client1, adoption1.getClient());
        assertEquals(LocalDateTime.of(2025, 3, 5, 14, 30), adoption1.getDate());
        assertEquals(AdoptionStatus.PENDING, adoption1.getStatus());
    }

    @Test
    void testToString() {
        // Obtener la representación en cadena de la adopción
        String adoptionToString = adoption1.toString();

        // Verificar que los valores están presentes en la cadena (ajustando la fecha y los campos de los objetos)
        assertTrue(adoptionToString.contains("id=AD001"));
        assertTrue(adoptionToString.contains("client=Client(name=Carlos, lastName=Perez, animalWL=[])"));
        assertTrue(adoptionToString.contains("date=2025-03-05T14:30"));  // Asegúrate de que el formato de la fecha es el mismo
        assertTrue(adoptionToString.contains("status=PENDING"));
    }




    @Test
    void testEqualsAndHashCode() {
        // Crear otro objeto con los mismos valores
        Adoption sameAdoption = Adoption.builder()
                .id("AD001")
                .animal(animal1)
                .animalShelter(animalShelter1)
                .client(client1)
                .date(LocalDateTime.of(2025, 3, 5, 14, 30))
                .status(AdoptionStatus.PENDING)
                .build();

        // Verificar que ambos objetos son iguales
        assertEquals(adoption1, sameAdoption);
        assertEquals(adoption1.hashCode(), sameAdoption.hashCode());

        // Crear otro objeto con valores diferentes
        Adoption differentAdoption = Adoption.builder()
                .id("AD002")
                .animal(animal1)
                .animalShelter(animalShelter1)
                .client(client1)
                .date(LocalDateTime.of(2025, 3, 5, 14, 30))
                .status(AdoptionStatus.ACCEPTED)
                .build();

        // Verificar que los objetos con valores diferentes no son iguales
        assertNotEquals(adoption1, differentAdoption);
        assertNotEquals(adoption1.hashCode(), differentAdoption.hashCode());
    }

    @Test
    void testDateFormatting() {
        // Verificar que la fecha esté correctamente formateada
        assertEquals("2025-03-05 14:30:00", adoption1.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
