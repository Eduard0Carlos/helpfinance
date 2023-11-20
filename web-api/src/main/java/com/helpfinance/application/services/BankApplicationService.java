package com.helpfinance.application.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.application.interfaces.IBankApplicationService;
import com.helpfinance.application.models.bank.BankAddModel;
import com.helpfinance.application.services.base.BaseService;
import com.helpfinance.domain.entities.Bank;
import com.helpfinance.domain.interfaces.services.IBankDomainService;

@Service
public class BankApplicationService extends BaseService implements IBankApplicationService {
  @Autowired
  private IBankDomainService _bankService;

  @Override
  public Bank insert(BankAddModel bankModel) {
    var newBank = new Bank(bankModel.userId, bankModel.name, bankModel.bankAccountNumber, bankModel.bankAgencyNumber, bankModel.integrationToken);

    _bankService.insert(newBank);

    if (!super.notificationService.hasErrors())
      super.unitOfWork.commit();

    return newBank;
  }

  @Override
  public List<Bank> getAll(UUID userId) {
    return _bankService.getAll(userId);
  }
}
