package com.helpfinance.infrastructure.dao;

import com.helpfinance.infrastructure.dao.base.baseEntityDAO;
import com.helpfinance.infrastructure.interfaces.IInvestmentDAO;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Investment;

@Repository
public class InvestmentDAO extends baseEntityDAO<Investment> implements IInvestmentDAO {
    public InvestmentDAO() {
        super(Investment.class, "Investments");
    }
}