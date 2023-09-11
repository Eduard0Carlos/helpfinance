package entities;

import entities.base.EntityBase;
import interfaces.IEntity;
import java.util.UUID;

public class Address extends EntityBase implements IEntity<Address> {
    private UUID userId;
    private String cep;
    private String number;
    private String city;
    private String state;
    private String country;

    public Address(UUID userId, String cep, String number, String city, String state, String country)
    {
        super();

        this.setUserId(userId);
        this.setCep(cep);
        this.setNumber(number);
        this.setCity(city);
        this.setState(state);
        this.setCountry(country);
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean add(Address address) {
        // TODO: Insert in database
        return false;
    }

    @Override
    public boolean delete(Address address) {
        // TODO: Delete on the database
        return false;
    }

    @Override
    public Address getById(UUID id) {
        // TODO: Search for entity in database
        return null;
    }
}
