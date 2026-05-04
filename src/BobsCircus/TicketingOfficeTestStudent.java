package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TicketingOfficeTestStudent {

    private TicketingOffice office;

    @BeforeEach
    public void setUp() {
        office = new TicketingOffice("Blue", 30.0, 20.0, "images/TicketOffice.jpg");
    }

    @Test public void testGetColor()        { assertEquals("Blue",             office.getColor()); }
    @Test public void testGetLength()       { assertEquals(30.0,               office.getLength()); }
    @Test public void testGetWidth()        { assertEquals(20.0,               office.getWidth()); }
    @Test public void testGetBuildingType() { assertEquals("Ticketing Office", office.getBuildingType()); }
    @Test public void testGetImagePath()    { assertEquals("images/TicketOffice.jpg", office.getImagePath()); }

    @Test
    public void testSetColor() {
        office.setColor("Green");
        assertEquals("Green", office.getColor());
    }

    @Test
    public void testSetSize() {
        office.setSize(60.0, 40.0);
        assertEquals(60.0, office.getLength());
        assertEquals(40.0, office.getWidth());
    }

    @Test
    public void testSetBuildingType() {
        office.setBuildingType("MainOffice");
        assertEquals("MainOffice", office.getBuildingType());
    }

    @Test
    public void testToString() {
        assertTrue(office.toString().contains("Ticketing Office"));
        assertTrue(office.toString().contains("Blue"));
        assertTrue(office.toString().contains("30.0"));
    }
}
