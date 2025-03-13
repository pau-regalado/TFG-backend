package es.ull.animal_shelter.backend.service;

import es.ull.animal_shelter.backend.model.Value;
import es.ull.animal_shelter.backend.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValueService {

    @Autowired
    private ValueRepository valueRepository;

    public Value updateEvaluation(Value value) {
        // Verifica que se proporcione una adopción en la evaluación.
        if (value.getAdoption() == null || value.getAdoption().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Se requiere la referencia a la adopción.");
        }
        // Si ya existe una evaluación para esta adopción, se actualiza; si no, se crea una nueva.
        Value existingValue = valueRepository.findByAdoptionId(value.getAdoption().getId());
        if (existingValue != null) {
            existingValue.setStars(value.getStars());
            existingValue.setValueClient(value.getValueClient());
            existingValue.setValueAnimalShelter(value.getValueAnimalShelter());
            return valueRepository.save(existingValue);
        } else {
            return valueRepository.save(value);
        }
    }

    public Value getEvaluationByAdoptionId(String id) {
        return valueRepository.findByAdoptionId(id);
    }
}
