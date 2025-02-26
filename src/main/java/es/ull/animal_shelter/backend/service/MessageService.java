package es.ull.animal_shelter.backend.service;

import es.ull.animal_shelter.backend.model.Message;
import es.ull.animal_shelter.backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }

}
