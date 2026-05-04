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


public enum DayOfWeek {
    MONDAY(0.10),
    TUESDAY(0.10),
    WEDNESDAY(0.10),
    THURSDAY(0.10),
    FRIDAY(0.10),
    SATURDAY(0.0),
    SUNDAY(0.0);

    private final double discount;

    DayOfWeek(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
