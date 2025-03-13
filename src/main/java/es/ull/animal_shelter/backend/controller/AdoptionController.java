package es.ull.animal_shelter.backend.controller;

import es.ull.animal_shelter.backend.controller.dto.AdoptionDetails;
import es.ull.animal_shelter.backend.model.Adoption;
import es.ull.animal_shelter.backend.service.AdoptionService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adoptions")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;

    @PostMapping
    public Adoption save(@RequestBody AdoptionDetails adoptiondetails) {
        //LogManager.getLogger(this.getClass()).warn(adoptiondetails.toString());
        return adoptionService.save(adoptiondetails);
    }

    @GetMapping
    public List<Adoption> findAll() {
        return adoptionService.findAll();
    }

    @GetMapping("/{id}")
    public Adoption findById(@PathVariable String id) {
        return adoptionService.findById(id);
    }

    @GetMapping("/animalShelter/{id}")
    public List<Adoption> findByAnimalShelterId(@PathVariable String id) {
        return adoptionService.findByAnimalShelterId(id);
    }

    @GetMapping("/client/{id}")
    public List<Adoption> findByClientId(@PathVariable String id) {
        return adoptionService.findByClientId(id);
    }

    @GetMapping("/{id}/confirm")
    public Adoption confirmAdoption(@PathVariable String id) {
        return adoptionService.confirmAdoptionRequest(id);
    }

    @GetMapping("/{id}/reject")
    public Adoption rejectAdoption(@PathVariable String id) {
        return adoptionService.rejectAdoptionRequest(id);
    }

}
