package com.example.busbooking.Model;

import java.util.List;

public class ProfileModel
{
    String Name;
    String DateOfBirth;
    String Gender;
    String MobileNumber;

    public ProfileModel(String name, String dateOfBirth, String gender, String mobileNumber) {
        Name = name;
        DateOfBirth = dateOfBirth;
        Gender = gender;
        MobileNumber = mobileNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }
}
