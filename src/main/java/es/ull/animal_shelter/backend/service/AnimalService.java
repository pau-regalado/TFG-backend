package es.ull.animal_shelter.backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.repository.AnimalRepository;


@Service
public class AnimalService {
	@Autowired
	private AnimalRepository animalRepository;
	 
	public Animal save(Animal animal) {
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
