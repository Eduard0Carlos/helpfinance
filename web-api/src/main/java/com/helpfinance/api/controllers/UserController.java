package com.helpfinance.api.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpfinance.api.controllers.base.ApiController;
import com.helpfinance.application.models.user.UserAddModel;
import com.helpfinance.application.services.UserApplicationService;
import com.helpfinance.core.data.IHttpResult;

@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
public class UserController extends ApiController {

    @Autowired
    private UserApplicationService _userApplicationService;

    @GetMapping
    IHttpResult get(Optional<UUID> id, Optional<String> email, Optional<String> password) {
        if (!email.isEmpty() && !password.isEmpty())
            return getResult(_userApplicationService.get(email.get(), password.get()));

        if (!id.isEmpty())
            return getResult(_userApplicationService.get(id.get()));

        return getResult(_userApplicationService.getAll());
    }
    
    @PostMapping
    IHttpResult post(@RequestBody UserAddModel model) {
        return getResult(_userApplicationService.insert(model));
    }
}