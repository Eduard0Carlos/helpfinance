package com.helpfinance.application.services.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.helpfinance.core.services.NotificationService;

public class BaseService {
    @Autowired
    protected NotificationService notificationService; 
}
