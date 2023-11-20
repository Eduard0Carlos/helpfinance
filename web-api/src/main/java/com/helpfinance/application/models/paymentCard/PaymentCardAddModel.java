package com.helpfinance.application.models.paymentCard;

import java.sql.Timestamp;
import java.util.UUID;

import com.helpfinance.domain.enums.PaymentNetwork;
import com.helpfinance.domain.enums.PaymentType;

public class PaymentCardAddModel {
  public UUID userId;
  public String cardNumber;
  public String nickname;
  public Timestamp expirationDate;
  public String cvv;
  public PaymentNetwork paymentNetwork;
  public PaymentType paymentType;
}
