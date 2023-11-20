package com.helpfinance.application.interfaces;

import java.util.List;
import java.util.UUID;

import com.helpfinance.application.models.paymentCard.PaymentCardAddModel;
import com.helpfinance.domain.entities.PaymentCard;

public interface IPaymentCardApplicationService {
  PaymentCard insert(PaymentCardAddModel paymentCardAddModel);
  List<PaymentCard> getAll(UUID userId);
}
