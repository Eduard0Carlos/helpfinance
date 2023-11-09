package com.helpfinance.domain.services.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.helpfinance.core.services.NotificationService;
import com.helpfinance.domain.entities.base.EntityBase;
import com.helpfinance.domain.interfaces.services.base.IDomainService;
import com.helpfinance.infrastructure.unitOfWork.UnitOfWork;

public abstract class DomainService<TEntity extends EntityBase> implements IDomainService<TEntity> {
    @Autowired
    protected UnitOfWork unitOfWork;

    @Autowired
    protected NotificationService notificationService;
}
