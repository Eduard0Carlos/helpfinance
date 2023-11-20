package com.helpfinance.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.domain.entities.User;
import com.helpfinance.domain.interfaces.services.IUserDomainService;
import com.helpfinance.domain.services.base.DomainService;
import com.helpfinance.infrastructure.interfaces.IUserRepository;

@Service
public class UserDomainService extends DomainService<User> implements IUserDomainService {

    @Autowired
    private IUserRepository _userRepository;

    public UserDomainService(@Autowired IUserRepository repository) {
        super(repository);
    }

    @Override
    public User get(UUID id) {
        var user = _userRepository.get(id);

        if (user == null) {
            notificationService.addError("Usuário não encontrado!");
            return null;
        }

        return user;
    }

    @Override
    public User get(String email, String password) {
        var user = _userRepository.get(email, password);

        if (user == null) {
            notificationService.addError("Email e/ou senha incorretos!");
            return null;
        }

        return user;
    }

}
