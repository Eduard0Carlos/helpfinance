package com.helpfinance.application.interfaces;

import com.helpfinance.application.models.job.JobAddModel;
import com.helpfinance.domain.entities.Job;

public interface IJobApplicationService {
    Job Insert(JobAddModel jobModel);
}
