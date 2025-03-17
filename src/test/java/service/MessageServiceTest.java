package service;

import es.ull.animal_shelter.backend.BackendApplication;
import es.ull.animal_shelter.backend.model.Message;
import es.ull.animal_shelter.backend.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    void testSaveMessage() {
        // Se crea un mensaje sin ID predefinido
        Message message = Message.builder()
                .message("Hello, this is a test message.")
                .userType(true)
                .build();

        // Se guarda el mensaje y se obtiene el mensaje guardado
        Message savedMessage = messageService.save(message);

        // Se validan los resultados
        assertNotNull(savedMessage);
        // Se comprueba que se ha asignado un ID al mensaje
        assertNotNull(savedMessage.getId());
        assertEquals("Hello, this is a test message.", savedMessage.getMessage());
        assertTrue(savedMessage.isUserType());
    }
}

