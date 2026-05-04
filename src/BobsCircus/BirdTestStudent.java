package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.*;

public class BirdTestStudent {

    private Bird bird;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        bird = new Bird("Tweety", 2, "Canary", "Yellow", "images/Bird.jpg");
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outputStream.reset();
    }

    @Test public void testGetName()      { assertEquals("Tweety",  bird.getName()); }
    @Test public void testGetAge()       { assertEquals(2,           bird.getAge()); }
    @Test public void testGetSpecies()   { assertEquals("Canary",   bird.getSpecies()); }
    @Test public void testGetColor()     { assertEquals("Yellow",   bird.getColor()); }
    @Test public void testGetImagePath() { assertEquals("images/Bird.jpg", bird.getImagePath()); }

    @Test
    public void testToString() {
        assertEquals("Bird [Name: Tweety, Age: 2, Species: Canary, Color: Yellow]",
                bird.toString());
    }

    @Test
    public void testMakeSound() {
        bird.makeSound();
        assertTrue(outputStream.toString().contains("Tweet!"));
    }

    @Test
    public void testMove() {
        bird.move();
        assertTrue(outputStream.toString().contains("Flies."));
    }
}
