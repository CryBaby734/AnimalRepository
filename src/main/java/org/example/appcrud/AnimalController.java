package org.example.appcrud;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/animals")
public class AnimalController {


    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> getAll() {
        return animalService.getAllAnimals();
    }

    @PostMapping
    public Animal create(@RequestBody Animal animal) {
        return animalService.createAnimal(animal);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Animal animaldetails) {
        return ResponseEntity.ok(animalService.updateAnimal(id, animaldetails));
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
         animalService.deleteAnimal(id);
    }

}
