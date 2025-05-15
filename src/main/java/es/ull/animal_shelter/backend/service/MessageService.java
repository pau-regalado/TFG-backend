package es.ull.animal_shelter.backend.service;

import es.ull.animal_shelter.backend.model.Message;
import es.ull.animal_shelter.backend.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MessageService {

    private MessageRepository messageRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
