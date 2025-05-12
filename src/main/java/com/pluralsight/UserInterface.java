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
                    processGetByPrice();
                    break;
                case 2:
                    processGetByMakeModel();
                    break;
                case 3:
                    processGetByYear();
                    break;
                case 4:
                    processGetByColor();
                    break;
                case 5:
                    processGetByMileage();
                    break;
                case 6:
                    processGetByType();
                    break;
                case 7:
                    processAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicle();
                    break;
                case 9:
                    processRemoveVehicle();
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
        System.out.println("1 - Search by price range");
        System.out.println("2 - Search by make and model");
        System.out.println("3 - Search by year range");
        System.out.println("4 - Search by color");
        System.out.println("5 - Search by mileage range");
        System.out.println("6 - Search by vehicle type");
        System.out.println("7 - List all vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
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
    private void processAddVehicle() {
        try {
            System.out.print("Enter VIN: ");
            int vin = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter year: ");
            int year = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter make: ");
            String make = scanner.nextLine().trim();

            System.out.print("Enter model: ");
            String model = scanner.nextLine().trim();

            System.out.print("Enter vehicle type (CAR, TRUCK, SUV, VAN): ");
            VehicleType type = VehicleType.fromString(scanner.nextLine().trim());

            System.out.print("Enter color: ");
            String color = scanner.nextLine().trim();

            System.out.print("Enter odometer reading: ");
            int odometer = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
            dealership.addVehicle(vehicle);

            DealershipFileManager fileManager = new DealershipFileManager();
            fileManager.saveDealership(dealership);

            System.out.println("Vehicle added successfully!");

        } catch (Exception e) { //error message
            System.out.println("Error: " + e.getMessage());
        }
    }
    //Remove a vehicle
    private void processRemoveVehicle() {
        System.out.print("Enter VIN of the vehicle to remove: ");
        try {
            int vin = Integer.parseInt(scanner.nextLine().trim());
            Vehicle toRemove = null;  // temporary variable is created to hold the reference to the vehicle we want to remove — set to null initially.

            for (Vehicle v : dealership.getAllVehicles()) {
                if (v.getVin() == vin) {  //Checks if the VIN of the current vehicle matches the VIN entered by the user.
                    toRemove = v; //store that matching vehicle to toRemove variable
                    break; //stop check
                }
            }

            if (toRemove != null) {  //if vehicle was found to remove run the if loop
                dealership.removeVehicle(toRemove);  //remove the vehicle

                DealershipFileManager fileManager = new DealershipFileManager(); //creates a new dealershipfilemanager instance
                fileManager.saveDealership(dealership); //updates the dealership inventory so that the csv is updated

                System.out.println("Vehicle removed successfully.");
            } else {
                System.out.println("Vehicle with that VIN not found.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
        }
    }

}
