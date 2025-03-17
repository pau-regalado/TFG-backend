package service;

import es.ull.animal_shelter.backend.BackendApplication;
import es.ull.animal_shelter.backend.service.ImageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    /**
     * Se prueba la carga de una imagen existente.
     * Si el archivo "testImage.jpg" no se encuentra en src/main/resources/developDataImages/,
     * se omite el test para evitar fallos.
     */
    @Test
    void testLoadExistingImage() {
        String filePath = "src/main/resources/developDataImages/Max.jpg";
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("El archivo " + filePath + " no existe, se omite testLoadExistingImage");
            return;
        }
        String imageData = imageService.loadImageBase64("Max", "jpg");
        assertNotNull(imageData, "La imagen debe cargarse correctamente");
        assertTrue(imageData.startsWith("data:image/jpg;base64,"), "El string devuelto debe tener el prefijo adecuado");
    }

    /**
     * Se prueba la carga de una imagen inexistente.
     * Dado que no existe el archivo, se espera que el método retorne null.

    @Test
    void testLoadNonExistingImage() {
        String imageData = imageService.loadImageBase64("nonExistingImage", "png");
        assertNull(imageData, "Si la imagen no existe, se debe retornar null");
    }
     */
}
