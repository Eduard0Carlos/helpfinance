package com.helpfinance.domain.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.domain.entities.Movimentation;
import com.helpfinance.domain.interfaces.services.IMovimentationDomainService;
import com.helpfinance.domain.services.base.DomainService;
import com.helpfinance.infrastructure.interfaces.IMovimentationRepository;

@Service
public class MovimentationDomainService extends DomainService<Movimentation> implements IMovimentationDomainService {

  @Autowired
  private IMovimentationRepository _movimentationRepository;

  public MovimentationDomainService(@Autowired IMovimentationRepository repository) {
    super(repository);
  }

  @Override
  public List<Movimentation> getAll(UUID userId, Timestamp from, Timestamp to) {
    return _movimentationRepository.getAll(userId, from, to);
  }

}
