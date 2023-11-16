package com.helpfinance.domain.interfaces.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.helpfinance.domain.entities.Movimentation;
import com.helpfinance.domain.interfaces.services.base.IDomainService;

public interface IMovimentationDomainService extends IDomainService<Movimentation> {
  List<Movimentation> getAll(UUID userId, Timestamp from, Timestamp to);
}
