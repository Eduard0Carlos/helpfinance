package com.helpfinance.application.interfaces;

import java.util.List;
import java.util.UUID;

import com.helpfinance.application.models.bank.BankAddModel;
import com.helpfinance.domain.entities.Bank;

public interface IBankApplicationService {
  Bank insert(BankAddModel bankAddModel);
  List<Bank> getAll(UUID userId);
}
