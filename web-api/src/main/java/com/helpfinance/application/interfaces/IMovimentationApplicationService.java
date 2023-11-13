package com.helpfinance.application.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.helpfinance.application.models.movimentation.MovimentationAddModel;
import com.helpfinance.domain.entities.Movimentation;

public interface IMovimentationApplicationService {
  Movimentation insert(MovimentationAddModel model);
  List<Movimentation> getAll(UUID userId, LocalDateTime from, LocalDateTime to);
}
