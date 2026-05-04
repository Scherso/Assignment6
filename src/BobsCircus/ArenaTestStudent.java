package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ArenaTestStudent {

    private Arena arena;

    @BeforeEach
    public void setUp() {
        arena = new Arena("Red", 100.0, 50.0, "images/Arena.jpg");
    }

    @Test public void testGetColor()        { assertEquals("Red",    arena.getColor()); }
    @Test public void testGetLength()       { assertEquals(100.0,    arena.getLength()); }
    @Test public void testGetWidth()        { assertEquals(50.0,     arena.getWidth()); }
    @Test public void testGetBuildingType() { assertEquals("Arena",  arena.getBuildingType()); }
    @Test public void testGetImagePath()    { assertEquals("images/Arena.jpg", arena.getImagePath()); }

    @Test
    public void testSetColor() {
        arena.setColor("Blue");
        assertEquals("Blue", arena.getColor());
    }

    @Test
    public void testSetSize() {
        arena.setSize(200.0, 100.0);
        assertEquals(200.0, arena.getLength());
        assertEquals(100.0, arena.getWidth());
    }

    @Test
    public void testSetBuildingType() {
        arena.setBuildingType("BigArena");
        assertEquals("BigArena", arena.getBuildingType());
    }

    @Test
    public void testToString() {
        assertTrue(arena.toString().contains("Arena"));
        assertTrue(arena.toString().contains("Red"));
        assertTrue(arena.toString().contains("100.0"));
    }
}
