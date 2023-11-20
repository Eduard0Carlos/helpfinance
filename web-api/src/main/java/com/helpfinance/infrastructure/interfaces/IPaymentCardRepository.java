package com.helpfinance.infrastructure.interfaces;

import java.util.List;
import java.util.UUID;

import com.helpfinance.domain.entities.PaymentCard;
import com.helpfinance.infrastructure.interfaces.base.IRepository;

public interface IPaymentCardRepository extends IRepository<PaymentCard> {
  List<PaymentCard> getAll(UUID userId);
}
