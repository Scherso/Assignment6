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


public class Arena implements Building {
    private String color;
    private double length;
    private double width;
    private String buildingType;
    private String imagePath;

    public Arena(String color, double length, double width, String imagePath) {
        this.color = color;
        this.length = length;
        this.width = width;
        this.buildingType = "Arena";
        this.imagePath = imagePath;
    }

    @Override
    public void setSize(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getLength()              { return length; }

    @Override
    public double getWidth()               { return width; }

    @Override
    public void setColor(String color)     { this.color = color; }

    @Override
    public String getColor()               { return color; }

    @Override
    public void setBuildingType(String type) { this.buildingType = type; }

    @Override
    public String getBuildingType()        { return buildingType; }

    @Override
    public String getImagePath()           { return imagePath; }

    @Override
    public String toString() {
        return String.format("\nBuilding Type: %s \nColor: %s \nSize: %.1f x %.1f\n",
                buildingType, color, length, width);
    }
}
