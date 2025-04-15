package service;

import models.Driver;
import models.Passenger;
import models.Ride;
import models.RideStatus;
import utils.Fare;
import utils.LoggerUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

public class RideService {

    private ArrayList <Ride> rides = new ArrayList<>();
    private static final Logger logger = LoggerUtil.getLogger(RideService.class);

    public Ride createRide(String id, Passenger passenger, Driver driver, String pickup, String dropOff) {
        Ride ride = new Ride(id, passenger, driver, pickup, dropOff);
        rides.add(ride);
        logger.info("Ride is created successfully.");
        return ride;
    }

    public void startRide(Ride ride){
        if (ride.getStatus() == RideStatus.PENDING){
            ride.setStatus(RideStatus.ONGOING);
            ride.setStartTime(LocalDateTime.now());
            logger.info("Ride is started.");
        }
    }

    public void endRide(Ride ride){
        if (ride.getStatus() == RideStatus.ONGOING){
            ride.setStatus(RideStatus.COMPLETED);
            ride.setEndTime(LocalDateTime.now());
            ride.setRideFare( Fare.calc(ride) );
            double rideFare = ride.getRideFare();
            logger.info("Ride is ended and ride fare is " + rideFare);
        }
    }

    public ArrayList<Ride> getAllRides() {
        return rides;
    }

    public Ride getRideById(String id){
        for(Ride ride : rides){
            if (ride.getId().equals(id)){
                return ride;
            }
        }
        return null;
    }

    public void cancelRide(Ride ride){
        if (ride.getStatus() == RideStatus.PENDING){
            ride.setStatus(RideStatus.CANCELLED);
            logger.info("Ride " + ride.getId() + " has been cancelled");
        }
        else {
            logger.info("Ride " + ride.getId() + " cannot be cancelled");
        }
    }

}
