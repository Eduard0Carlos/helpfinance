package com.helpfinance.application.interfaces;

import java.util.List;
import java.util.UUID;

import com.helpfinance.application.models.user.UserAddModel;
import com.helpfinance.domain.entities.User;

public interface IUserApplicationService {
    List<User> getAll();
    User get(UUID id);
    User get(String email, String password);
    User Insert(UserAddModel user);
}
