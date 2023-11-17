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

  @Override
  public Boolean insert(Movimentation entity) {
    return _movimentationRepository.insert(entity);
  }

  @Override
  public Boolean update(Movimentation entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public Boolean delete(Movimentation entity) {
    _movimentationRepository.delete(entity);

    return !super.notificationService.hasErrors() && super.unitOfWork.commit();
  }

  @Override
  public Boolean delete(UUID id) {
    return _movimentationRepository.delete(id);
  }

  @Override
  public List<Movimentation> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public Movimentation get(UUID id) {
    return _movimentationRepository.get(id);
  }

  @Override
  public List<Movimentation> getAll(UUID userId, Timestamp from, Timestamp to) {
    return _movimentationRepository.getAll(userId, from, to);
  }

}
