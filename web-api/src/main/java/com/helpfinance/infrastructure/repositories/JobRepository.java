package com.helpfinance.infrastructure.repositories;

import com.helpfinance.infrastructure.interfaces.IJobRepository;
import com.helpfinance.infrastructure.repositories.base.EntityRepository;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Job;

@Repository
public class JobRepository extends EntityRepository<Job> implements IJobRepository {
    
    public JobRepository() { super("Jobs"); }
}