package models;

import service.RideService;
import utils.LoggerUtil;

import java.util.logging.Logger;

public class Rate {

    private static final Logger logger = LoggerUtil.getLogger(Rate.class);

    private Ride ride;
    private Passenger passenger;
    private Driver driver;
    private int rate;

    public Rate(Ride ride, Passenger passenger, Driver driver, int rate) {
        this.ride = ride;
        this.passenger = passenger;
        this.driver = driver;
        this.rate = rate;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        if (rate >= 1 && rate <= 5 ){
            this.rate = rate;
        }
        logger.info("Rate must be between 1 and 5");
    }

}
