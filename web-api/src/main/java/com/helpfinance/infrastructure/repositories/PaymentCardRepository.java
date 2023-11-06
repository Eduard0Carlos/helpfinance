package com.helpfinance.infrastructure.repositories;

import com.helpfinance.infrastructure.interfaces.IPaymentCardRepository;
import com.helpfinance.infrastructure.repositories.base.EntityRepository;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.PaymentCard;

@Repository
public class PaymentCardRepository extends EntityRepository<PaymentCard> implements IPaymentCardRepository {
   
    public PaymentCardRepository() { super("Payment_Cards"); }
}