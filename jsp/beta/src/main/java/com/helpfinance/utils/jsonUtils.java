package com.helpfinance.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.helpfinance.models.ApiResult;

public class jsonUtils {
  public static <T extends Object> ApiResult<T> toApiResult(String json, Class<T> returnType) { 
    var type = TypeToken.getParameterized(ApiResult.class, returnType);
    return (ApiResult<T>) new Gson().fromJson(json, type);
  }

  public static String toJson(Object object) {
    return new Gson().toJson(object);
  }
}
