package com.helpfinance.application.interfaces;

import java.util.List;
import java.util.UUID;

import com.helpfinance.application.models.investment.InvestmentAddModel;
import com.helpfinance.domain.entities.Investment;

public interface IInvestmentApplicationService {
  Investment insert(InvestmentAddModel investmentModel);
  List<Investment> getAll(UUID userId);
}
