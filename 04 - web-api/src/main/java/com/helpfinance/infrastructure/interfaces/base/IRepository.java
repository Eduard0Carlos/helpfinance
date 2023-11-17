package com.helpfinance.infrastructure.interfaces.base;

import com.helpfinance.domain.entities.base.EntityBase;

import java.util.List;
import java.util.UUID;

public interface IRepository<TEntity extends EntityBase> {
    Boolean insert(TEntity entity);
    Boolean update(TEntity entity);
    Boolean delete(TEntity entity);
    Boolean delete(UUID id);
    List<TEntity> getAll();
    TEntity get(UUID id);
}
