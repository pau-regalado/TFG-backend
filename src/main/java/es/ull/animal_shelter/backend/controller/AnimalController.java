package es.ull.animal_shelter.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.service.AnimalService;
//import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/animals")
//@RequiredArgsConstructor
public class AnimalController {
	@Autowired
	private AnimalService animalService;
	
	@PostMapping
	public Animal save(@RequestBody Animal animal) {
		animalService.save(animal);
		return animal;
	}
	
	@GetMapping
	public List<Animal> findAll() {
		animalService.findAll().stream().map(a -> a)
		.forEach(a -> System.out.print(a.toString()));
		return animalService.findAll();
	}
	
	@GetMapping("/{idAnimal}")
	public Animal findById(@PathVariable String id) {
		return animalService.findById(id);
	}
	
	@DeleteMapping("/{idAnimal}")
	public void deleteById(@PathVariable String id) {
		animalService.deleteById(id);
	}
	
	@PutMapping
	public void update(@RequestBody Animal animal) {
		animalService.save(animal);
	}

}
