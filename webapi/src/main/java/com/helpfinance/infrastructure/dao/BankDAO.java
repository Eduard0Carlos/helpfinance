package com.helpfinance.infrastructure.dao;

import com.helpfinance.infrastructure.dao.base.baseEntityDAO;
import com.helpfinance.infrastructure.interfaces.IBankDAO;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Bank;

@Repository
public class BankDAO extends baseEntityDAO<Bank> implements IBankDAO {
    public BankDAO() {
        super(Bank.class, "Banks");
    }
}
