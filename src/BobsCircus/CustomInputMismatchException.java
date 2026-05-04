package BobsCircus;

/*
 * Class: CMSC203
 * Instructor: Grinberg
 * Description:
 * Due: 05/04/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 * Print your Name here: Sam Sporidis
 */

import java.util.InputMismatchException;

public class CustomInputMismatchException extends InputMismatchException {
    public CustomInputMismatchException(String message) {
        super(message);
    }
}
