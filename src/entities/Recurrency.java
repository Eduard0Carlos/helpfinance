package entities;

import entities.base.EntityBase;
import enums.DayOfWeek;
import interfaces.entity.IEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Recurrency extends EntityBase implements IEntity<Recurrency> {
    private UUID movimentationId;
    private Timestamp startDate;
    private Timestamp endDate;
    private DayOfWeek daysOfWeek;
    private boolean isMonthly;
    private boolean isAnnual;

    private Recurrency() { }

    public Recurrency(UUID movimentationId, Timestamp startDate, Timestamp endDate, DayOfWeek daysOfWeek, boolean isMonthly, boolean isAnnual)
    {
        super();

        this.setMovimentationId(movimentationId);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setDaysOfWeek(daysOfWeek);
        this.setMonthly(isMonthly);
        this.setAnnual(isAnnual);
    }

    public UUID getMovimentationId() {
        return movimentationId;
    }

    public void setMovimentationId(UUID movimentationId) {
        this.movimentationId = movimentationId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public DayOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(DayOfWeek daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public boolean getIsMonthly() {
        return isMonthly;
    }

    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }

    public boolean getIsAnnual() {
        return isAnnual;
    }

    public void setAnnual(boolean annual) {
        isAnnual = annual;
    }
}
