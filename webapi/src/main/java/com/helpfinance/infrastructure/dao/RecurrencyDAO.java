package com.helpfinance.infrastructure.dao;

import com.helpfinance.infrastructure.dao.base.baseEntityDAO;
import com.helpfinance.infrastructure.interfaces.IRecurrencyDAO;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Recurrency;

@Repository
public class RecurrencyDAO extends baseEntityDAO<Recurrency> implements IRecurrencyDAO {
    public RecurrencyDAO() {
        super(Recurrency.class, "Recurrents");
    }
}
