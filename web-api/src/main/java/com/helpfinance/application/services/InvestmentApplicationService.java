package com.helpfinance.application.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.application.interfaces.IInvestmentApplicationService;
import com.helpfinance.application.models.investment.InvestmentAddModel;
import com.helpfinance.application.services.base.BaseService;
import com.helpfinance.domain.entities.Investment;
import com.helpfinance.domain.interfaces.services.IInvestmentDomainService;

@Service
public class InvestmentApplicationService extends BaseService implements IInvestmentApplicationService {
  @Autowired
  private IInvestmentDomainService _investmentService;

  @Override
  public Investment insert(InvestmentAddModel investmentModel) {
    var newInvestment = new Investment(investmentModel.userId, investmentModel.stockId, investmentModel.amount);

    _investmentService.insert(newInvestment);

    if (!super.notificationService.hasErrors())
      super.unitOfWork.commit();

    return newInvestment;
  }

  @Override
  public List<Investment> getAll(UUID userId) {
    return _investmentService.getAll(userId);
  }
}
