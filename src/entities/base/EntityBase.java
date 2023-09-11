package entities.base;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class EntityBase {
    private UUID id;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EntityBase()
    {
        this.setId(UUID.randomUUID());
        this.setCreatedAt(LocalDateTime.now());
        this.Activate();
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public UUID getId()
    {
        return this.id;
    }

    public void Activate()
    {
        this.active = true;
    }

    public void Deactivate()
    {
        this.active = false;
    }

    public boolean isActive()
    {
        return this.active;
    }

    private void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt()
    {
        return this.createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt()
    {
        return this.updatedAt;
    }
}
