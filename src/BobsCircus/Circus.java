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

import java.util.ArrayList;
import java.util.List;

public class Circus {
    private List<Animal>   animals;
    private List<Person>   persons;
    private List<Building> buildings;

    public Circus() {
        animals   = new ArrayList<>();
        persons   = new ArrayList<>();
        buildings = new ArrayList<>();
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void displayAllBuildings() {
        for (Building b : buildings) {
            System.out.println(b);
        }
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void displayAllPersons() {
        for (Person p : persons) {
            System.out.println(p);
        }
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void displayAllAnimals() {
        for (Animal a : animals) {
            System.out.println(a);
        }
    }

    public void sortAnimalsByAge() {
        int n = animals.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (animals.get(j).getAge() < animals.get(minIdx).getAge()) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                Animal temp = animals.get(i);
                animals.set(i, animals.get(minIdx));
                animals.set(minIdx, temp);
            }
        }
    }

    public void sortAnimalsByName() {
        int n = animals.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (animals.get(j).getName().compareToIgnoreCase(
                        animals.get(minIdx).getName()) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                Animal temp = animals.get(i);
                animals.set(i, animals.get(minIdx));
                animals.set(minIdx, temp);
            }
        }
    }

    public Animal searchAnimalByName(String name) {
        for (Animal a : animals) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public List<Animal>   getAnimals()   { return animals; }
    public List<Person>   getPersons()   { return persons; }
    public List<Building> getBuildings() { return buildings; }
}
