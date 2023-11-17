package com.helpfinance.core.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class NotificationService {
    private List<String> _errors = new ArrayList<String>();

    public void addError(String error) {
        _errors.add(error);
    }

    public void clearErrors() {
        _errors.removeIf(x -> true);
    }

    public List<String> getErrors() {
        return _errors;
    }

    public Boolean hasErrors() {
        return !_errors.isEmpty();
    }
}
