package com.helpfinance.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.domain.entities.Bank;
import com.helpfinance.domain.interfaces.services.IBankDomainService;
import com.helpfinance.domain.services.base.DomainService;
import com.helpfinance.infrastructure.interfaces.IBankRepository;

@Service
public class BankDomainService extends DomainService<Bank> implements IBankDomainService {

  @Autowired
  private IBankRepository _bankRepository;

  public BankDomainService(@Autowired IBankRepository repository) {
    super(repository);
  }

  @Override
  public List<Bank> getAll(UUID userId) {
    return _bankRepository.getAll(userId);
  }
}
