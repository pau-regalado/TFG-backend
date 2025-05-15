package es.ull.animal_shelter.backend.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.service.AnimalService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AnimalController {

	private AnimalService animalService;
	
	@PostMapping("/animals")
	public Animal save(@RequestBody Animal animal) {
		animalService.save(animal);
		return animal;
	}
	
	@GetMapping("/animals")
	public List<Animal> findAll() {
		return animalService.findAll();
	}
	
	@GetMapping("/animals/{id}")
	public Animal findById(@PathVariable String id) {
		return animalService.findById(id);
	}
	
	@DeleteMapping("/animals/{id}")
	public void deleteById(@PathVariable String id) {
		animalService.deleteById(id);
	}

}
