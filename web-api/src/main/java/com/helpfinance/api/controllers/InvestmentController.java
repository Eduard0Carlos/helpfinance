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
import com.helpfinance.application.interfaces.IInvestmentApplicationService;
import com.helpfinance.application.models.investment.InvestmentAddModel;
import com.helpfinance.core.data.IHttpResult;

@CrossOrigin
@RestController
@RequestMapping("api/v1/investment")
public class InvestmentController extends ApiController {

  @Autowired
  private IInvestmentApplicationService _investmentApplicationService;

  @GetMapping("{userId}")
  IHttpResult get(@PathVariable UUID userId) {
    return getResult(_investmentApplicationService.getAll(userId));
  }

  @PostMapping
  IHttpResult post(@RequestBody InvestmentAddModel model) {
    return getResult(_investmentApplicationService.insert(model));
  }
}