package com.helpfinance.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpfinance.application.interfaces.IJobApplicationService;
import com.helpfinance.application.models.job.JobAddModel;
import com.helpfinance.application.services.base.BaseService;
import com.helpfinance.domain.entities.Job;
import com.helpfinance.domain.interfaces.services.IJobDomainService;

@Service
public class JobApplicationService extends BaseService implements IJobApplicationService {

    @Autowired
    private IJobDomainService _jobDomainService;

    @Override
    public Job insert(JobAddModel jobModel) {
        var newJob = new Job(jobModel.userId.get(), jobModel.companyName, jobModel.title, jobModel.netSallary);

        _jobDomainService.insert(newJob);

        return newJob;
    }
}
