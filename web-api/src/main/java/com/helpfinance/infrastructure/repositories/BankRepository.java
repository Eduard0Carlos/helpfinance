package com.helpfinance.infrastructure.repositories;

import com.helpfinance.infrastructure.interfaces.IBankRepository;
import com.helpfinance.infrastructure.repositories.base.EntityRepository;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Bank;

@Repository
public class BankRepository extends EntityRepository<Bank> implements IBankRepository {
    
    public BankRepository() { super("Banks"); }
}
