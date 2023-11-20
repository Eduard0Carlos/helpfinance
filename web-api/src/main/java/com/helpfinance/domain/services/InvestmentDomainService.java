package com.helpfinance.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.domain.entities.Investment;
import com.helpfinance.domain.interfaces.services.IInvestmentDomainService;
import com.helpfinance.domain.services.base.DomainService;
import com.helpfinance.infrastructure.interfaces.IInvestmentRepository;

@Service
public class InvestmentDomainService extends DomainService<Investment> implements IInvestmentDomainService {

  @Autowired
  private IInvestmentRepository _investmentRepository;

  public InvestmentDomainService(@Autowired IInvestmentRepository repository) {
    super(repository);
  }

  @Override
  public List<Investment> getAll(UUID userId) {
    return _investmentRepository.getAll(userId);
  }
}
