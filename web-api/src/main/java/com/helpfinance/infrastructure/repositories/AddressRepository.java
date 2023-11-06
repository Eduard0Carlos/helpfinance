package com.helpfinance.infrastructure.repositories;

import com.helpfinance.infrastructure.repositories.base.EntityRepository;
import com.helpfinance.infrastructure.interfaces.IAddressRepository;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Address;

@Repository
public class AddressRepository extends EntityRepository<Address> implements IAddressRepository {
   
    public AddressRepository() { super("Address"); }
}
