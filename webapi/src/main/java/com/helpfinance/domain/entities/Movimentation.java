package com.helpfinance.domain.entities;

import com.helpfinance.domain.entities.base.EntityBase;
import com.helpfinance.domain.enums.MovimentationCategory;
import com.helpfinance.domain.enums.MovimentationType;
import com.helpfinance.domain.interfaces.entities.IEntity;

import java.util.UUID;

public class Movimentation extends EntityBase implements IEntity<Movimentation> {
    private UUID userId;
    private String title;
    private int amount;
    private MovimentationCategory category;
    private MovimentationType movType;

    private Movimentation() { }

    public Movimentation(UUID userId, String title, int amount, MovimentationCategory category, MovimentationType movType)
    {
        super();

        this.setUserId(userId);
        this.setTitle(title);
        this.setAmount(amount);
        this.setCategory(category);
        this.setMovType(movType);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public MovimentationCategory getCategory() {
        return category;
    }

    public void setCategory(MovimentationCategory category) {
        this.category = category;
    }

    public MovimentationType getMovType() {
        return movType;
    }

    public void setMovType(MovimentationType type) {
        this.movType = type;
    }
}
