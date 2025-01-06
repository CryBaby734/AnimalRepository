package org.example.appcrud;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor

public class AnimalService {
    @Autowired
   private AnimalRepository animalRepository;

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Object updateAnimal(Long id, Animal animaldetails) {
        Animal animalToUpdate = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        animalToUpdate.setName(animaldetails.getName());
        animalToUpdate.setType(animaldetails.getType());
        return animalRepository.save(animalToUpdate);

    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
