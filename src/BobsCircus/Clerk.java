package BobsCircus;

/*
 * Class: CMSC203
 * Instructor: Grinberg
 * Description: Clerk class extends the abstract Person class
 * Due: 05/04/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 * Print your Name here: Sam Sporidis
 */

public class Clerk extends Person {

    public Clerk(String name, int age, int yearsWorked, String job, String imagePath) {
        super(name, age, yearsWorked, job, imagePath);
    }

    @Override
    public String toString() {
        return "Clerk - " + super.toString();
    }
}
