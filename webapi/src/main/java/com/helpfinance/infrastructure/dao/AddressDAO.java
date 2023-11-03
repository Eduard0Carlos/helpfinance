package com.helpfinance.infrastructure.dao;

import com.helpfinance.infrastructure.dao.base.baseEntityDAO;
import com.helpfinance.infrastructure.interfaces.IAddressDAO;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Address;

@Repository
public class AddressDAO extends baseEntityDAO<Address> implements IAddressDAO {
    public AddressDAO() {
        super(Address.class, "Address");
    }
}
