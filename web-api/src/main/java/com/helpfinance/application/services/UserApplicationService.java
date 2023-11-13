package com.helpfinance.application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.application.interfaces.IAddressApplicationService;
import com.helpfinance.application.interfaces.IJobApplicationService;
import com.helpfinance.application.interfaces.IUserApplicationService;
import com.helpfinance.application.models.user.UserAddModel;
import com.helpfinance.application.services.base.BaseService;
import com.helpfinance.domain.entities.User;
import com.helpfinance.domain.interfaces.services.IUserDomainService;

@Service
public class UserApplicationService extends BaseService implements IUserApplicationService {

    @Autowired
    private IUserDomainService _userDomainService;

    @Autowired
    private IAddressApplicationService _addressApplicationService;
    @Autowired
    private IJobApplicationService _jobApplicationService;

    @Override
    public List<User> getAll() {
        return _userDomainService.getAll();
    }

    @Override
    public User get(UUID id) {
        return _userDomainService.get(id);
    }

    public User get(String email, String password) {
        return _userDomainService.get(email, password);
    }

    @Override
    public User insert(UserAddModel userModel) {
        var newUser = new User(userModel.name, userModel.birthdate, userModel.email, userModel.password);

        _userDomainService.insert(newUser);

        userModel.address.userId = Optional.of(newUser.getId());
        _addressApplicationService.Insert(userModel.address);

        if (userModel.job != null) {
            userModel.job.userId = Optional.of(newUser.getId());

            _jobApplicationService.Insert(userModel.job);
        }

        return newUser;
    }
}
