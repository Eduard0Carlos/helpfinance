package com.helpfinance.domain.interfaces.services;

import java.util.List;
import java.util.UUID;

import com.helpfinance.domain.entities.Investment;
import com.helpfinance.domain.interfaces.services.base.IDomainService;

public interface IInvestmentDomainService extends IDomainService<Investment> {
  List<Investment> getAll(UUID userId);
}
