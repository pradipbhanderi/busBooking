package com.example.busbooking.Model;

public class BoardingModel {

    String location;
    String address;
    String time;
    Boolean Is_selected=false;

    public BoardingModel(String location, String address, String time) {
        this.location = location;
        this.address = address;
        this.time = time;
    }

    public Boolean getIs_selected() {
        return Is_selected;
    }

    public void setIs_selected(Boolean is_selected) {
        Is_selected = is_selected;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
