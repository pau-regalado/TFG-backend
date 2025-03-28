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
                Animal.builder().id("128").name("Brenda").color("marrón y blanco").size("mediano").race("bull terrier").description("Muy carinosa").birth_date("02-02-2020").entryDate("17-10-2024").sex("Hembra").age(5).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Brenda", "jpg")).build(),
                Animal.builder().id("129").name("Dakota").color("beige").size("pequeño").race("mestizo").description("Muy carinosa").birth_date("02-02-2022").entryDate("13-08-2024").sex("Hembra").age(3).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Dakota", "jpeg")).build(),
                Animal.builder().id("130").name("Dodo").color("marrón").size("grande").race("dogo").description("Muy carinosa").birth_date("02-02-2023").entryDate("02-02-2025").sex("Macho").age(2).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Dodo", "jpeg")).build(),
                Animal.builder().id("131").name("Freddy").color("blanco").size("grande").race("mestizo").description("Muy carinosa").birth_date("23-04-2021").entryDate("28-12-2024").sex("Macho").age(4).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Freddy", "jpg")).build(),
                Animal.builder().id("132").name("Kalo").color("marrón").size("grande").race("mestizo").description("Muy carinosa").birth_date("07-11-2023").entryDate("02-03-2025").sex("Macho").age(2).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Kalo", "webp")).build(),
                Animal.builder().id("133").name("Sheila").color("beige").size("mediano").race("mestizo").description("Muy carinosa").birth_date("24-08-2023").entryDate("18-01-2025").sex("Hembra").age(2).sterile(true).disability(true).imageUrl(this.imageService.loadImageBase64("Sheila", "webp")).build(),
                Animal.builder().id("134").name("Tobias").color("gris y blanco").size("mediano").race("terrier mestizo").description("Muy carinosa").birth_date("02-02-2020").entryDate("17-10-2024").sex("Macho").age(2).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Tobias", "jpg")).build(),
                Animal.builder().id("135").name("Nala").color("marrón y blanco").size("mediano").race("podenco").description("Muy carinosa").birth_date("15-06-2019").entryDate("20-11-2024").sex("Hembra").age(6).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Nala", "jpeg")).build(),
                Animal.builder().id("136").name("Max").color("negro").size("mediano").race("spanien azul").description("Muy carinosa").birth_date("08-09-2021").entryDate("25-12-2024").sex("Macho").age(4).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Max", "jpg")).build(),
                Animal.builder().id("137").name("Pipa").color("blanco").size("mediano").race("carlino").description("Muy carinosa").birth_date("22-03-2015").entryDate("10-10-2024").sex("Hembra").age(10).sterile(true).disability(true).imageUrl(this.imageService.loadImageBase64("Pipa", "webp")).build(),
                Animal.builder().id("138").name("Rocky").color("marrón y negro").size("mediano").race("mestizo").description("Muy carinosa").birth_date("05-12-2018").entryDate("30-09-2024").sex("Macho").age(7).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Rocky", "jpg")).build(),
                Animal.builder().id("139").name("Kai").color("blanco y negro").size("grande").race("mestizo").description("Muy carinosa").birth_date("22-03-2020").entryDate("10-10-2024").sex("Macho").age(5).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Kai", "webp")).build(),
                Animal.builder().id("140").name("Susan").color("beige y negro").size("mediano").race("mestizo").description("Muy carinosa").birth_date("14-07-2014").entryDate("27-02-2025").sex("Macho").age(11).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Susan", "webp")).build(),
                Animal.builder().id("141").name("Coffe").color("beige").size("mediano").race("mestizo").description("Muy carinosa").birth_date("24-08-2023").entryDate("18-01-2025").sex("Hembra").age(2).sterile(true).disability(true).imageUrl(this.imageService.loadImageBase64("Coffe", "jpg")).build(),
                Animal.builder().id("142").name("Chispa").color("blanco y beige").size("mediano").race("mestizo").description("Muy carinosa").birth_date("02-02-2020").entryDate("17-10-2024").sex("Hembra").age(2).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Chispa", "jpg")).build(),
                Animal.builder().id("143").name("Clara").color("marrón").size("mediano").race("mestizo").description("Muy carinosa").birth_date("15-06-2019").entryDate("20-11-2024").sex("Hembra").age(6).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Clara", "jpg")).build(),
                Animal.builder().id("144").name("Luna").color("negro").size("mediano").race("mestizo").description("Muy carinosa").birth_date("08-09-2021").entryDate("25-12-2024").sex("Hembra").age(4).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Luna", "jpg")).build(),
                Animal.builder().id("145").name("Paco").color("blanco").size("mediano").race("mastin").description("Muy carinosa").birth_date("22-03-2015").entryDate("10-10-2024").sex("Macho").age(10).sterile(true).disability(true).imageUrl(this.imageService.loadImageBase64("Paco", "jpg")).build(),
                Animal.builder().id("146").name("Cristal").color("beige y blanco").size("mediano").race("mestizo").description("Muy carinosa").birth_date("05-12-2018").entryDate("30-09-2024").sex("Hembra").age(7).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Cristal", "jpeg")).build(),
                Animal.builder().id("147").name("Marcus").color("beige").size("mediano").race("mestizo").description("Muy carinosa").birth_date("22-03-2020").entryDate("10-10-2024").sex("Macho").age(5).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Marcus", "jpg")).build(),
                Animal.builder().id("148").name("Roco").color("beige y negro").size("grande").race("mestizo").description("Muy carinosa").birth_date("14-07-2014").entryDate("27-02-2025").sex("Macho").age(11).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Roco", "jpg")).build(),
                Animal.builder().id("146").name("Melo").color("beige y negro").size("grande").race("mestizo").description("Muy carinosa").birth_date("05-12-2018").entryDate("30-09-2024").sex("Hembra").age(7).sterile(true).disability(true).imageUrl(this.imageService.loadImageBase64("Melo", "webp")).build(),
                Animal.builder().id("147").name("Truco").color("beige y blanco").size("mediano").race("mestizo").description("Muy carinosa").birth_date("22-03-2020").entryDate("10-10-2024").sex("Macho").age(5).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Truco", "jpg")).build(),
                Animal.builder().id("148").name("Rayo").color("beige y blanco").size("grande").race("mestizo").description("Muy carinosa").birth_date("14-07-2014").entryDate("27-02-2025").sex("Macho").age(11).sterile(true).disability(false).imageUrl(this.imageService.loadImageBase64("Rayo", "png")).build()
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
                AnimalShelter.builder().name("Rescue1").location("Cam. la Hoya del Granadillar, s/n, 38400 Cruz Santa, Santa Cruz de Tenerife").longitude(-16.6095418).latitude(28.3809799).username("rescue1").password("admin").numberPhone(612345678).animalWL(List.of(animals[0], animals[1],animals[2],animals[3], animals[20])).imageUrl(this.imageService.loadImageBase64("Rescue1", "png")).build(),
                AnimalShelter.builder().name("Rescue2").location("Carr. Este, 38400 Puerto de la Cruz, Santa Cruz de Tenerife").longitude(-16.651466).latitude(28.3747234).username("rescue2").password("admin").numberPhone(612345678).animalWL(List.of(animals[4], animals[5],animals[6],animals[7])).imageUrl(this.imageService.loadImageBase64("Rescue2", "jpg")).build(),
                AnimalShelter.builder().name("Rescue3").location("C. Cartagena, s/n, 38205 La Laguna, Santa Cruz de Tenerife").longitude(-16.4977802).latitude(28.4177782).username("rescue3").password("admin").numberPhone(612345678).animalWL(List.of(animals[8], animals[9],animals[10],animals[11])).imageUrl(this.imageService.loadImageBase64("Rescue3", "jpg")).build(),
                AnimalShelter.builder().name("Rescue4").location("Camino las Samboas, 8, 38627 Arona, Santa Cruz de Tenerife").longitude(-16.7698601).latitude(28.0878755).username("rescue4").password("admin").numberPhone(612345678).animalWL(List.of(animals[11], animals[12],animals[13],animals[14])).imageUrl(this.imageService.loadImageBase64("Rescue4", "jpg")).build(),
                AnimalShelter.builder().name("Rescue5").location("38611 Granadilla, Santa Cruz de Tenerife").longitude(-16.6503135).latitude(28.1209432).username("rescue5").password("admin").numberPhone(612345678).animalWL(List.of(animals[15], animals[16],animals[17],animals[18], animals[19])).imageUrl(this.imageService.loadImageBase64("Rescue5", "jpg")).build()
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
