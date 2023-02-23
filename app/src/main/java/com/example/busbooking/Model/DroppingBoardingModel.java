package com.example.busbooking.Model;

public class DroppingBoardingModel
{
    String LocationName;
    String PickAddress;
    String Time;

    public DroppingBoardingModel(String locationName, String pickAddress, String time) {
        LocationName = locationName;
        PickAddress = pickAddress;
        Time = time;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getPickAddress() {
        return PickAddress;
    }

    public void setPickAddress(String pickAddress) {
        PickAddress = pickAddress;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
