package model;

import es.ull.animal_shelter.backend.controller.dto.RegisterClientRequest;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client client1;
    private Client client2;
    private Animal animal1;
    private Animal animal2;

    @BeforeEach
    void setUp() {
        // Crear animales para la lista de deseos
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

        animal2 = Animal.builder()
                .id("A002")
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
                .build();

        // Crear clientes
        client1 = Client.builder()
                .id("C001")
                .name("Carlos")
                .lastName("Pérez")
                .username("Carlos123")
                .password("securePass")
                .email("carlos123@gmail.com")
                .animalWL(List.of(animal1))
                .build();

        client2 = Client.builder()
                .id("C002")
                .name("Maria")
                .lastName("Gómez")
                .username("Maria456")
                .password("pass456")
                .email("maria456@gmail.com")
                .animalWL(List.of(animal2))
                .build();
    }

    @Test
    void testGetters() {
        assertEquals("C001", client1.getId());
        assertEquals("Carlos", client1.getName());
        assertEquals("Pérez", client1.getLastName());
        assertEquals("Carlos123", client1.getUsername());
        assertEquals("securePass", client1.getPassword());
        assertEquals("carlos123@gmail.com", client1.getEmail());
        assertEquals(1, client1.getAnimalWL().size());
        assertEquals("Lola", client1.getAnimalWL().get(0).getName());
    }

    @Test
    void testSetters() {
        client1.setName("Juan");
        client1.setLastName("López");
        client1.setUsername("JuanL");
        client1.setPassword("newPass");
        client1.setEmail("juanL@gmail.com");
        client1.setAnimalWL(List.of(animal2));

        assertEquals("Juan", client1.getName());
        assertEquals("López", client1.getLastName());
        assertEquals("JuanL", client1.getUsername());
        assertEquals("newPass", client1.getPassword());
        assertEquals("juanL@gmail.com", client1.getEmail());
        assertEquals(1, client1.getAnimalWL().size());
        assertEquals("Max", client1.getAnimalWL().get(0).getName());
    }

    @Test
    void testEqualsAndHashCode() {
        Client sameAsClient1 = Client.builder()
                .id("C001")
                .name("Carlos")
                .lastName("Pérez")
                .username("Carlos123")
                .password("securePass")
                .email("carlos123@gmail.com")
                .animalWL(List.of(animal1))
                .build();

        assertEquals(client1, sameAsClient1);
        assertEquals(client1.hashCode(), sameAsClient1.hashCode());

        assertNotEquals(client1, client2);
        assertNotEquals(client1.hashCode(), client2.hashCode());
    }

    @Test
    void testToString() {
        String client1ToString = client1.toString();
        assertTrue(client1ToString.contains("Carlos"));
        assertTrue(client1ToString.contains("Pérez"));
        assertTrue(client1ToString.contains("animalWL"));
    }

    @Test
    void testFromRegisterClientRequest() {
        RegisterClientRequest request = new RegisterClientRequest();
        request.setName("Luis");
        request.setLastName("Martínez");
        request.setUsername("LuisM");
        request.setPassword("luisPass");
        request.setEmail("luism@gmail.com");

        Client newClient = client1.fromRegisterClientRequest(request);

        assertNotNull(newClient);
        assertEquals("Luis", newClient.getName());
        assertEquals("Martínez", newClient.getLastName());
        assertEquals("LuisM", newClient.getUsername());
        assertEquals("luisPass", newClient.getPassword());
        assertEquals("luism@gmail.com", newClient.getEmail());
    }
}
