package com.helpfinance.application.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.application.interfaces.IUserApplicationService;
import com.helpfinance.application.services.base.BaseService;
import com.helpfinance.domain.entities.User;
import com.helpfinance.domain.services.UserDomainService;

@Service
public class UserApplicationService extends BaseService implements IUserApplicationService {

    @Autowired
    private UserDomainService _userDomainService;

    @Override
    public List<User> getAll() {
        return _userDomainService.getAll();
    }

    @Override
    public User get(UUID id) {
        return _userDomainService.get(id);
    }

}
