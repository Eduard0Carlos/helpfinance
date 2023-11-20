package com.helpfinance.models;

import java.util.UUID;

public class BankModel {
  public UUID userId;
  public String name;
  public int bankAccountNumber;
  public int bankAgencyNumber;
  public String integrationToken;
  
  public UUID getUserId() {
    return userId;
  }
  public void setUserId(UUID userId) {
    this.userId = userId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getBankAccountNumber() {
    return bankAccountNumber;
  }
  public void setBankAccountNumber(int bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }
  public int getBankAgencyNumber() {
    return bankAgencyNumber;
  }
  public void setBankAgencyNumber(int bankAgencyNumber) {
    this.bankAgencyNumber = bankAgencyNumber;
  }
  public String getIntegrationToken() {
    return integrationToken;
  }
  public void setIntegrationToken(String integrationToken) {
    this.integrationToken = integrationToken;
  }
}
