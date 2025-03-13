package model;

import es.ull.animal_shelter.backend.model.Adoption;
import es.ull.animal_shelter.backend.model.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueTest {
    private Value value1;
    private Value value2;
    private Adoption dummyAdoption;

    @BeforeEach
    void setUp() {
        // Instancia de adopción dummy (se asume que la clase Adoption tiene un builder)
        dummyAdoption = Adoption.builder()
                .id("ad1")
                .build();

        // Instancias de Value
        value1 = Value.builder()
                .stars(5)
                .valueClient("Excelente experiencia con el refugio.")
                .valueAnimalShelter("Cliente muy responsable.")
                .adoption(dummyAdoption)
                .build();

        value2 = Value.builder()
                .stars(3)
                .valueClient("Buena experiencia, pero con algunos problemas.")
                .valueAnimalShelter("Cliente cumplió con los requisitos mínimos.")
                .adoption(dummyAdoption)
                .build();
    }

    @Test
    void testGetters() {
        assertEquals(5, value1.getStars());
        assertEquals("Excelente experiencia con el refugio.", value1.getValueClient());
        assertEquals("Cliente muy responsable.", value1.getValueAnimalShelter());
        assertEquals(dummyAdoption, value1.getAdoption());

        assertEquals(3, value2.getStars());
        assertEquals("Buena experiencia, pero con algunos problemas.", value2.getValueClient());
        assertEquals("Cliente cumplió con los requisitos mínimos.", value2.getValueAnimalShelter());
        assertEquals(dummyAdoption, value2.getAdoption());
    }

    @Test
    void testSetters() {
        value1.setStars(4);
        value1.setValueClient("Experiencia satisfactoria.");
        value1.setValueAnimalShelter("Cliente cumplió correctamente.");

        assertEquals(4, value1.getStars());
        assertEquals("Experiencia satisfactoria.", value1.getValueClient());
        assertEquals("Cliente cumplió correctamente.", value1.getValueAnimalShelter());
    }

    @Test
    void testEqualsAndHashCode() {
        Value sameAsValue1 = Value.builder()
                .stars(5)
                .valueClient("Excelente experiencia con el refugio.")
                .valueAnimalShelter("Cliente muy responsable.")
                .adoption(dummyAdoption)
                .build();

        assertEquals(value1, sameAsValue1);
        assertEquals(value1.hashCode(), sameAsValue1.hashCode());

        assertNotEquals(value1, value2);
        assertNotEquals(value1.hashCode(), value2.hashCode());
    }

    @Test
    void testToString() {
        String value1String = value1.toString();
        assertTrue(value1String.contains("5"));
        assertTrue(value1String.contains("Excelente experiencia con el refugio."));
        assertTrue(value1String.contains("Cliente muy responsable."));
    }
}
