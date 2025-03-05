package model;

import java.util.ArrayList;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.repository.AnimalRepository;
import es.ull.animal_shelter.backend.repository.ClientRepository;
import es.ull.animal_shelter.backend.service.ClientService;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        client.setLastName("Nieve");
        client.setLastName("Doe");
        client.setAnimalWL(new ArrayList<>()); // Inicializar lista vacía

        // Crear un animal de prueba
        animal = new Animal();
        animal.setId("animal123");
        animal.setName("Bobby");
    }
}