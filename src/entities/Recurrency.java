package entities;

import entities.base.EntityBase;
import enums.DayOfWeek;
import interfaces.IEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Recurrency extends EntityBase implements IEntity<Recurrency> {
    private UUID movimentationId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private DayOfWeek daysOfWeek;
    private boolean isMonthly;
    private boolean isAnnual;

    public Recurrency(UUID movimentationId, LocalDateTime startDate, LocalDateTime endDate, DayOfWeek daysOfWeek, boolean isMonthly, boolean isAnnual)
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public DayOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(DayOfWeek daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public boolean isMonthly() {
        return isMonthly;
    }

    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }

    public boolean isAnnual() {
        return isAnnual;
    }

    public void setAnnual(boolean annual) {
        isAnnual = annual;
    }

    @Override
    public boolean add(Recurrency recurrency) {
        return false;
    }

    @Override
    public boolean delete(Recurrency recurrency) {
        return false;
    }

    @Override
    public Recurrency getById(UUID id) {
        return null;
    }
}
