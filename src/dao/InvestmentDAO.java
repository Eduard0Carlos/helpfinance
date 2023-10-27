package dao;

import dao.base.baseEntityDAO;
import entities.Investment;
import interfaces.dao.IInvestmentDAO;

public class InvestmentDAO extends baseEntityDAO<Investment> implements IInvestmentDAO {
    public InvestmentDAO() {
        super(Investment.class, "Investments");
    }
}