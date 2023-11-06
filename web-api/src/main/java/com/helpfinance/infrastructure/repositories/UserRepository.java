package com.helpfinance.infrastructure.repositories;

import com.helpfinance.infrastructure.interfaces.IUserRepository;
import com.helpfinance.infrastructure.repositories.base.EntityRepository;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.User;

@Repository
public class UserRepository extends EntityRepository<User> implements IUserRepository {

    public UserRepository() { super("Users"); }
}
