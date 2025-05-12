/* DealershipFileManager will be responsible for reading the dealership file,
parsing the data, and creating a Dealership object full of vehicles from the
file. It will also be responsible for saving a dealership and the vehicles back
into the file in the same pipe-delimited format**/

package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class DealershipFileManager {

    //File that the dealership data is read from and saved to
    private static final String FILE_NAME = "inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null; //initializes a varibale to hold the Dealership object

        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {  //opens the file using scanner to read it line by line. try makes sure file is closed automatically after reading
            if (scanner.hasNextLine()) { //as long as the file isn't empty
                // First line: dealership info
                String[] headerLine = scanner.nextLine().split("\\|"); //split by the "|"
                String name = headerLine[0];    //first is the name
                String address = headerLine[1]; //second part is the address
                String phone = headerLine[2];   //third part is the phone number

                dealership = new Dealership(name, address, phone);   //create a new object with this info
            }

            // Remaining lines: vehicles
            while (scanner.hasNextLine()) {  //go to the second line
                String[] data = scanner.nextLine().split("\\|"); //split by "|"
                int vin = Integer.parseInt(data[0]);
                int year = Integer.parseInt(data[1]);
                String make = data[2];
                String model = data[3];
                VehicleType type = VehicleType.fromString(data[4]);
                String color = data[5];
                int odometer = Integer.parseInt(data[6]);
                double price = Double.parseDouble(data[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);  //create new vehicle object
                dealership.addVehicle(vehicle);   //add new vehicle to the inventory
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: inventory file not found.");  //display message if the csv file not found
        } catch (Exception e) {
            System.out.println("Error reading dealership file: " + e.getMessage()); //display message if dealership file not found
        }

        return dealership;    //return the populated dealership
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            // First line: dealership info
            writer.println(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());

            // Remaining lines: vehicle data
            for (Vehicle v : dealership.getAllVehicles()) {
                writer.printf("%d|%d|%s|%s|%s|%s|%d|%.2f%n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getVehicleType().name(), v.getColor(), v.getOdometer(), v.getPrice());
            }

        } catch (IOException e) {
            System.out.println("Error saving dealership file: " + e.getMessage());
        }
    }
}