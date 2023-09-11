package entities;

import entities.base.EntityBase;
import interfaces.IEntity;
import java.util.UUID;

public class Bank extends EntityBase implements IEntity<Bank> {
    public UUID userId;
    public String name;
    public int bankAccountNumber;
    public int bankAgencyNumber;
    public String integrationToken;

    public Bank(UUID userId, String name, int bankAccountNumber, int bankAgencyNumber, String integrationToken)
    {
        super();

        this.setUserId(userId);
        this.setName(name);
        this.setBankAccountNumber(bankAccountNumber);
        this.setBankAgencyNumber(bankAgencyNumber);
        this.setIntegrationToken(integrationToken);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public int getBankAgencyNumber() {
        return bankAgencyNumber;
    }

    public void setBankAgencyNumber(int bankAgencyNumber) {
        this.bankAgencyNumber = bankAgencyNumber;
    }

    public String getIntegrationToken() {
        return integrationToken;
    }

    public void setIntegrationToken(String integrationToken) {
        this.integrationToken = integrationToken;
    }

    @Override
    public boolean add(Bank bank) {
        return false;
    }

    @Override
    public boolean delete(Bank bank) {
        return false;
    }

    @Override
    public Bank getById(UUID id) {
        return null;
    }
}
