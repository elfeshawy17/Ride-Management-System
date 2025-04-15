package models;

public class Driver extends User{

    private String carModel;

    public Driver(String id, String name, String email, String password, String phoneNumber, String carModel) {
        super(id, name, email, password, phoneNumber);
        this.carModel = carModel;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
