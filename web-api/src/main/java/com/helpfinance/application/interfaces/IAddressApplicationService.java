package com.helpfinance.application.interfaces;

import com.helpfinance.application.models.address.AddressAddModel;
import com.helpfinance.domain.entities.Address;

public interface IAddressApplicationService {
    Address Insert(AddressAddModel addressModel);
}
