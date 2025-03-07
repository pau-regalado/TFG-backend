/*package model;

import es.ull.animal_shelter.backend.model.Animal;
import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    private Animal animal1;
    private Animal animal2;

    @BeforeEach
    void setUp() {
        animal1 = Animal.builder()
                .id("123")
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
                .image(new Binary(new byte[]{1, 2, 3}))
                .build();

        animal2 = Animal.builder()
                .id("124")
                .name("Max")
                .color("negro")
                .size("grande")
                .race("labrador")
                .description("Juguetón y enérgico")
                .birth_date("15-06-2019")
                .entryDate("20-11-2024")
                .sex("Macho")
                .age(10)
                .sterile(true)
                .disability(true)
                .image(new Binary(new byte[]{4, 5, 6}))
                .build();
    }

    @Test
    void testGetters() {
        assertEquals("123", animal1.getId());
        assertEquals("Lola", animal1.getName());
        assertEquals("marrón", animal1.getColor());
        assertEquals("mediano", animal1.getSize());
        assertEquals("mestizo", animal1.getRace());
        assertEquals("Muy cariñosa", animal1.getDescription());
        assertEquals("02-02-2020", animal1.getBirth_date());
        assertEquals("17-10-2024", animal1.getEntryDate());
        assertEquals("Hembra", animal1.getSex());
        assertEquals(5, animal1.getAge());
        assertTrue(animal1.getSterile());
        assertFalse(animal1.getDisability());
        assertNotNull(animal1.getImage());

        assertEquals("124", animal2.getId());
        assertEquals("Max", animal2.getName());
        assertEquals("negro", animal2.getColor());
        assertEquals("grande", animal2.getSize());
        assertEquals("labrador", animal2.getRace());
        assertEquals("Juguetón y enérgico", animal2.getDescription());
        assertEquals("15-06-2019", animal2.getBirth_date());
        assertEquals("20-11-2024", animal2.getEntryDate());
        assertEquals("Macho", animal2.getSex());
        assertEquals(10, animal2.getAge());
        assertTrue(animal2.getSterile());
        assertTrue(animal2.getDisability());
        assertNotNull(animal2.getImage());
    }

    @Test
    void testSetters() {
        animal1.setName("Nina");
        animal1.setColor("blanco");
        animal1.setSize("pequeño");
        animal1.setRace("caniche");
        animal1.setDescription("Muy juguetona");
        animal1.setBirth_date("01-01-2021");
        animal1.setEntryDate("15-08-2024");
        animal1.setSex("Hembra");
        animal1.setAge(3);
        animal1.setSterile(false);
        animal1.setDisability(true);
        animal1.setImage(new Binary(new byte[]{7, 8, 9}));

        assertEquals("Nina", animal1.getName());
        assertEquals("blanco", animal1.getColor());
        assertEquals("pequeño", animal1.getSize());
        assertEquals("caniche", animal1.getRace());
        assertEquals("Muy juguetona", animal1.getDescription());
        assertEquals("01-01-2021", animal1.getBirth_date());
        assertEquals("15-08-2024", animal1.getEntryDate());
        assertEquals("Hembra", animal1.getSex());
        assertEquals(3, animal1.getAge());
        assertFalse(animal1.getSterile());
        assertTrue(animal1.getDisability());
        assertNotNull(animal1.getImage());
    }

    @Test
    void testEqualsAndHashCode() {
        Animal sameAsAnimal1 = Animal.builder()
                .id("123")
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
                .image(new Binary(new byte[]{1, 2, 3}))
                .build();

        assertEquals(animal1, sameAsAnimal1);
        assertEquals(animal1.hashCode(), sameAsAnimal1.hashCode());

        assertNotEquals(animal1, animal2);
        assertNotEquals(animal1.hashCode(), animal2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Animal(id=123, name=Lola, color=marrón, size=mediano, race=mestizo, description=Muy cariñosa, birth_date=02-02-2020, entryDate=17-10-2024, sex=Hembra, age=5, sterile=true, disability=false, image=[binary data])";
        assertTrue(animal1.toString().contains("id=123"));
        assertTrue(animal1.toString().contains("name=Lola"));
        assertTrue(animal1.toString().contains("color=marrón"));
        assertTrue(animal1.toString().contains("sterile=true"));
    }
}
*/