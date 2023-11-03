package com.helpfinance.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.domain.entities.User;
import com.helpfinance.domain.interfaces.services.IUserDomainService;
import com.helpfinance.domain.services.base.DomainService;
import com.helpfinance.infrastructure.dao.UserDAO;

@Service
public class UserDomainService extends DomainService<User> implements IUserDomainService {

    @Autowired
    private UserDAO _userDAO;

    @Override
    public Boolean insert(User entity) {
        _userDAO.insert(entity);

        return !super.notificationService.hasErrors() && super.unitOfWork.commit();
    }

    @Override
    public Boolean update(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Boolean delete(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<User> getAll() {
        return _userDAO.getAll();
    }

    @Override
    public User get(UUID id) {
        var user = _userDAO.get(id);

        if (user == null) {
            notificationService.addError("Usuário não encontrado!");
            return null;
        }

        return user;
    }

}
