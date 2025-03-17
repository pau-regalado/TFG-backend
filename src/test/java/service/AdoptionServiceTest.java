package service;

import es.ull.animal_shelter.backend.BackendApplication;
import es.ull.animal_shelter.backend.controller.dto.AdoptionDetails;
import es.ull.animal_shelter.backend.model.Adoption;
import es.ull.animal_shelter.backend.model.AdoptionStatus;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.service.AdoptionService;
import es.ull.animal_shelter.backend.service.AnimalService;
import es.ull.animal_shelter.backend.service.AnimalShelterService;
import es.ull.animal_shelter.backend.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("dev")
class AdoptionServiceTest {

    @Autowired
    private AdoptionService adoptionService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalShelterService animalShelterService;

    @Autowired
    private ClientService clientService;

    /**
     * Se utiliza el animal con id "128" (Brenda, presembrada en DatabaseTest) y su refugio asociado (Rescue1),
     * y se crea un cliente nuevo para formar la solicitud de adopción.
     * IMPORTANTE: Se crea el DTO con el orden (animalId, clientId)
     */
    @Test
    void testSaveAdoption() {
        // Recuperamos el animal presembrado con id "128" (Brenda)
        Animal animal = animalService.findById("128");
        // Obtenemos el refugio asociado al animal (según la semilla, Brenda está en Rescue1)
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 128");

        // Creamos un cliente nuevo con id fijo
        Client client = Client.builder()
                .id("c_adopt1")
                .name("TestClient1")
                .build();
        clientService.save(client);

        // IMPORTANTE: Creamos el DTO con (animalId, clientId)
        AdoptionDetails details = new AdoptionDetails(animal.getId(), client.getId());
        Adoption adoption = adoptionService.save(details);

        assertNotNull(adoption);
        assertEquals(AdoptionStatus.PENDING, adoption.getStatus());
        assertEquals(client.getId(), adoption.getClient().getId());
        assertEquals(animal.getId(), adoption.getAnimal().getId());
        assertEquals(shelter.getId(), adoption.getAnimalShelter().getId());
    }

    /**
     * Se prueba que al intentar crear una solicitud duplicada (mismo cliente y animal) se lance la excepción
     * de conflicto.
     */
    @Test
    void testSaveAdoption_AlreadyRequested() {
        // Recuperamos el animal con id "129" (Dakota)
        Animal animal = animalService.findById("129");
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 129");

        // Creamos un cliente nuevo con id fijo
        Client client = Client.builder()
                .id("c_adopt2")
                .name("TestClient2")
                .build();
        clientService.save(client);

        // IMPORTANTE: El orden es (animalId, clientId)
        AdoptionDetails details = new AdoptionDetails(animal.getId(), client.getId());

        Adoption adoption1 = adoptionService.save(details);
        assertNotNull(adoption1);
        assertEquals(AdoptionStatus.PENDING, adoption1.getStatus());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            adoptionService.save(details);
        });
        assertEquals("409 CONFLICT \"Ya has enviado una solicitud para este animal y está pendiente de aprobación.\"",
                exception.getMessage());
    }

    /**
     * Se prueba la confirmación de una solicitud de adopción.
     * Utilizamos el animal con id "130" (Dodo) y un cliente nuevo.
     */
    @Test
    void testConfirmAdoptionRequest() {
        Animal animal = animalService.findById("130"); // Dodo
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 130");

        Client client = Client.builder()
                .id("c_adopt3")
                .name("TestClient3")
                .build();
        clientService.save(client);

        AdoptionDetails details = new AdoptionDetails(animal.getId(), client.getId());
        Adoption adoption = adoptionService.save(details);
        assertNotNull(adoption);
        assertEquals(AdoptionStatus.PENDING, adoption.getStatus());

        Adoption confirmedAdoption = adoptionService.confirmAdoptionRequest(adoption.getId());
        assertNotNull(confirmedAdoption);
        assertEquals(AdoptionStatus.ACCEPTED, confirmedAdoption.getStatus());
    }

    /**
     * Se prueba el rechazo de una solicitud de adopción.
     * Utilizamos el animal con id "131" (Freddy) y un cliente nuevo.
     */
    @Test
    void testRejectAdoptionRequest() {
        Animal animal = animalService.findById("131"); // Freddy
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 131");

        Client client = Client.builder()
                .id("c_adopt4")
                .name("TestClient4")
                .build();
        clientService.save(client);

        AdoptionDetails details = new AdoptionDetails(animal.getId(), client.getId());
        Adoption adoption = adoptionService.save(details);
        assertNotNull(adoption);
        assertEquals(AdoptionStatus.PENDING, adoption.getStatus());

        Adoption rejectedAdoption = adoptionService.rejectAdoptionRequest(adoption.getId());
        assertNotNull(rejectedAdoption);
        assertEquals(AdoptionStatus.REJECTED, rejectedAdoption.getStatus());
    }

    /**
     * Se prueba la consulta de una solicitud de adopción por su id.
     */
    @Test
    void testFindById() {
        Animal animal = animalService.findById("132"); // Kalo
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 132");

        Client client = Client.builder()
                .id("c_adopt5")
                .name("TestClient5")
                .build();
        clientService.save(client);

        AdoptionDetails details = new AdoptionDetails(animal.getId(), client.getId());
        Adoption adoption = adoptionService.save(details);

        Adoption foundAdoption = adoptionService.findById(adoption.getId());
        assertNotNull(foundAdoption);
        assertEquals(adoption.getId(), foundAdoption.getId());
    }

    /**
     * Se prueba que la consulta de una solicitud de adopción inexistente lanza la excepción esperada.
     */
    @Test
    void testFindById_NotFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            adoptionService.findById("nonexistent");
        });
        assertEquals("404 NOT_FOUND \"Adoption no encontrado con ID: nonexistent\"", exception.getMessage());
    }

    /**
     * Se prueba la consulta de solicitudes de adopción por id de refugio.
     * Utilizamos el animal con id "133" (Sheila) para obtener su refugio.
     */
    @Test
    void testFindByAnimalShelterId() {
        Animal animal = animalService.findById("133"); // Sheila
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 133");

        Client client = Client.builder()
                .id("c_adopt6")
                .name("TestClient6")
                .build();
        clientService.save(client);

        AdoptionDetails details = new AdoptionDetails(animal.getId(), client.getId());
        adoptionService.save(details);

        List<Adoption> adoptions = adoptionService.findByAnimalShelterId(shelter.getId());
        assertNotNull(adoptions);
        assertFalse(adoptions.isEmpty());
        adoptions.forEach(a -> assertEquals(shelter.getId(), a.getAnimalShelter().getId()));
    }

    /**
     * Se prueba la consulta de solicitudes de adopción por id de cliente.
     * Utilizamos el animal con id "134" (Tobias) y un cliente nuevo.
     */
    @Test
    void testFindByClientId() {
        Animal animal = animalService.findById("134"); // Tobias
        AnimalShelter shelter = animalShelterService.findByAnimal(animal);
        assertNotNull(shelter, "No se encontró el refugio para el animal con id 134");

        Client client = Client.builder()
                .id("c_adopt7")
                .name("TestClient7")
                .build();
        clientService.save(client);

        AdoptionDetails details = new AdoptionDetails(animal.getId(), client.getId());
        adoptionService.save(details);

        List<Adoption> adoptions = adoptionService.findByClientId(client.getId());
        assertNotNull(adoptions);
        assertFalse(adoptions.isEmpty());
        adoptions.forEach(a -> assertEquals(client.getId(), a.getClient().getId()));
    }
}
