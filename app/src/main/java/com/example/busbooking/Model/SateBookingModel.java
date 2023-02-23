package com.example.busbooking.Model;

public class SateBookingModel {
    int image;
    Boolean is_selected = false;

    public SateBookingModel(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Boolean getIs_selected() {
        return is_selected;
    }

    public void setIs_selected(Boolean is_selected) {
        this.is_selected = is_selected;
    }
}
