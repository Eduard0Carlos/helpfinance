package com.helpfinance.application.services;

import java.math.BigDecimal;
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
import com.helpfinance.domain.enums.MovimentationType;
import com.helpfinance.domain.interfaces.services.IMovimentationDomainService;
import com.helpfinance.domain.interfaces.services.IUserDomainService;

@Service
public class MovimentationApplicationService extends BaseService implements IMovimentationApplicationService {

  @Autowired
  private IMovimentationDomainService _movimentationDomainService;

  @Autowired
  private IUserDomainService _userDomainService;

  @Override
  public Movimentation insert(MovimentationAddModel model) {
    var newMovimentation = new Movimentation(model.userId, model.title, model.amount, model.category, model.movType, model.date);

    _movimentationDomainService.insert(newMovimentation);

    var existUser = _userDomainService.get(model.userId);

    if (existUser == null)
      return null;

    if (newMovimentation.getMovType().equals(MovimentationType.INCOMING))
      existUser.addBalance(model.amount);
    else
      existUser.removeBalance(model.amount);

    existUser.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

    if (!_userDomainService.update(existUser))
      return null;

    if (super.notificationService.hasErrors())
      return null;

    super.unitOfWork.commit();

    return newMovimentation;
  }

  @Override
  public List<Movimentation> getAll(UUID userId, LocalDateTime from, LocalDateTime to) {
    return _movimentationDomainService.getAll(userId, Timestamp.valueOf(from), Timestamp.valueOf(to));
  }

  @Override
  public Boolean delete(UUID id) {
    var movimentation = _movimentationDomainService.get(id);
    var existUser = _userDomainService.get(movimentation.getUserId());

    if (existUser == null)
      return null;

    if (movimentation.getMovType().equals(MovimentationType.INCOMING))
      existUser.removeBalance(movimentation.getAmount());
    else
      existUser.addBalance(movimentation.getAmount());

    existUser.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

    if (!_userDomainService.update(existUser))
      return null;

    if (!_movimentationDomainService.delete(id))
      return null;

    if (super.notificationService.hasErrors())
      return null;

    super.unitOfWork.commit();

    return super.unitOfWork.commit();
  }

}
