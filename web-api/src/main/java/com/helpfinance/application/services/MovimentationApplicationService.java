package com.helpfinance.application.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.application.interfaces.IMovimentationApplicationService;
import com.helpfinance.application.models.movimentation.MovimentationAddModel;
import com.helpfinance.application.services.base.BaseService;
import com.helpfinance.domain.entities.Movimentation;
import com.helpfinance.domain.interfaces.services.IMovimentationDomainService;

@Service
public class MovimentationApplicationService extends BaseService implements IMovimentationApplicationService {

    @Autowired
    private IMovimentationDomainService _movimentationDomainService;

    @Override
    public Movimentation insert(MovimentationAddModel model) {
      var newMovimentation = new Movimentation(model.userId, model.title, model.amount, model.category, model.movType, Timestamp.valueOf(model.date));

      _movimentationDomainService.insert(newMovimentation);

      return newMovimentation;
    }

    @Override
    public List<Movimentation> getAll(UUID userId, LocalDateTime from, LocalDateTime to) {
      return _movimentationDomainService.getAll(userId, Timestamp.valueOf(from), Timestamp.valueOf(to));
    }
  
}
