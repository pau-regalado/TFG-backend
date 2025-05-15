package es.ull.animal_shelter.backend.service;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.repository.AnimalRepository;

@AllArgsConstructor
@Service
public class AnimalService {

	private AnimalRepository animalRepository;
	 
	public Animal save(Animal animal) {
		animal.setId(UUID.randomUUID().toString());
		return animalRepository.save(animal);
	}
	
	public List<Animal> findAll() {
		return animalRepository.findAll();
	}
	
	public Animal findById(String id) {
		return animalRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal no encontrado con ID: " + id));
	}

	public void deleteById(String id) {
		animalRepository.deleteById(id);
	}
}
