package entities;

import entities.base.EntityBase;
import interfaces.IEntity;

import java.util.UUID;

public class Investment extends EntityBase implements IEntity<Investment> {
    public UUID userId;
    public String stockId;
    public int amount;

    public Investment(UUID userId, String stockId, int amount)
    {
        super();

        this.setUserId(userId);
        this.setStockId(stockId);
        this.setAmount(amount);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean add(Investment investment) {
        return false;
    }

    @Override
    public boolean delete(Investment investment) {
        return false;
    }

    @Override
    public Investment getById(UUID id) {
        return null;
    }
}
