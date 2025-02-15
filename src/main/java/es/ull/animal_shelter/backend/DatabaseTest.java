package es.ull.animal_shelter.backend;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.model.AnimalShelter;
import es.ull.animal_shelter.backend.model.Client;
import es.ull.animal_shelter.backend.repository.AnimalRepository;
import es.ull.animal_shelter.backend.repository.AnimalShelterRepository;
import es.ull.animal_shelter.backend.repository.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("dev")
@Service
public class DatabaseTest {
    private AnimalRepository animalRepository;
    private ClientRepository clientRepository;
    private AnimalShelterRepository animalShelterRepository;


    @Autowired
    public DatabaseTest(AnimalRepository animalRepository,
                        ClientRepository clientRepository,
                        AnimalShelterRepository animalShelterRepository) {
        this.animalRepository = animalRepository;
        this.clientRepository = clientRepository;
        this.animalShelterRepository = animalShelterRepository;
        this.deleteAllAndInitializeAndSeedDataBase();
    }

    public void deleteAllAndInitializeAndSeedDataBase() {
        this.deleteAllAndInitialize();
        this.seedDataBaseJava();
    }

    private void deleteAllAndInitialize() {
        this.animalRepository.deleteAll();
        this.clientRepository.deleteAll();
        this.animalShelterRepository.deleteAll();
        LogManager.getLogger(this.getClass()).warn("------- Delete All -----------");
    }

    private void seedDataBaseJava() {
        LogManager.getLogger(this.getClass()).warn("------- Initial Load from JAVA -----------");

        Animal[] animals = {
                Animal.builder().id("123").name("Lola").color("marron").size("mediano").race("mestizo").description("Muy carinosa").birth_date("02-02-2020").entryDate("17-10-2024").sex("Hembra").age(5).sterile(true).disability(false).build(),
                Animal.builder().id("124").name("Max").color("negro").size("grande").race("labrador").description("Juguetón y enérgico").birth_date("15-06-2019").entryDate("20-11-2024").sex("Macho").age(5).sterile(true).disability(false).build(),
                Animal.builder().id("125").name("Nala").color("blanco").size("pequeño").race("poodle").description("Dócil y amigable").birth_date("08-09-2021").entryDate("25-12-2024").sex("Hembra").age(3).sterile(false).disability(false).build(),
                Animal.builder().id("126").name("Rocky").color("marrón y blanco").size("mediano").race("beagle").description("Curioso y explorador").birth_date("22-03-2020").entryDate("10-10-2024").sex("Macho").age(4).sterile(true).disability(false).build(),
                Animal.builder().id("127").name("Luna").color("gris").size("mediano").race("husky").description("Muy inteligente y activa").birth_date("05-12-2018").entryDate("30-09-2024").sex("Hembra").age(6).sterile(true).disability(false).build()
        };
        this.animalRepository.saveAll(List.of(animals));
        LogManager.getLogger(this.getClass()).warn("        ------- animals");

        Client[] clientsLogin = {
                Client.builder().username("Nico20").email("Nico20@gmail.com").password("admin").animalWL(List.of(animals[0])).build(),
                Client.builder().username("PabloReyes30").email("PabloReyes30@gmail.com").password("admin").build(),
                Client.builder().username("PauRegalado01").email("PauRegalado01@gmail.com").password("admin").build(),
                Client.builder().username("Cami87").email("Cami87@gmail.com").password("admin").build(),
                Client.builder().username("NereaCampos99").email("NereaCampos99@gmail.com").password("admin").build()
        };
        this.clientRepository.saveAll(List.of(clientsLogin));
        LogManager.getLogger(this.getClass()).warn("        ------- clients");

        AnimalShelter[] animalShelters = {
                AnimalShelter.builder().name("Rescue1").location("La Laguna").username("Nico30").password("admin").numberPhone(612345678).animals(List.of(animals[0])).build(),
                AnimalShelter.builder().name("Rescue2").location("Santa Cruz").numberPhone(612345678).animals(List.of(animals[1])).build(),
                AnimalShelter.builder().name("Rescue3").location("La Orotava").numberPhone(612345678).animals(List.of(animals[2])).build(),
                AnimalShelter.builder().name("Rescue4").location("El Sauzal").numberPhone(612345678).animals(List.of(animals[3])).build(),
                AnimalShelter.builder().name("Rescue5").location("Los Cristianos").numberPhone(612345678).animals(List.of(animals[4])).build()
        };
        this.animalShelterRepository.saveAll(List.of(animalShelters));
        LogManager.getLogger(this.getClass()).warn("        ------- animalShelters");
    }


}
