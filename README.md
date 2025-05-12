
# Car Dealership Console Application

This is a Java-based console application for managing a car dealership's vehicle inventory. The program allows users to search for vehicles, add new vehicles, and remove vehicles from the inventory. All inventory data is saved to and loaded from a CSV file.

---

##  Project Overview

This application simulates a dealership system that would be used at the desk of a car salesman or manager. Users can interact with the inventory through a text-based menu system.


##  Application Features

The user can perform the following operations:
1 - Find vehicles within a price range
2 - Find vehicles by make and model
3 - Find vehicles by year range
4 - Find vehicles by color
5 - Find vehicles by mileage range
6 - Find vehicles by type (CAR, TRUCK, SUV, VAN)
7 - List all vehicles
8 - Add a vehicle
9 - Remove a vehicle
99 - Quit

The vehicle inventory is saved in a `inventory.csv` file, using pipe-delimited format. Changes to the inventory are written to the file automatically when vehicles are added or removed.


## File Format: `inventory.csv`

The first line contains dealership information. All other lines contain vehicle records.


---

##  Class Descriptions

### `Vehicle.java`
- Holds information about a single vehicle.
- Fields: `vin`, `year`, `make`, `model`, `vehicleType`, `color`, `odometer`, `price`
- Includes full getter/setter methods.
- Includes an overridden `toString()` method for clean display.

### `VehicleType.java`
- Enum with constants: `CAR`, `TRUCK`, `SUV`, `VAN`
- Includes a `fromString(String)` method for parsing user/file input.

### `Dealership.java`
- Holds dealership information (`name`, `address`, `phone`)
- Contains an `ArrayList<Vehicle>` inventory
- Methods:
  - `addVehicle(Vehicle)`
  - `removeVehicle(Vehicle)`
  - `getAllVehicles()`
  - Search methods by:
    - Price range
    - Make/model
    - Year range
    - Color
    - Mileage range
    - Vehicle type

### `DealershipFileManager.java`
- Responsible for reading from and writing to the `inventory.csv` file.
- Methods:
  - `getDealership()` – Loads dealership and vehicles from file
  - `saveDealership(Dealership)` – Writes dealership and vehicles to file

### `UserInterface.java`
- Handles all user interaction and command logic.
- Methods:
  - `display()` – Main loop for menu and input
  - `init()` – Loads dealership from file
  - `displayMenu()` – Prints the menu
  - `displayVehicles(List<Vehicle>)` – Helper to show vehicles
  - `processAllVehiclesRequest()`
  - `processGetByPrice()`
  - `processGetByMakeModel()`
  - `processGetByYear()`
  - `processGetByColor()`
  - `processGetByMileage()`
  - `processGetByType()`
  - `processAddVehicle()` 
  - `processRemoveVehicle()` 

### `Program.java`
- Entry point of the application
- Contains `main()` which launches the `UserInterface.display()` method

### Screenshot of Screens

[Homescreen](Screenshots/Homescreen_screenshot.png)
[Product Display Screen ](Screenshots/productDisplayScreen_screenshot.png)
[Error message example](Screenshots/error_screenshot.png)

### Interesting Peice of Code

The `processGetByPrice()` method in the `UserInterface` class dynamically interacts with the user to collect a price range and then displays the vehicles that fall within that range.
This method is a good example of how the `UserInterface` class delegates the logic of filtering vehicles to the `Dealership` class.
```java
private void processGetByPrice() {
    System.out.print("Enter minimum price: ");
    double min = Double.parseDouble(scanner.nextLine());
    System.out.print("Enter maximum price: ");
    double max = Double.parseDouble(scanner.nextLine());

    List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
    displayVehicles(vehicles);
}


#### Chelsea Bhuiyan May 2025