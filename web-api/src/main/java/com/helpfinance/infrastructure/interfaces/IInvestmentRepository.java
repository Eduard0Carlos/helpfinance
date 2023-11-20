package com.helpfinance.infrastructure.interfaces;

import java.util.List;
import java.util.UUID;

import com.helpfinance.domain.entities.Investment;
import com.helpfinance.infrastructure.interfaces.base.IRepository;

public interface IInvestmentRepository extends IRepository<Investment> {
  List<Investment> getAll(UUID userId);
}
