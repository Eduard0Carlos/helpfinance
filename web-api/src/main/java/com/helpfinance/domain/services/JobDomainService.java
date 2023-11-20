package com.helpfinance.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.domain.entities.Job;
import com.helpfinance.domain.interfaces.services.IJobDomainService;
import com.helpfinance.domain.services.base.DomainService;
import com.helpfinance.infrastructure.interfaces.IJobRepository;

@Service
public class JobDomainService extends DomainService<Job> implements IJobDomainService {

    @Autowired
    private IJobRepository _jobRepository;

    public JobDomainService(@Autowired IJobRepository repository) {
        super(repository);
    }
}
