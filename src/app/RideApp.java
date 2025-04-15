package app;

import models.Driver;
import models.Passenger;
import models.Ride;
import models.RideStatus;
import service.RateService;
import service.RideService;
import storage.UsersDataStorage;
import utils.LoggerUtil;

import java.util.Scanner;
import java.util.logging.Logger;

public class RideApp {

    private RideService rideService = new RideService();
    private RateService rateService = new RateService();
    private UsersDataStorage userStorage = new UsersDataStorage();

    private static final Logger logger = LoggerUtil.getLogger(RideApp.class);
    private Scanner input = new Scanner(System.in);

    public void run() {
        while (true) {
            printMenu();
            int choice = input.nextInt();
            input.nextLine();

            switch (choice){
                case 1 -> createRide();
                case 2 -> startRide();
                case 3 -> endRide();
                case 4 -> cancelRide();
                case 5 -> rateDriver();
                case 6 -> viewAllRides();
                case 7 -> viewDriverRating();
                case 8 -> userManagement();
                case 0 -> {
                    System.out.println("Goodbye.");
                    return;
                }
                default -> logger.warning("Invalid choice.");
            }
        }
    }

    private void userManagement() {
        while (true) {
            printUserOperations();
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> addUser();
                case 2 -> getUserById();
                case 3 -> viewAllUsers();
                case 4 -> viewTotalUserCounts();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void viewTotalUserCounts() {
        int passengerCount = userStorage.getPassengerCount();
        int driverCount = userStorage.getDriverCount();

        System.out.println("Total Passengers: " + passengerCount);
        System.out.println("Total Drivers: " + driverCount);
    }

    private void viewAllUsers () {
        String userType = checkUserType();
        if (userType.equals("driver")){
            viewAllDrivers();
        } else if (userType.equals("passenger")){
            viewAllPassengers();
        } else {
            logger.warning("Invalid user type.");
        }
    }

    private void viewAllPassengers () {
        for (Passenger passenger : userStorage.getAllPassengers()) {
            System.out.println("User id : " + passenger.getId());
            System.out.println("User name : " + passenger.getName());
            System.out.println("User email : " + passenger.getEmail());
            System.out.println("User phone number : " + passenger.getPhoneNumber());
            System.out.println("---------------------------------");
        }
    }

    private void viewAllDrivers () {
        for (Driver driver : userStorage.getAllDrivers()) {
            System.out.println("User id : " + driver.getId());
            System.out.println("User name : " + driver.getName());
            System.out.println("User email : " + driver.getEmail());
            System.out.println("User phone number : " + driver.getPhoneNumber());
            System.out.println("Car model : " + driver.getCarModel());
            System.out.println("---------------------------------");
        }
    }

    private void getUserById () {
        String userType = checkUserType();
        System.out.println("Please enter user id");
        String id = input.nextLine();
        if (userType.equals("driver")){
            getDriverById(id);
        } else if (userType.equals("passenger")){
            getPassengerById(id);
        } else {
            logger.warning("Invalid user type.");
        }
    }

    private void getDriverById (String id) {
        Driver driver = userStorage.getDriverById(id);
        if (driver == null) {
            logger.warning("Driver not found.");
        } else {
            System.out.println("User id : " + driver.getId());
            System.out.println("User name : " + driver.getName());
            System.out.println("User email : " + driver.getEmail());
            System.out.println("User phone number : " + driver.getPhoneNumber());
            System.out.println("Car model : " + driver.getCarModel());
        }
    }

    private void getPassengerById (String id) {
        Passenger passenger = userStorage.getPassengerById(id);
        if (passenger == null) {
            logger.warning("passenger not found.");
        } else {
            System.out.println("User id : " + passenger.getId());
            System.out.println("User name : " + passenger.getName());
            System.out.println("User email : " + passenger.getEmail());
            System.out.println("User phone number : " + passenger.getPhoneNumber());
        }
    }

    private void addUser () {
        String userType = checkUserType();

        System.out.println("Please enter user id:");
        String id = input.nextLine();
        System.out.println("Please enter user name:");
        String name = input.nextLine();
        System.out.println("Please enter user email:");
        String email = input.nextLine();
        System.out.println("Please enter user password:");
        String password = input.nextLine();
        System.out.println("Please enter user phone number:");
        String phoneNum = input.nextLine();

        if (userType.equals("driver")){
            System.out.println("Please enter car model:");
            String carModel = input.nextLine();
            userStorage.addDriver(id, name, email, password, phoneNum, carModel);
        } else if (userType.equals("passenger")){
            userStorage.addPassenger(id, name, email, password, phoneNum);
        } else {
            System.out.println("Invalid user type.");
        }
    }

    private String checkUserType () {
        System.out.println("Enter user type (passenger/driver): ");
        return input.nextLine().toLowerCase();
    }

    private void printUserOperations() {
        System.out.println("1. Add User");
        System.out.println("2. Get User Data By ID");
        System.out.println("3. View All Users");
        System.out.println("4. View Total Count of Users");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
    }

//    *********************************************************

    private void viewDriverRating() {
        System.out.println("Please enter driver id:");
        String driverId = input.nextLine();
        Driver driver = userStorage.getDriverById(driverId);
        if (driver == null) {
            logger.warning("Driver not found.");
            return;
        }

        double avg = rateService.getRatingsAvg(driver);
        if (avg > 0) {
            System.out.println("Average Driver Rating: " + avg);
        }
    }

    private void viewAllRides() {
        for (Ride ride : rideService.getAllRides()) {
            System.out.println(
                    "Ride ID: " + ride.getId() + "\n" +
                            "Status: " + ride.getStatus() + "\n" +
                            "From: " + ride.getPickupLocation() + "\n" +
                            "To: " + ride.getDropOffLocation() + "\n" +
                            "-----------------------------"
            );
        }
    }

    private void rateDriver() {
        Ride ride = getRideById();

        if (ride.getStatus() != RideStatus.COMPLETED) {
            logger.warning("Cannot rate a ride that is not completed.");
            return;
        }

        System.out.println("Please enter driver rate : ");
        int rate = input.nextInt();
        input.nextLine();
        rateService.rateDriver(ride, ride.getPassengerInfo(), ride.getDriverInfo(), rate);
    }

    private void cancelRide() {
        Ride ride = getRideById();
        if (ride == null) {
            return;
        }
        rideService.cancelRide(ride);
    }

    private void endRide() {
        Ride ride = getRideById();
        if (ride == null) {
            return;
        }
        rideService.endRide(ride);
    }

    private void startRide() {
        Ride ride = getRideById();
        if (ride == null) {
            return;
        }
        rideService.startRide(ride);
    }

    private void createRide(){
        System.out.println("Please enter ride id");
        String id = input.nextLine();

        System.out.println("Please enter passenger id:");
        String passengerId = input.nextLine();
        Passenger passenger = userStorage.getPassengerById(passengerId);
        if (passenger == null) {
            logger.warning("Passenger not found.");
            return;
        }

        System.out.println("Please enter driver id:");
        String driverId = input.nextLine();
        Driver driver = userStorage.getDriverById(driverId);
        if (driver == null) {
            logger.warning("Driver not found.");
            return;
        }

        System.out.println("Please enter pickup location");
        String pickup = input.nextLine();
        System.out.println("Please enter drop-off location");
        String dropOff = input.nextLine();

        rideService.createRide(id, passenger, driver, pickup, dropOff);
    }

    private Ride getRideById() {
        System.out.println("Please enter ride id");
        String id = input.nextLine();
        Ride ride = rideService.getRideById(id);
        if (ride == null) {
            logger.warning("Ride is not found.");
        }
        return ride;
    }

    private void printMenu() {
        System.out.println("\n--- Ride App Menu ---");
        System.out.println("1. Create a Ride");
        System.out.println("2. Start Ride");
        System.out.println("3. End Ride");
        System.out.println("4. Cancel Ride");
        System.out.println("5. Rate Driver");
        System.out.println("6. View All Rides");
        System.out.println("7. View Driver Ratings");
        System.out.println("8. Users Management");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

}
