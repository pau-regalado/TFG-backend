package es.ull.animal_shelter.backend.controller;

import es.ull.animal_shelter.backend.model.Value;
import es.ull.animal_shelter.backend.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/values")
public class ValueController {

    @Autowired
    private ValueService valueService;

    @PutMapping("/{adoptionId}")
    public Value updateEvaluation(@PathVariable String adoptionId, @RequestBody Value value) {
        if(value.getAdoption() == null || value.getAdoption().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La evaluación debe incluir la adopción.");
        }
        if(!adoptionId.equals(value.getAdoption().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID de adopción no coincide.");
        }
        return valueService.updateEvaluation(value);
    }
}
