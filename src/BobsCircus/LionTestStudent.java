package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.*;

public class LionTestStudent {

    private Lion lion;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        lion = new Lion("Simba", 5, "Panthera leo", "Golden", "images/Animal.jpg");
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outputStream.reset();
    }

    @Test public void testGetName()      { assertEquals("Simba",        lion.getName()); }
    @Test public void testGetAge()       { assertEquals(5,                lion.getAge()); }
    @Test public void testGetSpecies()   { assertEquals("Panthera leo",  lion.getSpecies()); }
    @Test public void testGetColor()     { assertEquals("Golden",        lion.getColor()); }
    @Test public void testGetImagePath() { assertEquals("images/Animal.jpg", lion.getImagePath()); }

    @Test
    public void testToString() {
        assertEquals("Lion [Name: Simba, Age: 5, Species: Panthera leo, Color: Golden]",
                lion.toString());
    }

    @Test
    public void testMakeSound() {
        lion.makeSound();
        assertTrue(outputStream.toString().contains("Roar!"));
    }

    @Test
    public void testMove() {
        lion.move();
        assertTrue(outputStream.toString().contains("Prowls."));
    }
}
