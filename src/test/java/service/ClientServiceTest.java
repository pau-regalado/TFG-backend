/*package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.repository.AnimalRepository;
import es.ull.animal_shelter.backend.repository.ClientRepository;
import es.ull.animal_shelter.backend.service.ClientService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;
    private Animal animal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        client = new Client();
        client.setId("client123");
        client.setName("John");
        client.setLastName("Doe");
        client.setAnimalWL(new ArrayList<>()); // Inicializar lista vacía

        // Crear un animal de prueba
        animal = new Animal();
        animal.setId("animal123");
        animal.setName("Bobby");
    }

    @Test
    void testGiveLike_Success() {
        when(clientRepository.findById("client123")).thenReturn(Optional.of(client));
        when(animalRepository.findById("animal123")).thenReturn(Optional.of(animal));

        List<Animal> likedAnimals = clientService.giveLike("client123", "animal123");

        assertEquals(1, likedAnimals.size());
        assertTrue(likedAnimals.contains(animal));

        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testGiveLike_ClientNotFound() {
        when(clientRepository.findById("client123")).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> clientService.giveLike("client123", "animal123"));
    }

    @Test
    void testGiveLike_AnimalNotFound() {
        when(clientRepository.findById("client123")).thenReturn(Optional.of(client));
        when(animalRepository.findById("animal123")).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> clientService.giveLike("client123", "animal123"));
    }

    @Test
    void testGiveLike_AnimalAlreadyLiked() {
        client.getAnimalWL().add(animal);

        when(clientRepository.findById("client123")).thenReturn(Optional.of(client));
        when(animalRepository.findById("animal123")).thenReturn(Optional.of(animal));

        List<Animal> likedAnimals = clientService.giveLike("client123", "animal123");

        assertEquals(1, likedAnimals.size());
        assertTrue(likedAnimals.contains(animal));

        verify(clientRepository, times(1)).save(client);
    }
}*/
