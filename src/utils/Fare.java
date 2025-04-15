package utils;

import models.Ride;

import java.time.Duration;

public class Fare {

    public static double calc(Ride ride){
        long minutes = Duration.between(ride.getStartTime(), ride.getEndTime()).toMinutes();
        minutes = Math.max(minutes, 1);
        return minutes * 2.5;
    }

}
