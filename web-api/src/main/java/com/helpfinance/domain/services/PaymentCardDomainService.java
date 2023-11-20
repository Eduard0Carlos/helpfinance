package com.helpfinance.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.domain.entities.PaymentCard;
import com.helpfinance.domain.interfaces.services.IPaymentCardDomainService;
import com.helpfinance.domain.services.base.DomainService;
import com.helpfinance.infrastructure.interfaces.IPaymentCardRepository;

@Service
public class PaymentCardDomainService extends DomainService<PaymentCard> implements IPaymentCardDomainService {

  @Autowired
  private IPaymentCardRepository _paymentCardRepository;

  public PaymentCardDomainService(@Autowired IPaymentCardRepository repository) {
    super(repository);
  }

  @Override
  public List<PaymentCard> getAll(UUID userId) {
    return _paymentCardRepository.getAll(userId);
  }
}
