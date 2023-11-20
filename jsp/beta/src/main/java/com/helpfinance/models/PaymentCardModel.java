package com.helpfinance.models;

import java.sql.Timestamp;
import java.util.UUID;

public class PaymentCardModel {
  public UUID userId;
  public String cardNumber;
  public String nickname;
  public String expirationDate;
  public String cvv;
  public String paymentNetwork;
  public String paymentType;
  
  public UUID getUserId() {
    return userId;
  }
  public void setUserId(UUID userId) {
    this.userId = userId;
  }
  public String getCardNumber() {
    return cardNumber;
  }
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getExpirationDate() {
    return expirationDate;
  }
  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }
  public String getCvv() {
    return cvv;
  }
  public void setCvv(String cvv) {
    this.cvv = cvv;
  }
  public String getPaymentNetwork() {
    return paymentNetwork;
  }
  public void setPaymentNetwork(String paymentNetwork) {
    this.paymentNetwork = paymentNetwork;
  }
  public String getPaymentType() {
    return paymentType;
  }
  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }
}
