package es.ull.animal_shelter.backend;

import es.ull.animal_shelter.backend.model.*;
import es.ull.animal_shelter.backend.repository.AdoptionRepository;
import es.ull.animal_shelter.backend.repository.AnimalRepository;
import es.ull.animal_shelter.backend.repository.AnimalShelterRepository;
import es.ull.animal_shelter.backend.repository.ClientRepository;
import es.ull.animal_shelter.backend.service.ImageService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Profile("dev")
@Service
public class DatabaseTest {
    private AnimalRepository animalRepository;
    private ClientRepository clientRepository;
    private AnimalShelterRepository animalShelterRepository;
    private AdoptionRepository adoptionRepository;
    private ImageService imageService;


    @Autowired
    public DatabaseTest(AnimalRepository animalRepository,
                        ClientRepository clientRepository,
                        AnimalShelterRepository animalShelterRepository,
                        AdoptionRepository adoptionRepository,
                        ImageService imageService) {
        this.animalRepository = animalRepository;
        this.clientRepository = clientRepository;
        this.animalShelterRepository = animalShelterRepository;
        this.adoptionRepository = adoptionRepository;
        this.imageService = imageService;
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
        this.adoptionRepository.deleteAll();
        LogManager.getLogger(this.getClass()).warn("------- Delete All -----------");
    }

    private void seedDataBaseJava() {
        LogManager.getLogger(this.getClass()).warn("------- Initial Load from JAVA -----------");

        Animal[] animals = {
                Animal.builder().id("123").name("Lola").color("marrón").size("mediano").race("mestizo").description("Muy carinosa").birth_date("02-02-2020").entryDate("17-10-2024").sex("Hembra").age(5).sterile(true).disability(false).build(),
                Animal.builder().id("124").name("Max").color("negro").size("grande").race("labrador").description("Juguetón y enérgico").birth_date("15-06-2019").entryDate("20-11-2024").sex("Macho").age(10).sterile(true).disability(true).build(),
                Animal.builder().id("125").name("Nala").color("blanco").size("pequeño").race("poodle").description("Dócil y amigable").birth_date("08-09-2021").entryDate("25-12-2024").sex("Hembra").age(3).sterile(false).disability(false).build(),
                Animal.builder().id("126").name("Rocky").color("marrón y blanco").size("mediano").race("beagle").description("Curioso y explorador").birth_date("22-03-2020").entryDate("10-10-2024").sex("Macho").age(4).sterile(true).disability(false).build(),
                Animal.builder().id("127").name("Luna").color("gris").size("mediano").race("husky").description("Muy inteligente y activa").birth_date("05-12-2018").entryDate("30-09-2024").sex("Hembra").age(6).sterile(true).disability(false).build(),
                Animal.builder().id("128").name("Brenda").color("marrón y blanco").size("mediano").race("bull terrier").description("Muy carinosa").birth_date("02-02-2020").entryDate("17-10-2024").sex("Hembra").age(5).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Brenda", "jpg")).build(),
                Animal.builder().id("129").name("Dakota").color("beige").size("pequeño").race("mestizo").description("Muy carinosa").birth_date("02-02-2022").entryDate("13-08-2024").sex("Hembra").age(3).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Dakota", "jpeg")).build()
        };
        this.animalRepository.saveAll(List.of(animals));
        LogManager.getLogger(this.getClass()).warn("        ------- animals");

        Client[] clientsLogin = {
                Client.builder().name("Nico").lastName("Perez").username("Nico20").email("Nico20@gmail.com").password("admin").animalWL(List.of(animals[0])).build(),
                Client.builder().name("Pablo").lastName("Reyes").username("PabloReyes30").email("PabloReyes30@gmail.com").password("admin").build(),
                Client.builder().name("Paula").lastName("Regalado").username("PauRegalado01").email("PauRegalado01@gmail.com").password("admin").build(),
                Client.builder().name("Camila").lastName("Tejero").username("Cami87").email("Cami87@gmail.com").password("admin").build(),
                Client.builder().name("Nerea").lastName("Campos").username("NereaCampos99").email("NereaCampos99@gmail.com").password("admin").build()
        };
        this.clientRepository.saveAll(List.of(clientsLogin));
        LogManager.getLogger(this.getClass()).warn("        ------- clients");

        AnimalShelter[] animalShelters = {
                AnimalShelter.builder().name("Rescue1").location("La Laguna").username("Nico30").password("admin").numberPhone(612345678).animalWL(List.of(animals[0])).build(),
                AnimalShelter.builder().name("Rescue2").location("Santa Cruz").username("rescue2").password("admin").numberPhone(612345678).animalWL(List.of(animals[1])).build(),
                AnimalShelter.builder().name("Rescue3").location("La Orotava").username("rescue3").password("admin").numberPhone(612345678).animalWL(List.of(animals[2])).build(),
                AnimalShelter.builder().name("Rescue4").location("El Sauzal").username("rescue4").password("admin").numberPhone(612345678).animalWL(List.of(animals[3])).build(),
                AnimalShelter.builder().name("Rescue5").location("Los Cristianos").username("rescue5").password("admin").numberPhone(612345678).animalWL(List.of(animals[4])).build()
        };
        this.animalShelterRepository.saveAll(List.of(animalShelters));
        LogManager.getLogger(this.getClass()).warn("        ------- animalShelters");

        Adoption[] adoptions = {
                Adoption.builder().animalShelter(animalShelters[0]).animal(animals[0]).client(clientsLogin[0]).date(LocalDateTime.now()).status(AdoptionStatus.PENDING).build(),
                Adoption.builder().animalShelter(animalShelters[1]).animal(animals[1]).client(clientsLogin[1]).date(LocalDateTime.of(2024,12,12,14,30)).status(AdoptionStatus.PENDING).build()
        };
        this.adoptionRepository.saveAll(List.of(adoptions));
        LogManager.getLogger(this.getClass()).warn("        ------- adoptions");
    }
}
