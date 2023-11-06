package com.helpfinance.infrastructure.repositories;

import com.helpfinance.infrastructure.interfaces.IInvestmentRepository;
import com.helpfinance.infrastructure.repositories.base.EntityRepository;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Investment;

@Repository
public class InvestmentRepository extends EntityRepository<Investment> implements IInvestmentRepository {
    
    public InvestmentRepository() { super("Investments"); }
}