package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.*;

public class DogJUnitTest {

    private Dog dog;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        dog = new Dog("Buddy", 3, "Canine", "Black", "images/Dog.jpg");
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outputStream.reset();
    }

    @Test public void testGetName()      { assertEquals("Buddy",  dog.getName()); }
    @Test public void testGetAge()       { assertEquals(3,         dog.getAge()); }
    @Test public void testGetSpecies()   { assertEquals("Canine",  dog.getSpecies()); }
    @Test public void testGetColor()     { assertEquals("Black",   dog.getColor()); }
    @Test public void testGetImagePath() { assertEquals("images/Dog.jpg", dog.getImagePath()); }

    @Test
    public void testToString() {
        assertEquals("Dog [Name: Buddy, Age: 3, Species: Canine, Color: Black]",
                dog.toString());
    }

    @Test
    public void testMakeSound() {
        dog.makeSound();
        assertEquals("Bark!" + System.lineSeparator(), outputStream.toString());
    }

    @Test
    public void testMove() {
        dog.move();
        String output = outputStream.toString().replace("\r\n", "\n");
        assertEquals("Walks around.\n\n", output);
    }
}
