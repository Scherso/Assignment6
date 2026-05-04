package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class CircusTestStudent {

    private Circus circus;

    @BeforeEach
    public void setUp() {
        circus = new Circus();
        circus.addAnimal(new Dog("Rex",   5, "Canine",       "Black",  "images/Dog.jpg"));
        circus.addAnimal(new Horse("Spirit", 3, "Mustang",   "Brown",  "images/Horse.jpg"));
        circus.addAnimal(new Lion("Simba", 7, "Panthera leo","Golden", "images/Animal.jpg"));
    }

    @Test
    public void testAddAnimal() {
        assertEquals(3, circus.getAnimals().size());
    }

    @Test
    public void testAddPerson() {
        circus.addPerson(new Clerk("Alice", 30, 5, "Clerk", "images/TicketingCashier.jpg"));
        assertEquals(1, circus.getPersons().size());
    }

    @Test
    public void testAddBuilding() {
        circus.addBuilding(new Arena("Red", 100, 50, "images/Arena.jpg"));
        assertEquals(1, circus.getBuildings().size());
    }

    @Test
    public void testSortAnimalsByAge() {
        circus.sortAnimalsByAge();
        assertEquals("Spirit", circus.getAnimals().get(0).getName()); // age 3
        assertEquals("Rex",    circus.getAnimals().get(1).getName()); // age 5
        assertEquals("Simba",  circus.getAnimals().get(2).getName()); // age 7
    }

    @Test
    public void testSortAnimalsByName() {
        circus.sortAnimalsByName();
        assertEquals("Rex",    circus.getAnimals().get(0).getName()); // R
        assertEquals("Simba",  circus.getAnimals().get(1).getName()); // S
        assertEquals("Spirit", circus.getAnimals().get(2).getName()); // S (i < p)
    }

    @Test
    public void testSearchAnimalByNameFound() {
        Animal found = circus.searchAnimalByName("Rex");
        assertNotNull(found);
        assertEquals("Rex", found.getName());
    }

    @Test
    public void testSearchAnimalByNameNotFound() {
        assertNull(circus.searchAnimalByName("Nemo"));
    }

    @Test
    public void testSearchCaseInsensitive() {
        assertNotNull(circus.searchAnimalByName("rex"));
        assertNotNull(circus.searchAnimalByName("SIMBA"));
    }
}
