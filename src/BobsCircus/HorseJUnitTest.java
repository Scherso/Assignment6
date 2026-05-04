package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.*;

public class HorseJUnitTest {

    private Horse horse;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        horse = new Horse("Spirit", 4, "Mustang", "Brown", "images/Horse.jpg");
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outputStream.reset();
    }

    @Test public void testGetName()      { assertEquals("Spirit",  horse.getName()); }
    @Test public void testGetAge()       { assertEquals(4,           horse.getAge()); }
    @Test public void testGetSpecies()   { assertEquals("Mustang",  horse.getSpecies()); }
    @Test public void testGetColor()     { assertEquals("Brown",    horse.getColor()); }
    @Test public void testGetImagePath() { assertEquals("images/Horse.jpg", horse.getImagePath()); }

    @Test
    public void testToString() {
        assertEquals("Horse [Name: Spirit, Age: 4, Species: Mustang, Color: Brown]",
                horse.toString());
    }

    @Test
    public void testMakeSound() {
        horse.makeSound();
        assertTrue(outputStream.toString().contains("Neigh!"));
    }

    @Test
    public void testMove() {
        horse.move();
        assertTrue(outputStream.toString().contains("Gallops."));
    }
}
