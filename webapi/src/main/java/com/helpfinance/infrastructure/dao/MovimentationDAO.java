package com.helpfinance.infrastructure.dao;

import com.helpfinance.infrastructure.dao.base.baseEntityDAO;
import com.helpfinance.infrastructure.interfaces.IMovimentationDAO;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Movimentation;

@Repository
public class MovimentationDAO extends baseEntityDAO<Movimentation> implements IMovimentationDAO {
    public MovimentationDAO() {
        super(Movimentation.class, "Movimentations");
    }
}