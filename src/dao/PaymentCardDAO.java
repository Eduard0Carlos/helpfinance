package dao;

import dao.base.baseEntityDAO;
import entities.PaymentCard;
import interfaces.dao.IPaymentCardDAO;

public class PaymentCardDAO extends baseEntityDAO<PaymentCard> implements IPaymentCardDAO {
    public PaymentCardDAO() {
        super(PaymentCard.class, "Payment_Cards");
    }
}