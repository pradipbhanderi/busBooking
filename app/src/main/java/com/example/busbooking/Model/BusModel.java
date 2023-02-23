package com.example.busbooking.Model;

public class BusModel {
    String Reststop;
    String Picktime;
    String Droptime;
    String Bookingremaingtime;
    String Availablesates;
    String Travelsname;
    String Rateing;
    String Totalpassenger;
    String Ticketprice;

    public BusModel(String reststop, String picktime, String droptime, String bookingremaingtime, String availablesates, String travelsname, String rateing, String totalpassenger, String ticketprice) {
        Reststop = reststop;
        Picktime = picktime;
        Droptime = droptime;
        Bookingremaingtime = bookingremaingtime;
        Availablesates = availablesates;
        Travelsname = travelsname;
        Rateing = rateing;
        Totalpassenger = totalpassenger;
        Ticketprice = ticketprice;
    }

    public String getReststop() {
        return Reststop;
    }

    public void setReststop(String reststop) {
        Reststop = reststop;
    }

    public String getPicktime() {
        return Picktime;
    }

    public void setPicktime(String picktime) {
        Picktime = picktime;
    }

    public String getDroptime() {
        return Droptime;
    }

    public void setDroptime(String droptime) {
        Droptime = droptime;
    }

    public String getBookingremaingtime() {
        return Bookingremaingtime;
    }

    public void setBookingremaingtime(String bookingremaingtime) {
        Bookingremaingtime = bookingremaingtime;
    }

    public String getAvailablesates() {
        return Availablesates;
    }

    public void setAvailablesates(String availablesates) {
        Availablesates = availablesates;
    }

    public String getTravelsname() {
        return Travelsname;
    }

    public void setTravelsname(String travelsname) {
        Travelsname = travelsname;
    }

    public String getRateing() {
        return Rateing;
    }

    public void setRateing(String rateing) {
        Rateing = rateing;
    }

    public String getTotalpassenger() {
        return Totalpassenger;
    }

    public void setTotalpassenger(String totalpassenger) {
        Totalpassenger = totalpassenger;
    }

    public String getTicketprice() {
        return Ticketprice;
    }

    public void setTicketprice(String ticketprice) {
        Ticketprice = ticketprice;
    }
}
