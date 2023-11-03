package com.helpfinance.infrastructure.unitOfWork;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.core.services.NotificationService;

@Service
public class UnitOfWork {
    @Autowired(required = false)
    private Connection _connection;

    @Autowired
    private NotificationService _notificationService;

    public Boolean commit() {
        try {
            _connection.commit();
            return true;
        } catch (Exception e) {
            _notificationService.addError("Houve um problema ao tentar salvar suas alterações!");
            return false;
        }
    }
}
