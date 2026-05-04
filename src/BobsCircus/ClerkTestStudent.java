package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ClerkTestStudent {

    private Clerk clerk;

    @BeforeEach
    public void setUp() {
        clerk = new Clerk("Alice", 30, 5, "Ticket Clerk", "images/TicketingCashier.jpg");
    }

    @Test public void testGetName()        { assertEquals("Alice",        clerk.getName()); }
    @Test public void testGetAge()         { assertEquals(30,              clerk.getAge()); }
    @Test public void testGetYearsWorked() { assertEquals(5,               clerk.getYearsWorked()); }
    @Test public void testGetJob()         { assertEquals("Ticket Clerk",  clerk.getJob()); }
    @Test public void testGetImagePath()   { assertEquals("images/TicketingCashier.jpg", clerk.getImagePath()); }

    @Test
    public void testToString() {
        String s = clerk.toString();
        assertTrue(s.startsWith("Clerk - "));
        assertTrue(s.contains("Alice"));
        assertTrue(s.contains("30"));
    }
}
