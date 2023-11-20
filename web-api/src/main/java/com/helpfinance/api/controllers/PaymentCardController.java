package com.helpfinance.api.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpfinance.api.controllers.base.ApiController;
import com.helpfinance.application.interfaces.IPaymentCardApplicationService;
import com.helpfinance.application.models.paymentCard.PaymentCardAddModel;
import com.helpfinance.core.data.IHttpResult;

@CrossOrigin
@RestController
@RequestMapping("api/v1/paymentcard")
public class PaymentCardController extends ApiController {

  @Autowired
  private IPaymentCardApplicationService _cardApplicationService;

  @GetMapping("{userId}")
  IHttpResult get(@PathVariable UUID userId) {
    return getResult(_cardApplicationService.getAll(userId));
  }

  @PostMapping
  IHttpResult post(@RequestBody PaymentCardAddModel model) {
    return getResult(_cardApplicationService.insert(model));
  }
}
