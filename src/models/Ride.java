package models;

import java.time.LocalDateTime;

public class Ride {

    private String id;
    private Passenger passengerInfo;
    private Driver driverInfo;
    private String pickupLocation;
    private String dropOffLocation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private RideStatus status;
    private double rideFare;

    public Ride(String id, Passenger passengerInfo, Driver driverInfo, String pickupLocation, String dropOffLocation){
        this.id = id;
        this.passengerInfo = passengerInfo;
        this.driverInfo = driverInfo;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.status = RideStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Passenger getPassengerInfo() {
        return passengerInfo;
    }

    public void setPassengerInfo(Passenger passengerInfo) {
        this.passengerInfo = passengerInfo;
    }

    public Driver getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(Driver driverInfo) {
        this.driverInfo = driverInfo;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public double getRideFare() {
        return rideFare;
    }

    public void setRideFare(double rideFare) {
        this.rideFare = rideFare;
    }
}
