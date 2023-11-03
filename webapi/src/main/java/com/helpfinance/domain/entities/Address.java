package com.helpfinance.domain.entities;

import com.helpfinance.domain.entities.base.EntityBase;
import com.helpfinance.domain.interfaces.entities.IEntity;

import java.util.UUID;

public class Address extends EntityBase implements IEntity<Address> {
    private UUID userId;
    private String cep;
    private String houseNumber;
    private String street;
    private String city;
    private String state;
    private String country;

    private Address() { }

    public Address(UUID userId, String cep, String houseNumber, String street, String city, String state, String country)
    {
        super();

        this.setUserId(userId);
        this.setCep(cep);
        this.setHouseNumber(houseNumber);
        this.setCity(city);
        this.setState(state);
        this.setCountry(country);
        this.setStreet(street);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
