package dao;

import dao.base.baseEntityDAO;
import entities.Bank;
import interfaces.dao.IBankDAO;

public class BankDAO extends baseEntityDAO<Bank> implements IBankDAO {
    public BankDAO() {
        super(Bank.class, "Banks");
    }
}
