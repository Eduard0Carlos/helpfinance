package com.helpfinance.domain.interfaces.services.base;

import java.util.List;
import java.util.UUID;

import com.helpfinance.domain.entities.base.EntityBase;

public interface IDomainService<TEntity extends EntityBase> {
    Boolean insert(TEntity entity);
    Boolean update(TEntity entity);
    Boolean delete(TEntity entity);
    Boolean delete(UUID id);
    List<TEntity> getAll();
    TEntity get(UUID id);
}
