/* Dealership holds information about t the dealership (name, address, …)
and maintain a list of vehicles. Since it has the list of vehicles, it will also
have the methods that search the list for matching vehicles as well as
add/remove vehicles.
  **/
package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle>inventory;

    // Constructor
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    //Getter and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //Get vehicles by price method
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> results = new ArrayList<>(); //new list is created for the results
        for (Vehicle v : inventory) { //check very vehicle in our inventory list if there is a vehicle in the price range add it our new results array
            if (v.getPrice() >= min && v.getPrice() <= max) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getMake().equalsIgnoreCase(make) &&
                    v.getModel().equalsIgnoreCase(model)) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= min && v.getYear() <= max) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByType(VehicleType vehicleType) {
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getVehicleType() == vehicleType) {
                results.add(v);
            }
        }
        return results;
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }
}

