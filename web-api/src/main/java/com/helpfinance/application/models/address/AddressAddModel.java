package com.helpfinance.application.models.address;

import java.util.Optional;
import java.util.UUID;

public class AddressAddModel {
    public Optional<UUID> userId;
    public String cep;
    public String street;
    public String district;
    public String houseNumber;
    public String country;
    public String state;
    public String city;
}
