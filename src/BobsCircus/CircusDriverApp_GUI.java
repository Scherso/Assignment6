package BobsCircus;

/*
 * Class: CMSC203
 * Instructor: Grinberg
 * Description: CircusDriverApp_GUI - JavaFX driver class for Bob's Circus.
 *              Demonstrates all classes via a GUI menu with dialogs.
 * Due: 05/04/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 * Print your Name here: Sam Sporidis
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircusDriverApp_GUI extends Application {

    private Circus circus = new Circus();
    private VBox   animalListBox;
    private TextArea outputArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Bob's Circus Management System");

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(770);

        Button addAnimalBtn       = new Button("Add Animal");
        Button addPersonBtn       = new Button("Add Person");
        Button addBuildingBtn     = new Button("Add Building");
        Button ticketBtn          = new Button("Generate Ticket");
        Button displayAnimalsBtn  = new Button("Display All Animals");
        Button displayPersonsBtn  = new Button("Display All Persons");
        Button displayBuildingsBtn= new Button("Display All Buildings");
        Button sortAgeBtn         = new Button("Sort Animals by Age");
        Button sortNameBtn        = new Button("Sort Animals by Name");
        Button searchBtn          = new Button("Search Animal by Name");
        Button exitBtn            = new Button("Exit");

        FlowPane menu = new FlowPane(10, 10,
                addAnimalBtn, addPersonBtn, addBuildingBtn, ticketBtn,
                displayAnimalsBtn, displayPersonsBtn, displayBuildingsBtn,
                sortAgeBtn, sortNameBtn, searchBtn, exitBtn);
        menu.setAlignment(Pos.CENTER);

        VBox leftPanel = new VBox(10, menu, outputArea);
        leftPanel.setPrefWidth(600);

        animalListBox = new VBox(8);
        animalListBox.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(animalListBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(800);

        VBox imagePanel = new VBox(8, new Label("Image + Details"), scrollPane);
        imagePanel.setPrefHeight(850);
        imagePanel.setPadding(new Insets(10));
        imagePanel.setStyle("-fx-border-color: gray;");

        HBox root = new HBox(15, leftPanel, imagePanel);
        root.setPadding(new Insets(15));

        stage.setScene(new Scene(root, 900, 650));
        stage.show();

        addAnimalBtn.setOnAction(e -> handleAddAnimal());
        addPersonBtn.setOnAction(e -> handleAddPerson());
        addBuildingBtn.setOnAction(e -> handleAddBuilding());
        ticketBtn.setOnAction(e -> handleGenerateTicket());

        displayAnimalsBtn.setOnAction(e -> {
            displayAnimalsWithImages();
            outputArea.setText(captureConsole(circus::displayAllAnimals));
        });

        displayPersonsBtn.setOnAction(e -> {
            displayPersonsWithImages();
            outputArea.setText(captureConsole(circus::displayAllPersons));
        });

        displayBuildingsBtn.setOnAction(e -> {
            displayBuildingsWithImages();
            outputArea.setText(captureConsole(circus::displayAllBuildings));
        });

        sortAgeBtn.setOnAction(e -> {
            circus.sortAnimalsByAge();
            outputArea.setText("Animals sorted by age.");
        });

        sortNameBtn.setOnAction(e -> {
            circus.sortAnimalsByName();
            outputArea.setText("Animals sorted by name.");
        });

        searchBtn.setOnAction(e -> handleSearchAnimal());
        exitBtn.setOnAction(e -> stage.close());
    }

    // ===== CAPTURE CONSOLE OUTPUT =====
    private String captureConsole(Runnable action) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps  = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        action.run();
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }

    private void handleGenerateTicket() {
        animalListBox.getChildren().clear();
        StringBuilder allDetails = new StringBuilder();
        allDetails.append("Ticket Calculation Details:\n");
        allDetails.append("---------------------------\n");

        boolean moreTickets = true;
        while (moreTickets) {

            // Day selection
            List<String> dayChoices = new ArrayList<>();
            for (DayOfWeek d : DayOfWeek.values()) {
                int pct = (int) (d.getDiscount() * 100);
                dayChoices.add(d.name().charAt(0)
                        + d.name().substring(1).toLowerCase()
                        + " (" + pct + "% discount)");
            }
            ChoiceDialog<String> dayDialog = new ChoiceDialog<>(dayChoices.get(0), dayChoices);
            dayDialog.setHeaderText("Select Day of Week");
            Optional<String> dayOpt = dayDialog.showAndWait();
            if (dayOpt.isEmpty()) break;
            String day = dayOpt.get().split(" ")[0].toUpperCase();

            // Customer type
            List<String> customerChoices = List.of(
                    "Child (10% discount)",
                    "Student (10% discount)",
                    "Adult",
                    "Senior (5% discount)");
            ChoiceDialog<String> custDialog = new ChoiceDialog<>(customerChoices.get(0), customerChoices);
            custDialog.setHeaderText("Select Customer Type");
            Optional<String> custOpt = custDialog.showAndWait();
            if (custOpt.isEmpty()) break;

            double customerDiscount = switch (custOpt.get()) {
                case "Child (10% discount)", "Student (10% discount)" -> 0.10;
                case "Senior (5% discount)"                           -> 0.05;
                default                                               -> 0.0;
            };

            // Seat level
            List<String> seatChoices = List.of("Lower Level", "T-Level", "Upper Level");
            ChoiceDialog<String> seatDialog = new ChoiceDialog<>(seatChoices.get(0), seatChoices);
            seatDialog.setHeaderText("Select Seat Level");
            Optional<String> seatOpt = seatDialog.showAndWait();
            if (seatOpt.isEmpty()) break;
            String seatLevel = seatOpt.get();

            double basePrice   = inputDouble("Enter base price:");
            int    ticketCount = inputInt("Enter number of tickets:");

            double singlePrice = calculateTicketPrice(day, basePrice, seatLevel, customerDiscount);
            double totalPrice  = singlePrice * ticketCount;

            allDetails.append(getSingleTicketDetails(day, basePrice, seatLevel, customerDiscount));
            allDetails.append(String.format("Number of Tickets: %d%n", ticketCount));
            allDetails.append(String.format("Total Price: $%.2f%n", totalPrice));
            allDetails.append("---------------------------\n");

            Alert moreAlert = new Alert(Alert.AlertType.CONFIRMATION);
            moreAlert.setHeaderText("Do you want to buy more tickets?");
            moreAlert.setContentText("Click OK for Yes, Cancel for No");
            Optional<ButtonType> more = moreAlert.showAndWait();
            moreTickets = more.isPresent() && more.get() == ButtonType.OK;
        }

        allDetails.append("\nEnjoy the show!");
        outputArea.setText(allDetails.toString());
    }

    private void handleAddAnimal() {
        ChoiceDialog<String> dialog =
                new ChoiceDialog<>("Dog", "Dog", "Horse", "Bird", "Lion");
        dialog.setHeaderText("Select Animal Type");
        Optional<String> type = dialog.showAndWait();
        if (type.isEmpty()) return;

        String name      = inputText("Enter animal name:");
        int    age       = inputInt("Enter age:");
        String species   = inputText("Enter species:");
        String color     = inputText("Enter color:");
        String imagePath = inputText("Enter image file path (e.g. images/Dog.jpg):");

        Animal animal = switch (type.get()) {
            case "Dog"   -> new Dog(name, age, species, color, imagePath);
            case "Horse" -> new Horse(name, age, species, color, imagePath);
            case "Bird"  -> new Bird(name, age, species, color, imagePath);
            case "Lion"  -> new Lion(name, age, species, color, imagePath);
            default      -> null;
        };

        if (animal != null) {
            circus.addAnimal(animal);
            outputArea.setText("Animal added:\n" + animal);
        }
    }

    // ===== ADD PERSON =====
    private void handleAddPerson() {
        ChoiceDialog<String> dialog =
                new ChoiceDialog<>("Clerk", "Clerk", "Acrobatic");
        dialog.setHeaderText("Select Person Type");
        Optional<String> type = dialog.showAndWait();
        if (type.isEmpty()) return;

        String name        = inputText("Enter name:");
        int    age         = inputInt("Enter age:");
        int    yearsWorked = inputInt("Enter years worked:");
        String job         = inputText("Enter job title:");
        String imagePath   = inputText("Enter image file path (e.g. images/Person.jpg):");

        Person person = switch (type.get()) {
            case "Clerk"     -> new Clerk(name, age, yearsWorked, job, imagePath);
            case "Acrobatic" -> new Acrobatic(name, age, yearsWorked, job, imagePath);
            default          -> null;
        };

        if (person != null) {
            circus.addPerson(person);
            outputArea.setText("Person added:\n" + person);
        }
    }

    private void handleAddBuilding() {
        ChoiceDialog<String> dialog =
                new ChoiceDialog<>("Arena", "Arena", "Ticketing Office");
        dialog.setHeaderText("Select Building Type");
        Optional<String> type = dialog.showAndWait();
        if (type.isEmpty()) return;

        String color     = inputText("Enter color:");
        double length    = inputDouble("Enter length:");
        double width     = inputDouble("Enter width:");
        String imagePath = inputText("Enter image file path (e.g. images/Arena.jpg):");

        Building building = switch (type.get()) {
            case "Arena"           -> new Arena(color, length, width, imagePath);
            case "Ticketing Office"-> new TicketingOffice(color, length, width, imagePath);
            default                -> null;
        };

        if (building != null) {
            circus.addBuilding(building);
            outputArea.setText("Building added:\n" + building);
        }
    }

    private void displayAnimalsWithImages() {
        animalListBox.getChildren().clear();
        for (Animal animal : circus.getAnimals()) {
            VBox card = buildCard(animal.getImagePath(), animal.toString());
            animalListBox.getChildren().add(card);
        }
    }

    private void displayPersonsWithImages() {
        animalListBox.getChildren().clear();
        for (Person person : circus.getPersons()) {
            VBox card = buildCard(person.getImagePath(), person.toString());
            animalListBox.getChildren().add(card);
        }
    }

    private void displayBuildingsWithImages() {
        animalListBox.getChildren().clear();
        for (Building building : circus.getBuildings()) {
            VBox card = buildCard(building.getImagePath(), building.toString());
            animalListBox.getChildren().add(card);
        }
    }

    // ===== SEARCH ANIMAL =====
    private void handleSearchAnimal() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Search Animal by Name");
        dialog.setContentText("Enter animal name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) return;

        Animal found = circus.searchAnimalByName(result.get());
        if (found != null) {
            outputArea.setText("Found:\n" + found);
            animalListBox.getChildren().clear();
            animalListBox.getChildren().add(buildCard(found.getImagePath(), found.toString()));
        } else {
            outputArea.setText("Animal not found: " + result.get());
            animalListBox.getChildren().clear();
        }
    }

    private double calculateTicketPrice(String dayOfWeek, double basePrice,
                                        String seatLevel, double customerDiscount) {
        double dayDiscount = 0.0;
        for (DayOfWeek d : DayOfWeek.values()) {
            if (d.name().equalsIgnoreCase(dayOfWeek)) {
                dayDiscount = d.getDiscount();
                break;
            }
        }

        double seatMultiplier = switch (seatLevel) {
            case "T-Level"    -> 1.5;
            case "Upper Level"-> 2.0;
            default           -> 1.0;   // Lower Level
        };

        double price = basePrice * seatMultiplier;
        price -= price * dayDiscount;
        price -= price * customerDiscount;
        return price;
    }

    private String getSingleTicketDetails(String dayOfWeek, double basePrice,
                                          String seatLevel, double customerDiscount) {
        double dayDiscount = 0.0;
        for (DayOfWeek d : DayOfWeek.values()) {
            if (d.name().equalsIgnoreCase(dayOfWeek)) {
                dayDiscount = d.getDiscount();
                break;
            }
        }

        double seatMultiplier = switch (seatLevel) {
            case "T-Level"    -> 1.5;
            case "Upper Level"-> 2.0;
            default           -> 1.0;
        };

        double afterSeat     = basePrice * seatMultiplier;
        double afterDay      = afterSeat  - afterSeat  * dayDiscount;
        double finalPrice    = afterDay   - afterDay   * customerDiscount;

        return String.format(
                "Day: %s (%d%% discount)%n" +
                "Seat Level: %s (x%.1f)%n" +
                "Customer Discount: %.0f%%%n" +
                "Base Price: $%.2f%n" +
                "After Seat Multiplier: $%.2f%n" +
                "After Day Discount: $%.2f%n" +
                "Single Ticket Price: $%.2f%n",
                dayOfWeek, (int)(dayDiscount * 100),
                seatLevel, seatMultiplier,
                customerDiscount * 100,
                basePrice, afterSeat, afterDay, finalPrice);
    }

    private VBox buildCard(String imagePath, String labelText) {
        VBox card = new VBox(4);
        card.setStyle("-fx-border-color: lightgray; -fx-padding: 6; -fx-border-radius: 4;");
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            ImageView iv = new ImageView(new Image(imgFile.toURI().toString()));
            iv.setFitWidth(120);
            iv.setPreserveRatio(true);
            card.getChildren().add(iv);
        }
        card.getChildren().add(new Label(labelText));
        return card;
    }

    // ===== INPUT HELPERS =====
    private String inputText(String prompt) {
        TextInputDialog d = new TextInputDialog();
        d.setHeaderText(prompt);
        return d.showAndWait().orElse("");
    }

    private int inputInt(String prompt) {
        while (true) {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText(prompt);
            Optional<String> r = d.showAndWait();
            if (r.isEmpty()) return 0;
            try {
                return Integer.parseInt(r.get().trim());
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Enter a valid integer.").showAndWait();
            }
        }
    }

    private double inputDouble(String prompt) {
        while (true) {
            TextInputDialog d = new TextInputDialog();
            d.setHeaderText(prompt);
            Optional<String> r = d.showAndWait();
            if (r.isEmpty()) return 0.0;
            try {
                return Double.parseDouble(r.get().trim());
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Enter a valid number.").showAndWait();
            }
        }
    }
}
