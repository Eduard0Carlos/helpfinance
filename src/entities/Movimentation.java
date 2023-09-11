package entities;

import entities.base.EntityBase;
import enums.MovimentationCategory;
import enums.MovimentationType;
import interfaces.IEntity;

import java.util.UUID;

public class Movimentation extends EntityBase implements IEntity<Movimentation> {
    private UUID userId;
    private String title;
    private int amount;
    private MovimentationCategory category;
    private MovimentationType type;

    public Movimentation(UUID userId, String title, int amount, MovimentationCategory category, MovimentationType type)
    {
        super();

        this.setUserId(userId);
        this.setTitle(title);
        this.setAmount(amount);
        this.setCategory(category);
        this.setType(type);
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

    public MovimentationType getType() {
        return type;
    }

    public void setType(MovimentationType type) {
        this.type = type;
    }

    @Override
    public boolean add(Movimentation movimentation) {
        return false;
    }

    @Override
    public boolean delete(Movimentation movimentation) {
        return false;
    }

    @Override
    public Movimentation getById(UUID id) {
        return null;
    }
}
