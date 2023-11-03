package com.helpfinance.api.controllers.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.helpfinance.core.data.Result;
import com.helpfinance.core.factories.ResultFactory;
import com.helpfinance.core.services.NotificationService;

public abstract class ApiController {

    @Autowired
    private NotificationService _notificationService;

    protected <T> Result getResult(T data) {
        if (_notificationService.hasErrors())
            return ResultFactory.getFailureResult(_notificationService.getErrors());
        
        return ResultFactory.getSuccessResult(data);
    }
}
