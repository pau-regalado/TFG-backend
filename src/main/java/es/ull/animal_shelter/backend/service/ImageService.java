package es.ull.animal_shelter.backend.service;

import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@NoArgsConstructor
public class ImageService {
    private static final String imageBasePath = "src/main/resources/developDataImages/";

    public String loadImageBase64(String imagePath, String extension) {
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(imageBasePath + imagePath + "." + extension));
            return "data:image/" + extension + ";base64," + Base64.getEncoder().encodeToString(fileContent);
        } catch (NoSuchFileException e) {
            LogManager.getLogger(getClass()).warn("Imagen no encontrada: " + imagePath + "." + extension);
            return null;
        } catch (IOException e) {
            LogManager.getLogger(getClass()).error("Error al cargar la imagen: " + imagePath, e);
            return null;
        }
    }
}
