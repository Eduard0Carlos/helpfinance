package com.helpfinance.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.domain.entities.Address;
import com.helpfinance.domain.interfaces.services.IAddressDomainService;
import com.helpfinance.domain.services.base.DomainService;
import com.helpfinance.infrastructure.interfaces.IAddressRepository;

@Service
public class AddressDomainService extends DomainService<Address> implements IAddressDomainService {

    @Autowired
    private IAddressRepository _addressRepository;

    @Override
    public Boolean insert(Address entity) {
        return _addressRepository.insert(entity);
    }

    @Override
    public Boolean update(Address entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(Address entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Boolean delete(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Address> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Address get(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
    
}
