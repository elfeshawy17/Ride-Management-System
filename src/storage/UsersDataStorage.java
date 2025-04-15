package storage;

import models.Driver;
import models.Passenger;
import service.RateService;
import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.logging.Logger;

public class UsersDataStorage {

    ArrayList<Passenger> passengers = new ArrayList<>();
    ArrayList<Driver> drivers = new ArrayList<>();
    private static final Logger logger = LoggerUtil.getLogger(UsersDataStorage.class);

    public void addDriver(String id, String name, String email, String password, String phoneNum, String carModel) {
        if (getDriverById(id) == null){
            Driver driver = new Driver(id, name, email, password, phoneNum, carModel);
            drivers.add(driver);
        } else {
            logger.warning("Driver already exists");
        }
    }

    public void addPassenger(String id, String name, String email, String password, String phoneNum) {
        if (getPassengerById(id) == null){
            Passenger passenger = new Passenger(id, name, email, password, phoneNum);
            passengers.add(passenger);
        }else {
            logger.warning("Passenger already exists");
        }
    }

    public Passenger getPassengerById(String id){
        for (Passenger passenger : passengers) {
            if (passenger.getId().equals(id)){
                return passenger;
            }
        }
        return null;
    }

    public Driver getDriverById(String id) {
        for (Driver driver : drivers) {
            if (driver.getId().equals(id)){
                return driver;
            }
        }
        return null;
    }

    public ArrayList<Passenger> getAllPassengers () {
        return passengers;
    }

    public ArrayList<Driver> getAllDrivers () {
        return drivers;
    }

    public int getPassengerCount() {
        return passengers.size();
    }

    public int getDriverCount() {
        return drivers.size();
    }

}
