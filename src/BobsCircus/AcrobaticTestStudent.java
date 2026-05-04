package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class AcrobaticTestStudent {

    private Acrobatic acrobatic;

    @BeforeEach
    public void setUp() {
        acrobatic = new Acrobatic("Bob", 25, 3, "Acrobat", "images/acrobat.jpg");
    }

    @Test public void testGetName()        { assertEquals("Bob",      acrobatic.getName()); }
    @Test public void testGetAge()         { assertEquals(25,          acrobatic.getAge()); }
    @Test public void testGetYearsWorked() { assertEquals(3,           acrobatic.getYearsWorked()); }
    @Test public void testGetJob()         { assertEquals("Acrobat",   acrobatic.getJob()); }
    @Test public void testGetImagePath()   { assertEquals("images/acrobat.jpg", acrobatic.getImagePath()); }

    @Test
    public void testToString() {
        String s = acrobatic.toString();
        assertTrue(s.startsWith("Acrobatic - "));
        assertTrue(s.contains("Bob"));
        assertTrue(s.contains("25"));
    }
}
