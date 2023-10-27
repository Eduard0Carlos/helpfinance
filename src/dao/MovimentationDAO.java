package dao;

import dao.base.baseEntityDAO;
import entities.Movimentation;
import interfaces.dao.IMovimentationDAO;

public class MovimentationDAO extends baseEntityDAO<Movimentation> implements IMovimentationDAO {
    public MovimentationDAO() {
        super(Movimentation.class, "Movimentations");
    }
}