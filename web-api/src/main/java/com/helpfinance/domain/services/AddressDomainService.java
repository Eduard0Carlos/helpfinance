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

    public AddressDomainService(@Autowired IAddressRepository repository) {
        super(repository);
    }    
}
