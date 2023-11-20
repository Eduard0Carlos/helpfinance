package com.helpfinance.domain.interfaces.services;

import java.util.List;
import java.util.UUID;

import com.helpfinance.domain.entities.PaymentCard;
import com.helpfinance.domain.interfaces.services.base.IDomainService;

public interface IPaymentCardDomainService extends IDomainService<PaymentCard> {
  List<PaymentCard> getAll(UUID userId);
}
