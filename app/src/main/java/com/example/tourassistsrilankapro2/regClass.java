package com.example.tourassistsrilankapro2;

public class regClass {

    private String name, Email, Phone, EAddress, Password;

    public regClass() {
    }

    public regClass(String name, String email, String phone, String EAddress, String password) {
        this.name = name;
        Email = email;
        Phone = phone;
        this.EAddress = EAddress;
        Password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEAddress() {
        return EAddress;
    }

    public void setEAddress(String EAddress) {
        this.EAddress = EAddress;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}