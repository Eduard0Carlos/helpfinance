package com.helpfinance.infrastructure.repositories;

import com.helpfinance.infrastructure.interfaces.IRecurrencyRepository;
import com.helpfinance.infrastructure.repositories.base.EntityRepository;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Recurrency;

@Repository
public class RecurrencyRepository extends EntityRepository<Recurrency> implements IRecurrencyRepository {
   
    public RecurrencyRepository() { super("Recurrents"); }
}
