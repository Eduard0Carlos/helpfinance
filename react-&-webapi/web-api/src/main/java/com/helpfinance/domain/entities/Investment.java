package com.helpfinance.domain.entities;

import com.helpfinance.domain.entities.base.EntityBase;
import com.helpfinance.domain.interfaces.entities.IEntity;

import java.util.UUID;

public class Investment extends EntityBase implements IEntity<Investment> {
    private UUID userId;
    private String stockId;
    private int amount;

    private Investment() { }

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
}
