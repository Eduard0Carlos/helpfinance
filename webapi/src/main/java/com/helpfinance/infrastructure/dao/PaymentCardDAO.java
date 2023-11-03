package com.helpfinance.infrastructure.dao;

import com.helpfinance.infrastructure.dao.base.baseEntityDAO;
import com.helpfinance.infrastructure.interfaces.IPaymentCardDAO;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.PaymentCard;

@Repository
public class PaymentCardDAO extends baseEntityDAO<PaymentCard> implements IPaymentCardDAO {
    public PaymentCardDAO() {
        super(PaymentCard.class, "Payment_Cards");
    }
}