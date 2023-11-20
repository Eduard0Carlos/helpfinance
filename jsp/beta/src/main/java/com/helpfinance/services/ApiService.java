package com.helpfinance.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Map;

import com.helpfinance.models.ApiResult;
import com.helpfinance.utils.jsonUtils;

public class ApiService {
  public final static String API_URL = "http://localhost:8080/api/";

  public static <TResult extends Object> ApiResult<TResult> doPost(String url, String json, Class<TResult> returnType) {
    var client = HttpClient.newHttpClient();

    try {
      var httpRequest = HttpRequest
          .newBuilder(new URI(API_URL + url))
          .POST(HttpRequest.BodyPublishers.ofString(json))
          .header("Content-Type", "application/json; charset=utf8")          
          .build();

      var httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

      return jsonUtils.<TResult>toApiResult(httpResponse.body(), returnType);
    } catch (Exception e) {
      var result = new ApiResult<TResult>();

      result.success = false;
      result.errors = Collections.singletonList(e.getMessage());

      return result;
    }
  }

  public static <TResult extends Object> ApiResult<TResult> doGet(String url, Class<TResult> returnType,
      Map<String, String>... queryParams) {
    var client = HttpClient.newHttpClient();

    try {
      var finalUrl = new StringBuilder();
      finalUrl.append(API_URL + url + "?");

      if (queryParams.length > 0) 
        queryParams[0].forEach((key, value) -> finalUrl.append(key + "=" + value + "&"));

      var httpRequest = HttpRequest
          .newBuilder(new URI(finalUrl.toString()))
          .GET()
          .build();

      var httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

      return jsonUtils.<TResult>toApiResult(httpResponse.body(), returnType);
    } catch (Exception e) {
      var result = new ApiResult<TResult>();
      result.success = false;
      result.errors = Collections.singletonList(e.getMessage());

      return result;
    }
  }
}
