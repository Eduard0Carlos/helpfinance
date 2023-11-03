package com.helpfinance.api.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpfinance.api.controllers.base.ApiController;
import com.helpfinance.application.services.UserApplicationService;
import com.helpfinance.core.data.Result;

@RestController
@RequestMapping("api/v1/users")
public class UserController extends ApiController {

    @Autowired
    private UserApplicationService _userApplicationService;

    @GetMapping
    Result get(Optional<UUID> id) {
        if (!id.isEmpty())
            return getResult(_userApplicationService.get(id.get()));
            
        return getResult(_userApplicationService.getAll());
    }
}