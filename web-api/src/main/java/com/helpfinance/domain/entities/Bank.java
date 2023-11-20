package com.helpfinance.domain.entities;

import com.helpfinance.domain.entities.base.EntityBase;
import com.helpfinance.domain.interfaces.entities.IEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class Bank extends EntityBase implements IEntity<Bank> {
    private UUID userId;
    private String name;
    private BigDecimal bankAccountNumber;
    private BigDecimal bankAgencyNumber;
    private String integrationToken;

    private Bank() { }

    public Bank(UUID userId, String name, int bankAccountNumber, int bankAgencyNumber, String integrationToken)
    {
        super();

        this.setUserId(userId);
        this.setName(name);
        this.setBankAccountNumber(BigDecimal.valueOf(bankAccountNumber));
        this.setBankAgencyNumber(BigDecimal.valueOf(bankAgencyNumber));
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

    public BigDecimal getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(BigDecimal bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public BigDecimal getBankAgencyNumber() {
        return bankAgencyNumber;
    }

    public void setBankAgencyNumber(BigDecimal bankAgencyNumber) {
        this.bankAgencyNumber = bankAgencyNumber;
    }

    public String getIntegrationToken() {
        return integrationToken;
    }

    public void setIntegrationToken(String integrationToken) {
        this.integrationToken = integrationToken;
    }
}
