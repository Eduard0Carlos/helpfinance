package dao;

import dao.base.baseEntityDAO;
import entities.Recurrency;
import interfaces.dao.IRecurrencyDAO;

public class RecurrencyDAO extends baseEntityDAO<Recurrency> implements IRecurrencyDAO {
    public RecurrencyDAO() {
        super(Recurrency.class, "Recurrents");
    }
}
