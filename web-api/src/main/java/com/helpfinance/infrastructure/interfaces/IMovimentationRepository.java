package com.helpfinance.infrastructure.interfaces;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.helpfinance.domain.entities.Movimentation;
import com.helpfinance.infrastructure.interfaces.base.IRepository;

public interface IMovimentationRepository extends IRepository<Movimentation> {
  List<Movimentation> getAll(UUID userId, Timestamp from, Timestamp to);
}
