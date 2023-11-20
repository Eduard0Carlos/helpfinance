package com.helpfinance.models;

public class AddressModel {
  public String street;
  public String cep;
  public String country;
  public String state;
  public String houseNumber;
  public String district;  
  public String city;
  
  public String getStreet() {
    return street;
  }
  public void setStreet(String street) {
    this.street = street;
  }
  public String getCep() {
    return cep;
  }
  public void setCep(String cep) {
    this.cep = cep;
  }
  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getHouseNumber() {
    return houseNumber;
  }
  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
  }
  public String getDistrict() {
    return district;
  }
  public void setDistrict(String district) {
    this.district = district;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
}
