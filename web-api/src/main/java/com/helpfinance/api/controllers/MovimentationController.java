package com.helpfinance.api.controllers;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpfinance.api.controllers.base.ApiController;
import com.helpfinance.application.interfaces.IMovimentationApplicationService;
import com.helpfinance.application.models.movimentation.MovimentationAddModel;
import com.helpfinance.core.data.IHttpResult;

@CrossOrigin
@RestController
@RequestMapping("api/v1/movimentation")
public class MovimentationController extends ApiController {

    @Autowired
    private IMovimentationApplicationService _movimentationApplicationService;

    @GetMapping("{userId}")
    IHttpResult get(@PathVariable UUID userId, Optional<LocalDateTime> from, Optional<LocalDateTime> to) {
        return getResult(_movimentationApplicationService.getAll(userId, from.get(), to.get()));
    }
    
    @PostMapping
    IHttpResult post(@RequestBody MovimentationAddModel model) {
        return getResult(_movimentationApplicationService.insert(model));
    }
}