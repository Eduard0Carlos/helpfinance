package com.helpfinance.domain.entities;

import com.helpfinance.domain.entities.base.EntityBase;
import com.helpfinance.domain.interfaces.entities.IEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class Investment extends EntityBase implements IEntity<Investment> {
    private UUID userId;
    private String stockId;
    private BigDecimal amount;

    private Investment() { }

    public Investment(UUID userId, String stockId, int amount)
    {
        super();

        this.setUserId(userId);
        this.setStockId(stockId);
        this.setAmount(BigDecimal.valueOf(amount));
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
