package org.example.appcrud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    private Animal animal;
    private Animal updatedAnimal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        animal = new Animal();
        animal.setId(1L);
        animal.setName("Lion");
        animal.setType("Mammal");

        updatedAnimal = new Animal();
        updatedAnimal.setName("Tiger");
        updatedAnimal.setType("Mammal");
    }

    @Test
    void testGetAllAnimals() {
        // Arrange
        when(animalRepository.findAll()).thenReturn(List.of(animal));

        // Act
        List<Animal> animals = animalService.getAllAnimals();

        // Assert
        assertNotNull(animals);
        assertEquals(1, animals.size());
        assertEquals("Lion", animals.get(0).getName());
    }

    @Test
    void testCreateAnimal() {
        // Arrange
        when(animalRepository.save(animal)).thenReturn(animal);

        // Act
        Animal createdAnimal = animalService.createAnimal(animal);

        // Assert
        assertNotNull(createdAnimal);
        assertEquals("Lion", createdAnimal.getName());
        assertEquals("Mammal", createdAnimal.getType());
    }

    @Test
    void testUpdateAnimal() {
        // Arrange
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
        when(animalRepository.save(any(Animal.class))).thenReturn(updatedAnimal);

        // Act
        Animal updated = animalService.updateAnimal(1L, updatedAnimal);

        // Assert
        assertNotNull(updated);
        assertEquals("Tiger", updated.getName());
        assertEquals("Mammal", updated.getType());
    }

    @Test
    void testDeleteAnimal() {
        // Arrange
        doNothing().when(animalRepository).deleteById(1L);

        // Act
        animalService.deleteAnimal(1L);

        // Assert
        verify(animalRepository, times(1)).deleteById(1L);
    }
}
