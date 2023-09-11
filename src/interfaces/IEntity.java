package interfaces;

import entities.base.EntityBase;

import java.util.UUID;

public interface IEntity<TEntity extends EntityBase> {
    public boolean add(TEntity entity);
    public boolean delete(TEntity entity);
    public TEntity getById(UUID id);
}
