/*package model;

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
        // Instancia de adopción dummy
        dummyAdoption = Adoption.builder()
                .id("ad1")
                .build();

        // Instancias de Value con los nuevos atributos
        value1 = Value.builder()
                .starsClient(5)
                .starsAnimalShelter(4)
                .valueClient("Excelente experiencia con el refugio.")
                .valueAnimalShelter("Cliente muy responsable.")
                .build();

        value2 = Value.builder()
                .starsClient(3)
                .starsAnimalShelter(2)
                .valueClient("Buena experiencia, pero con algunos problemas.")
                .valueAnimalShelter("Cliente cumplió con los requisitos mínimos.")
                .build();
    }

    @Test
    void testGetters() {
        assertEquals(5, value1.getStarsClient());
        assertEquals(4, value1.getStarsAnimalShelter());
        assertEquals("Excelente experiencia con el refugio.", value1.getValueClient());
        assertEquals("Cliente muy responsable.", value1.getValueAnimalShelter());

        assertEquals(3, value2.getStarsClient());
        assertEquals(2, value2.getStarsAnimalShelter());
        assertEquals("Buena experiencia, pero con algunos problemas.", value2.getValueClient());
        assertEquals("Cliente cumplió con los requisitos mínimos.", value2.getValueAnimalShelter());
    }

    @Test
    void testSetters() {
        value1.setStarsClient(4);
        value1.setStarsAnimalShelter(3);
        value1.setValueClient("Experiencia satisfactoria.");
        value1.setValueAnimalShelter("Cliente cumplió correctamente.");

        assertEquals(4, value1.getStarsClient());
        assertEquals(3, value1.getStarsAnimalShelter());
        assertEquals("Experiencia satisfactoria.", value1.getValueClient());
        assertEquals("Cliente cumplió correctamente.", value1.getValueAnimalShelter());
    }

    @Test
    void testEqualsAndHashCode() {
        Value sameAsValue1 = Value.builder()
                .starsClient(5)
                .starsAnimalShelter(4)
                .valueClient("Excelente experiencia con el refugio.")
                .valueAnimalShelter("Cliente muy responsable.")
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
        assertTrue(value1String.contains("4"));
        assertTrue(value1String.contains("Excelente experiencia con el refugio."));
        assertTrue(value1String.contains("Cliente muy responsable."));
    }
}
*/