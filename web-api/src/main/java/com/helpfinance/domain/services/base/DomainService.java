package com.helpfinance.domain.services.base;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.helpfinance.core.services.NotificationService;
import com.helpfinance.domain.entities.base.EntityBase;
import com.helpfinance.domain.interfaces.services.base.IDomainService;
import com.helpfinance.infrastructure.interfaces.base.IRepository;
import com.helpfinance.infrastructure.unitOfWork.UnitOfWork;

public class DomainService<TEntity extends EntityBase> implements IDomainService<TEntity> {
    @Autowired
    protected UnitOfWork unitOfWork;

    @Autowired
    protected NotificationService notificationService;

    private IRepository<TEntity> _repository;

    public DomainService(IRepository<TEntity> repository) {
        super();
        _repository = repository;
    }

    @Override
    public Boolean insert(TEntity entity) {
        return _repository.insert(entity);
    }

    @Override
    public Boolean update(TEntity entity) {
        return _repository.update(entity);
    }

    @Override
    public Boolean delete(TEntity entity) {
        return _repository.delete(entity);
    }

    @Override
    public Boolean delete(UUID id) {
        return _repository.delete(id);
    }

    @Override
    public List<TEntity> getAll() {
        return _repository.getAll();
    }

    @Override
    public TEntity get(UUID id) {
        return _repository.get(id);
    }
}
