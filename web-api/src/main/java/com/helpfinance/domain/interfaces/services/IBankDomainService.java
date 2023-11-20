package com.helpfinance.domain.interfaces.services;

import java.util.List;
import java.util.UUID;

import com.helpfinance.domain.entities.Bank;
import com.helpfinance.domain.interfaces.services.base.IDomainService;

public interface IBankDomainService extends IDomainService<Bank> {
  List<Bank> getAll(UUID userId);
}