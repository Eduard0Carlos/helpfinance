package com.helpfinance.infrastructure.interfaces;

import java.util.List;
import java.util.UUID;

import com.helpfinance.domain.entities.Bank;
import com.helpfinance.infrastructure.interfaces.base.IRepository;

public interface IBankRepository extends IRepository<Bank> {
    List<Bank> getAll(UUID userId);
}
