package com.helpfinance.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.application.interfaces.IAddressApplicationService;
import com.helpfinance.application.models.address.AddressAddModel;
import com.helpfinance.application.services.base.BaseService;
import com.helpfinance.domain.entities.Address;
import com.helpfinance.domain.interfaces.services.IAddressDomainService;

@Service
public class AddressApplicationService extends BaseService implements IAddressApplicationService {

    @Autowired
    private IAddressDomainService _jobDomainService;

    @Override
    public Address insert(AddressAddModel addressModel) {
        var newAddress = new Address(addressModel.userId.get(), addressModel.cep, addressModel.houseNumber, addressModel.street, addressModel.city, addressModel.state, addressModel.country);
    
        _jobDomainService.insert(newAddress);

        return newAddress;
    }
    
}
