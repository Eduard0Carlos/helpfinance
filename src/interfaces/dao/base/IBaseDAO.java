package interfaces.dao.base;

import entities.base.EntityBase;

import java.util.List;
import java.util.UUID;

public interface IBaseDAO<TEntity extends EntityBase> {
    void insert(TEntity entity);
    void update(TEntity entity);
    void delete(TEntity entity);
    void delete(UUID id);
    List<TEntity> getAll();
    TEntity get(UUID id);
}
