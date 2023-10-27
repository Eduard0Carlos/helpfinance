package dao;

import dao.base.baseEntityDAO;
import entities.Address;
import interfaces.dao.IAddressDAO;

public class AddressDAO extends baseEntityDAO<Address> implements IAddressDAO {
    public AddressDAO() {
        super(Address.class, "Address");
    }
}
