package com.helpfinance.models;

import java.util.UUID;

public class MovimentationModel {
  public UUID userId;
  public String title;
  public String amount;
  public String category;
  public String movType;
  public String date;
  
  public UUID getUserId() {
    return userId;
  }
  public void setUserId(UUID userId) {
    this.userId = userId;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getAmount() {
    return amount;
  }
  public void setAmount(String amount) {
    this.amount = amount;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public String getMovType() {
    return movType;
  }
  public void setMovType(String movType) {
    this.movType = movType;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
}
