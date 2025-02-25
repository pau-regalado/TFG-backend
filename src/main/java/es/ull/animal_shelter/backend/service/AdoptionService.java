package es.ull.animal_shelter.backend.service;

import es.ull.animal_shelter.backend.model.Adoption;
import es.ull.animal_shelter.backend.repository.AdoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AdoptionService {
    @Autowired
    private AdoptionRepository adoptionRepository;

    public Adoption save(Adoption adoption) {
        adoption.setId(UUID.randomUUID().toString());
        return adoptionRepository.save(adoption);
    }

    public List<Adoption> findAll() {
        return adoptionRepository.findAll();
    }

    public Adoption findById(String id) {
        return adoptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Adoption no encontrado con ID: " + id));
    }
}
