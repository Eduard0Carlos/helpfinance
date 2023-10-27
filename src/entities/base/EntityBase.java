package entities.base;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public abstract class EntityBase {
    protected UUID id;
    protected boolean active;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;

    public EntityBase()
    {
        this.setId(UUID.randomUUID());
        this.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.Activate();
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public boolean getActive() {
        return this.active;
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

    private void setCreatedAt(Timestamp createdAt)
    {
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAt()
    {
        return this.createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Timestamp getUpdatedAt()
    {
        return this.updatedAt;
    }

    @Override
    public String toString() {
        var formattedFields = new StringBuilder();

        var fields = new java.util.ArrayList<>(Arrays.stream(this.getClass().getDeclaredFields()).toList());
        fields.addAll(Arrays.stream(this.getClass().getSuperclass().getDeclaredFields()).toList());

        var methods = Arrays.stream(this.getClass().getMethods()).filter(x -> x.getName().toLowerCase().startsWith("get")).toList();

        for (var field : fields) {
            var getMethod = methods.stream().filter(z -> z.getName().toLowerCase().contains(field.getName().toLowerCase())).findFirst().orElse(null);

            if (getMethod != null) {
                try {
                    var fieldValue = getMethod.invoke(this);

                    formattedFields.append("\r\n" + field.getName() + ": " + fieldValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return formattedFields.toString();
    }
}
