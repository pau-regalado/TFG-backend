package service;

import es.ull.animal_shelter.backend.BackendApplication;
import es.ull.animal_shelter.backend.controller.dto.LoginRequest;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.repository.AnimalRepository;
import es.ull.animal_shelter.backend.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    void testSaveClient() {
        Client client = Client.builder()
                .id("c1")
                .username("testuser1")
                .password("pass")
                .email("testuser1@example.com")
                .name("Test User 1")
                .animalWL(Collections.emptyList())
                .build();

        Client savedClient = clientService.save(client);
        assertNotNull(savedClient);
        assertEquals("testuser1", savedClient.getUsername());
    }

    @Test
    void testFindByUsername() {
        Client client = Client.builder()
                .id("c2")
                .username("john_doe")
                .password("secret")
                .email("john@example.com")
                .name("John Doe")
                .animalWL(Collections.emptyList())
                .build();

        clientService.save(client);

        Client foundClient = clientService.findByUsername("john_doe");
        assertNotNull(foundClient);
        assertEquals("john_doe", foundClient.getUsername());
    }

    @Test
    void testFindById() {
        Client client = Client.builder()
                .id("c3")
                .username("alice")
                .password("pass")
                .email("alice@example.com")
                .name("Alice")
                .animalWL(Collections.emptyList())
                .build();

        clientService.save(client);

        Client foundClient = clientService.findById("c3");
        assertNotNull(foundClient);
        assertEquals("alice", foundClient.getUsername());
    }

    @Test
    void testFindAll() {
        Client client1 = Client.builder()
                .id("c4")
                .username("user1")
                .password("pass1")
                .email("user1@example.com")
                .name("User One")
                .animalWL(Collections.emptyList())
                .build();

        Client client2 = Client.builder()
                .id("c5")
                .username("user2")
                .password("pass2")
                .email("user2@example.com")
                .name("User Two")
                .animalWL(Collections.emptyList())
                .build();

        clientService.save(client1);
        clientService.save(client2);

        List<Client> clients = clientService.findAll();
        assertNotNull(clients);
        assertTrue(clients.size() >= 2);
    }

    @Test
    void testLogin() {
        Client client = Client.builder()
                .id("c6")
                .username("loginuser")
                .password("mypassword")
                .email("login@example.com")
                .name("Login User")
                .animalWL(Collections.emptyList())
                .build();

        clientService.save(client);

        LoginRequest loginRequest = new LoginRequest("loginuser", "mypassword");
        Client loggedClient = clientService.login(loginRequest);
        assertNotNull(loggedClient);
        assertEquals("loginuser", loggedClient.getUsername());
    }

    @Test
    void testAddAnimalToWishListAndViewAnimals() {
        // Crear y guardar un cliente
        Client client = Client.builder()
                .id("c7")
                .username("wishlistuser")
                .password("pass")
                .email("wishlist@example.com")
                .name("Wish List User")
                .animalWL(Collections.emptyList())
                .build();
        clientService.save(client);

        // Crear y guardar un animal (ID único que no coincide con los de la semilla)
        Animal animal = new Animal();
        animal.setId("a1");
        animal.setName("Fluffy");
        animal.setColor("white");
        animal.setSize("small");
        animal.setRace("cat");
        animal.setDescription("Cute cat");
        animal.setBirth_date("01-01-2021");
        animal.setEntryDate("02-02-2021");
        animal.setSex("Hembra");
        animal.setAge(2);
        animal.setSterile(true);
        animal.setDisability(false);
        animal.setImageUrl("http://example.com/fluffy.jpg");
        animalRepository.save(animal);

        // Agregar el animal a la lista de deseos del cliente
        Client updatedClient = clientService.addAnimalToWishList("c7", "a1");
        assertNotNull(updatedClient);
        assertTrue(updatedClient.getAnimalWL().contains(animal));

        // Verificar la lista de animales en la lista de deseos
        List<Animal> wishlistAnimals = clientService.viewAnimals("c7");
        assertNotNull(wishlistAnimals);
        assertTrue(wishlistAnimals.contains(animal));
    }

    @Test
    void testDeleteAnimalFromWishList() {
        // Crear y guardar un cliente
        Client client = Client.builder()
                .id("c8")
                .username("deleteuser")
                .password("pass")
                .email("delete@example.com")
                .name("Delete User")
                .animalWL(Collections.emptyList())
                .build();
        clientService.save(client);

        // Crear y guardar un animal (ID único)
        Animal animal = new Animal();
        animal.setId("a2");
        animal.setName("Buddy");
        animal.setColor("brown");
        animal.setSize("medium");
        animal.setRace("dog");
        animal.setDescription("Friendly dog");
        animal.setBirth_date("01-01-2020");
        animal.setEntryDate("02-02-2020");
        animal.setSex("Macho");
        animal.setAge(3);
        animal.setSterile(true);
        animal.setDisability(false);
        animal.setImageUrl("http://example.com/buddy.jpg");
        animalRepository.save(animal);

        // Agregar el animal a la lista de deseos
        clientService.addAnimalToWishList("c8", "a2");

        // Eliminar el animal de la lista de deseos
        Animal deletedAnimal = clientService.deleteAnimalFromWishList("c8", "a2");
        assertNotNull(deletedAnimal);
        assertEquals("Buddy", deletedAnimal.getName());

        // Verificar que el animal ya no se encuentre en la lista de deseos
        Client updatedClient = clientService.findById("c8");
        assertFalse(updatedClient.getAnimalWL().contains(animal));
    }

    @Test
    void testGetRecommendations() {
        // Crear y guardar un cliente sin animales en su lista de deseos inicialmente
        Client client = Client.builder()
                .id("c9")
                .username("recommender")
                .password("pass")
                .email("recommender@example.com")
                .name("Recommender User")
                .animalWL(Collections.emptyList())
                .build();
        clientService.save(client);

        // Crear y guardar algunos animales (IDs únicos)
        Animal animal1 = new Animal();
        animal1.setId("a3");
        animal1.setName("Bella");
        animal1.setColor("black");
        animal1.setSize("small");
        animal1.setRace("cat");
        animal1.setDescription("A lovely cat");
        animal1.setBirth_date("01-03-2021");
        animal1.setEntryDate("05-03-2021");
        animal1.setSex("Hembra");
        animal1.setAge(2);
        animal1.setSterile(true);
        animal1.setDisability(false);
        animal1.setImageUrl("http://example.com/bella.jpg");
        animalRepository.save(animal1);

        Animal animal2 = new Animal();
        animal2.setId("a4");
        animal2.setName("Max");
        animal2.setColor("black");
        animal2.setSize("small");
        animal2.setRace("cat");
        animal2.setDescription("Another lovely cat");
        animal2.setBirth_date("01-04-2021");
        animal2.setEntryDate("05-04-2021");
        animal2.setSex("Macho");
        animal2.setAge(2);
        animal2.setSterile(true);
        animal2.setDisability(false);
        animal2.setImageUrl("http://example.com/max.jpg");
        animalRepository.save(animal2);

        // Agregar animal1 a la lista de deseos (se excluye de recomendaciones)
        clientService.addAnimalToWishList("c9", "a3");

        // Obtener recomendaciones: se espera que devuelva animal2 (u otros que se asemejen a las características promedio)
        List<Animal> recommendations = clientService.getRecommendations("c9");
        assertNotNull(recommendations);
        // Al tener solo animal2 fuera de la lista de deseos, se espera que figure en las recomendaciones
        assertTrue(recommendations.contains(animal2));
    }

    @Test
    void testClientNotFound() {
        // Probar findById con un cliente inexistente
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            clientService.findById("nonexistent");
        });
        assertTrue(exception.getMessage().contains("Client not found with id: nonexistent"));

        // Probar findByUsername con un cliente inexistente
        Exception exception2 = assertThrows(ResponseStatusException.class, () -> {
            clientService.findByUsername("nonexistent");
        });
        assertTrue(exception2.getMessage().contains("Client not found with username: nonexistent"));
    }
}
