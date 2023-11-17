package com.helpfinance.application.services.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.helpfinance.core.services.NotificationService;
import com.helpfinance.infrastructure.unitOfWork.UnitOfWork;

public class BaseService {
    @Autowired
    protected NotificationService notificationService;

    @Autowired
    protected UnitOfWork unitOfWork;
}
