package com.example.busbooking.Model;

public class VehicleConfigureModel {

    String vehicleNumber;
    String vehicleType;
    String vehicleColor;
    String vehicleSeats;
    String  vehicleModel;
    String carType;

    public VehicleConfigureModel(String vehicleNumber, String vehicleType, String vehicleColor, String vehicleSeats, String vehicleModel, String carType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.vehicleColor = vehicleColor;
        this.vehicleSeats = vehicleSeats;
        this.vehicleModel = vehicleModel;
        this.carType = carType;
    }



    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleSeats() {
        return vehicleSeats;
    }

    public void setVehicleSeats(String vehicleSeats) {
        this.vehicleSeats = vehicleSeats;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
