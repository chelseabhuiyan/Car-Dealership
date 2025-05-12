/** UserInterface will be responsible for all output to the screen, reading of
 user input, and "dispatching" of the commands to the Dealership as
 needed. (ex: when the user selects "List all Vehicles", UserInterface would
 call the appropriate Dealership method and then display the vehicles it
 returns.) **/

package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        init(); // Load dealership from file

        boolean running = true;
        while (running) {
            displayMenu();  //displays the menu
            int choice = Integer.parseInt(scanner.nextLine());  //read the user command

            switch (choice) {
                case 1:
                    processAllVehiclesRequest();
                    break;
                case 2:
                    processGetByPrice();
                    break;
                case 3:
                    processGetByMakeModel();
                    break;
                case 4:
                    processGetByYear();
                    break;
                case 5:
                    processGetByColor();
                    break;
                case 6:
                    processGetByMileage();
                    break;
                case 7:
                    processGetByType();
                    break;
                case 99:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    //Loads the dealership from the file
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
        if (dealership == null) {
            System.out.println("Failed to load dealership data.");
            System.exit(1);
        }
    }

    private void displayMenu() {
        System.out.println(" --Car Dealership Menu--");
        System.out.println("1 - List all vehicles");
        System.out.println("2 - Search by price range");
        System.out.println("3 - Search by make and model");
        System.out.println("4 - Search by year range");
        System.out.println("5 - Search by color");
        System.out.println("6 - Search by mileage range");
        System.out.println("7 - Search by vehicle type");
        System.out.println("99 - Quit");
        System.out.print("Enter your choice: ");
    }

    private void processAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    private void processGetByPrice() {
        System.out.print("Enter minimum price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter maximum price: ");
        double max = Double.parseDouble(scanner.nextLine());

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByMakeModel() {
        System.out.print("What is the make: ");
        String make = scanner.nextLine();
        System.out.print("What is the model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    private void processGetByYear() {
        System.out.print("Enter minimum year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter maximum year: ");
        int max = Integer.parseInt(scanner.nextLine());

        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByColor() {
        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    private void processGetByMileage() {
        System.out.print("Enter the minimum mileage: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the maximum mileage: ");
        int max = Integer.parseInt(scanner.nextLine());

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByType() {
        System.out.print("Enter vehicle type (CAR, TRUCK, SUV, VAN): ");
        String input = scanner.nextLine();
        try {
            VehicleType type = VehicleType.fromString(input);
            List<Vehicle> vehicles = dealership.getVehiclesByType(type);
            displayVehicles(vehicles);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid vehicle type.");
        }
    }


    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles were found.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }
}
