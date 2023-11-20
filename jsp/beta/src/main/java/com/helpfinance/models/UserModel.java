package com.helpfinance.models;

import java.util.UUID;

public class UserModel {
    public UUID id;
    public String name;
    public String email;
    public String birthdate;
    public String password;    
    public int balance;
    public AddressModel address;
    public int monthlySpendingsLimit;
    public JobModel job;
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public AddressModel getAddress() {
        return address;
    }
    public void setAddress(AddressModel address) {
        this.address = address;
    }
    public JobModel getJob() {
        return job;
    }
    public void setJob(JobModel job) {
        this.job = job;
    }
}
