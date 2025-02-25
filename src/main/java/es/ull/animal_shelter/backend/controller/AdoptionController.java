package es.ull.animal_shelter.backend.controller;


import es.ull.animal_shelter.backend.model.Adoption;
import es.ull.animal_shelter.backend.model.Animal;
import es.ull.animal_shelter.backend.service.AnimalService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/adoptions")
public class AdoptionController {

    //@Autowired
    //private AdoptionService animalService;

    @PostMapping
    public Adoption save(@RequestBody Adoption adoption) {
        LogManager.getLogger(this.getClass()).warn(adoption.toString());
        //adoptionService.save(adoption);
        return adoption;
    }

    @GetMapping("/{id}")
    public Adoption findById(@PathVariable String id) {
        //return adoptionService.findById(id);
    }

}
