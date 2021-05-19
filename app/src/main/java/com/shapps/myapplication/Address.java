package com.shapps.myapplication;

public class Address {
    private String name;
    private String phone;
    private String pin;
    private String houseNo;
    private String area;
    private String city;
    private String state;

    public Address(String name, String phone, String pin, String houseNo, String area, String city, String state) {
        this.name = name;
        this.phone = phone;
        this.pin = pin;
        this.houseNo = houseNo;
        this.area = area;
        this.city = city;
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
