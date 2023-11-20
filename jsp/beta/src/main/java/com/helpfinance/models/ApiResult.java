package com.helpfinance.models;

import java.util.List;

public class ApiResult<T> {
  public boolean success;
  public T data;
  public List<String> errors;
}
