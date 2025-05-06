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
    private static final String ADMIN = "admin";

    private AnimalRepository animalRepository;
    private ClientRepository clientRepository;
    private AnimalShelterRepository animalShelterRepository;
    private AdoptionRepository adoptionRepository;
    private ImageService imageService;

    // Enums para literales repetidos
    private enum Size { MEDIANO("mediano"), GRANDE("grande"), PEQUENO("pequeño");
        private final String v; Size(String v){ this.v=v;} public String v(){return v;}}
    private enum Sex { HEMBRA("Hembra"), MACHO("Macho");
        private final String v; Sex(String v){ this.v=v;} public String v(){return v;}}
    private enum Race { MESTIZO("mestizo");
        private final String v; Race(String v){ this.v=v;} public String v(){return v;}}
    private enum Color {
        BEIGE("beige"), MARRON("marrón"), BLANCO("blanco"),
        NEGRO("negro"), GRAY_WHITE("gris y blanco"),
        BEIGE_Y_NEGRO("beige y negro"), BEIGE_Y_BLANCO("beige y blanco"),
        BLANCO_Y_NEGRO("blanco y negro"), BLANCO_Y_BEIGE("blanco y beige");
        private final String v; Color(String v){ this.v=v;} public String v(){return v;}}

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
        deleteAllAndInitializeAndSeedDataBase();
    }

    public void deleteAllAndInitializeAndSeedDataBase() {
        deleteAllAndInitialize();
        seedDataBaseJava();
    }

    private void deleteAllAndInitialize() {
        animalRepository.deleteAll();
        clientRepository.deleteAll();
        animalShelterRepository.deleteAll();
        adoptionRepository.deleteAll();
        LogManager.getLogger(this.getClass()).warn("------- Delete All -----------");
    }

    private void seedDataBaseJava() {
        LogManager.getLogger(this.getClass()).warn("------- Initial Load from JAVA -----------");

        Animal[] animals = {
                Animal.builder().id("128").name("Brenda")
                        .color(Color.MARRON.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Tranquila y muy sociable con niños, ideal para familias que buscan una perrita equilibrada y afectuosa")
                        .birth_date("02-02-2020").entryDate("17-10-2024")
                        .sex(Sex.HEMBRA.v()).age(5).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Brenda", "jpg")).build(),
                Animal.builder().id("129").name("Dakota")
                        .color(Color.BEIGE.v()).size(Size.PEQUENO.v()).race(Race.MESTIZO.v())
                        .description("Juguetona, curiosa y siempre en movimiento, perfecta para hogares activos")
                        .birth_date("02-02-2022").entryDate("13-08-2024")
                        .sex(Sex.HEMBRA.v()).age(3).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Dakota", "jpeg")).build(),
                Animal.builder().id("130").name("Dodo")
                        .color(Color.MARRON.v()).size(Size.GRANDE.v()).race(Race.MESTIZO.v())
                        .description("Con mucha energía y fuerza, necesita largos paseos diarios")
                        .birth_date("02-02-2023").entryDate("02-02-2025")
                        .sex(Sex.MACHO.v()).age(2).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Dodo", "jpeg")).build(),
                Animal.builder().id("131").name("Freddy")
                        .color(Color.BLANCO.v()).size(Size.GRANDE.v()).race(Race.MESTIZO.v())
                        .description("Noble y protector, disfruta de la tranquilidad del hogar")
                        .birth_date("23-04-2021").entryDate("28-12-2024")
                        .sex(Sex.MACHO.v()).age(4).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Freddy", "jpg")).build(),
                Animal.builder().id("132").name("Kalo")
                        .color(Color.MARRON.v()).size(Size.GRANDE.v()).race(Race.MESTIZO.v())
                        .description("Curioso y obediente, se adapta muy bien a nuevos entornos")
                        .birth_date("07-11-2023").entryDate("02-03-2025")
                        .sex(Sex.MACHO.v()).age(2).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Kalo", "webp")).build(),
                Animal.builder().id("133").name("Sheila")
                        .color(Color.BEIGE.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Activa y sociable, se adapta fácilmente a pesar de su discapacidad")
                        .birth_date("24-08-2023").entryDate("18-01-2025")
                        .sex(Sex.HEMBRA.v()).age(2).sterile(true).disability(true)
                        .imageUrl(imageService.loadImageBase64("Sheila", "webp")).build(),
                Animal.builder().id("134").name("Tobias")
                        .color(Color.GRAY_WHITE.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Cariñoso, algo tímido al principio, pero muy apegado cuando confía")
                        .birth_date("02-04-2020").entryDate("10-10-2024")
                        .sex(Sex.MACHO.v()).age(2).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Tobias", "jpg")).build(),
                Animal.builder().id("135").name("Nala")
                        .color(Color.MARRON.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Noble e inteligente, ideal para quienes buscan una compañera tranquila")
                        .birth_date("15-06-2019").entryDate("20-11-2024")
                        .sex(Sex.HEMBRA.v()).age(6).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Nala", "jpeg")).build(),
                Animal.builder().id("136").name("Max")
                        .color(Color.NEGRO.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Activo y alegre, siempre busca juegos y estímulos")
                        .birth_date("08-09-2021").entryDate("25-12-2024")
                        .sex(Sex.MACHO.v()).age(4).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Max", "jpg")).build(),
                Animal.builder().id("137").name("Pipa")
                        .color(Color.BLANCO.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Tierna y calmada, necesita cuidados especiales y mucho cariño")
                        .birth_date("22-03-2015").entryDate("10-09-2024")
                        .sex(Sex.HEMBRA.v()).age(10).sterile(true).disability(true)
                        .imageUrl(imageService.loadImageBase64("Pipa", "webp")).build(),
                Animal.builder().id("138").name("Rocky")
                        .color(Color.BEIGE_Y_NEGRO.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Fiel y equilibrado, con carácter estable ideal para adoptantes con experiencia")
                        .birth_date("05-12-2018").entryDate("30-09-2024")
                        .sex(Sex.MACHO.v()).age(7).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Rocky", "jpg")).build(),
                Animal.builder().id("139").name("Kai")
                        .color(Color.BLANCO_Y_NEGRO.v()).size(Size.GRANDE.v()).race(Race.MESTIZO.v())
                        .description("Sociable y con gran energía, perfecto para actividades al aire libre")
                        .birth_date("22-03-2020").entryDate("10-10-2024")
                        .sex(Sex.MACHO.v()).age(5).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Kai", "webp")).build(),
                Animal.builder().id("140").name("Susan")
                        .color(Color.BEIGE_Y_NEGRO.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Tranquila y fiel, disfruta de paseos cortos y lugares cálidos")
                        .birth_date("14-07-2014").entryDate("27-03-2025")
                        .sex(Sex.MACHO.v()).age(11).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Susan", "webp")).build(),
                Animal.builder().id("141").name("Coffe")
                        .color(Color.BEIGE.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Energética y afectuosa, mantiene una actitud muy positiva")
                        .birth_date("24-08-2023").entryDate("18-01-2025")
                        .sex(Sex.HEMBRA.v()).age(2).sterile(true).disability(true)
                        .imageUrl(imageService.loadImageBase64("Coffe", "jpg")).build(),
                Animal.builder().id("142").name("Chispa")
                        .color(Color.BLANCO_Y_BEIGE.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Vivaz y juguetona, perfecta para hogares con niños")
                        .birth_date("17-02-2020").entryDate("17-10-2024")
                        .sex(Sex.HEMBRA.v()).age(2).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Chispa", "jpg")).build(),
                Animal.builder().id("143").name("Clara")
                        .color(Color.MARRON.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Tranquila, leal y paciente, ideal para hogares serenos")
                        .birth_date("15-06-2019").entryDate("20-11-2024")
                        .sex(Sex.HEMBRA.v()).age(6).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Clara", "jpg")).build(),
                Animal.builder().id("144").name("Luna")
                        .color(Color.BLANCO.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Tierna y algo tímida, se abre con un trato respetuoso")
                        .birth_date("08-09-2021").entryDate("25-12-2024")
                        .sex(Sex.HEMBRA.v()).age(4).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Luna", "jpg")).build(),
                Animal.builder().id("145").name("Paco")
                        .color(Color.BLANCO.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Cariñoso y sereno, necesita un hogar donde lo cuiden con dedicación")
                        .birth_date("22-03-2015").entryDate("11-10-2024")
                        .sex(Sex.MACHO.v()).age(10).sterile(true).disability(true)
                        .imageUrl(imageService.loadImageBase64("Paco", "jpg")).build(),
                Animal.builder().id("146").name("Cristal")
                        .color(Color.BEIGE_Y_BLANCO.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Obediente y dulce, disfruta de la compañía humana y las rutinas")
                        .birth_date("05-07-2018").entryDate("22-09-2024")
                        .sex(Sex.HEMBRA.v()).age(7).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Cristal", "jpeg")).build(),
                Animal.builder().id("147").name("Marcus").color(Color.BEIGE.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Simpático y atento, le encanta aprender nuevos trucos")
                        .birth_date("22-03-2020").entryDate("18-10-2024")
                        .sex(Sex.MACHO.v()).age(5).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Marcus", "jpg")).build(),
                Animal.builder().id("148").name("Roco")
                        .color(Color.BEIGE_Y_NEGRO.v()).size(Size.GRANDE.v()).race(Race.MESTIZO.v())
                        .description("Sabio y calmado, busca una familia que le ofrezca un retiro digno")
                        .birth_date("18-07-2014").entryDate("27-02-2025")
                        .sex(Sex.MACHO.v()).age(11).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Roco", "jpg")).build(),
                Animal.builder().id("149").name("Melo")
                        .color(Color.BEIGE_Y_NEGRO.v()).size(Size.GRANDE.v()).race(Race.MESTIZO.v())
                        .description("Leal, paciente y muy afectuosa, ideal para un entorno sin muchas escaleras")
                        .birth_date("22-12-2018").entryDate("02-09-2024")
                        .sex(Sex.HEMBRA.v()).age(7).sterile(true).disability(true)
                        .imageUrl(imageService.loadImageBase64("Melo", "webp")).build(),
                Animal.builder().id("150").name("Truco")
                        .color(Color.BEIGE_Y_BLANCO.v()).size(Size.MEDIANO.v()).race(Race.MESTIZO.v())
                        .description("Sociable con otros perros y curioso, siempre dispuesto a explorar")
                        .birth_date("22-05-2020").entryDate("20-10-2024")
                        .sex(Sex.MACHO.v()).age(5).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Truco", "jpg")).build(),
                Animal.builder().id("151").name("Rayo")
                        .color(Color.BEIGE_Y_BLANCO.v()).size(Size.GRANDE.v()).race(Race.MESTIZO.v())
                        .description("Amigable y de ritmo pausado, un compañero tranquilo y agradecido")
                        .birth_date("19-07-2014").entryDate("27-05-2025")
                        .sex(Sex.MACHO.v()).age(11).sterile(true).disability(false)
                        .imageUrl(imageService.loadImageBase64("Rayo", "png")).build()
        };
        animalRepository.saveAll(List.of(animals));
        LogManager.getLogger(this.getClass()).warn("        ------- animals");

        Client[] clientsLogin = {
                Client.builder().name("Nico").lastName("Perez").username("Nico20").email("Nico20@gmail.com").password(ADMIN).animalWL(List.of(animals[0])).build(),
                Client.builder().name("Pablo").lastName("Reyes").username("PabloReyes30").email("PabloReyes30@gmail.com").password(ADMIN).build(),
                Client.builder().name("Paula").lastName("Regalado").username("PauRegalado01").email("PauRegalado01@gmail.com").password(ADMIN).build(),
                Client.builder().name("Camila").lastName("Tejero").username("Cami87").email("Cami87@gmail.com").password(ADMIN).build(),
                Client.builder().name("Nerea").lastName("Campos").username("NereaCampos99").email("NereaCampos99@gmail.com").password(ADMIN).build()
        };
        clientRepository.saveAll(List.of(clientsLogin));
        LogManager.getLogger(this.getClass()).warn("        ------- clients");

        AnimalShelter[] animalShelters = {
                AnimalShelter.builder().name("Rescue1")
                        .location("Cam. la Hoya del Granadillar, s/n, 38400 Cruz Santa, Santa Cruz de Tenerife")
                        .longitude(-16.6095418).latitude(28.3809799)
                        .username("rescue1").password(ADMIN)
                        .numberPhone(612345678)
                        .animalWL(List.of(animals[0], animals[1], animals[2], animals[3], animals[4]))
                        .imageUrl(imageService.loadImageBase64("Rescue1", "png")).build(),
                AnimalShelter.builder().name("Rescue2")
                        .location("Carr. Este, 38400 Puerto de la Cruz, Santa Cruz de Tenerife")
                        .longitude(-16.651466).latitude(28.3747234)
                        .username("rescue2").password(ADMIN)
                        .numberPhone(612345678)
                        .animalWL(List.of(animals[5], animals[6], animals[7], animals[8]))
                        .imageUrl(imageService.loadImageBase64("Rescue2", "jpg")).build(),
                AnimalShelter.builder().name("Rescue3")
                        .location("C. Cartagena, s/n, 38205 La Laguna, Santa Cruz de Tenerife")
                        .longitude(-16.4977802).latitude(28.4177782)
                        .username("rescue3").password(ADMIN)
                        .numberPhone(612345678)
                        .animalWL(List.of(animals[9], animals[10], animals[11], animals[12]))
                        .imageUrl(imageService.loadImageBase64("Rescue3", "jpg")).build(),
                AnimalShelter.builder().name("Rescue4")
                        .location("Camino las Samboas, 8, 38627 Arona, Santa Cruz de Tenerife")
                        .longitude(-16.7698601).latitude(28.0878755)
                        .username("rescue4").password(ADMIN)
                        .numberPhone(612345678)
                        .animalWL(List.of(animals[13], animals[14], animals[15], animals[16]))
                        .imageUrl(imageService.loadImageBase64("Rescue4", "jpg")).build(),
                AnimalShelter.builder().name("Rescue5")
                        .location("38611 Granadilla, Santa Cruz de Tenerife")
                        .longitude(-16.6503135).latitude(28.1209432)
                        .username("rescue5").password(ADMIN)
                        .numberPhone(612345678)
                        .animalWL(List.of(animals[17], animals[18], animals[19], animals[20], animals[21]))
                        .imageUrl(imageService.loadImageBase64("Rescue5", "jpg")).build()
        };
        animalShelterRepository.saveAll(List.of(animalShelters));
        LogManager.getLogger(this.getClass()).warn("        ------- animalShelters");

        Adoption[] adoptions = {
                Adoption.builder()
                        .animalShelter(animalShelters[0]).animal(animals[0]).client(clientsLogin[0])
                        .date(LocalDateTime.of(2025,3,15,10,0))
                        .status(AdoptionStatus.ACCEPTED)
                        .value(Value.builder()
                                .clientValue(ClientValue.builder()
                                        .stars(5).value("Todo fue perfecto, el refugio muy atento.").build())
                                .build())
                        .build(),
                Adoption.builder()
                        .animalShelter(animalShelters[0]).animal(animals[1]).client(clientsLogin[1])
                        .date(LocalDateTime.of(2025,2,20,16,30))
                        .status(AdoptionStatus.ACCEPTED)
                        .value(Value.builder()
                                .clientValue(ClientValue.builder()
                                        .stars(4).value("Muy buena experiencia, aunque hubo un poco de espera.").build())
                                .build())
                        .build(),
                Adoption.builder()
                        .animalShelter(animalShelters[0]).animal(animals[2]).client(clientsLogin[2])
                        .date(LocalDateTime.of(2025,1,5,12,15))
                        .status(AdoptionStatus.ACCEPTED)
                        .value(Value.builder()
                                .clientValue(ClientValue.builder()
                                        .stars(3).value("El proceso fue bien pero me costó contactar.").build())
                                .build())
                        .build()
        };
        adoptionRepository.saveAll(List.of(adoptions));
        LogManager.getLogger(this.getClass()).warn("        ------- adoptions");
    }
}
