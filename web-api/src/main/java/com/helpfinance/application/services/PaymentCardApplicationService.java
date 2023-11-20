package com.helpfinance.application.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.application.interfaces.IPaymentCardApplicationService;
import com.helpfinance.application.models.paymentCard.PaymentCardAddModel;
import com.helpfinance.application.services.base.BaseService;
import com.helpfinance.domain.entities.PaymentCard;
import com.helpfinance.domain.interfaces.services.IPaymentCardDomainService;

@Service
public class PaymentCardApplicationService extends BaseService implements IPaymentCardApplicationService {
  @Autowired
  private IPaymentCardDomainService _paymentCardService;

  @Override
  public PaymentCard insert(PaymentCardAddModel paymentCardModel) {
    
    var newPaymentCard = new PaymentCard(paymentCardModel.userId, 
                                        paymentCardModel.cardNumber, 
                                        paymentCardModel.nickname, 
                                        paymentCardModel.expirationDate, 
                                        paymentCardModel.cvv, 
                                        paymentCardModel.paymentNetwork, 
                                        paymentCardModel.paymentType);

    _paymentCardService.insert(newPaymentCard);

    if (!super.notificationService.hasErrors())
      super.unitOfWork.commit();

    return newPaymentCard;
  }

  @Override
  public List<PaymentCard> getAll(UUID userId) {
    return _paymentCardService.getAll(userId);
  }
}

