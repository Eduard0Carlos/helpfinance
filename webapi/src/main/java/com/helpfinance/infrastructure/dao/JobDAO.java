package com.helpfinance.infrastructure.dao;

import com.helpfinance.infrastructure.dao.base.baseEntityDAO;
import com.helpfinance.infrastructure.interfaces.IJobDAO;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Job;

@Repository
public class JobDAO extends baseEntityDAO<Job> implements IJobDAO {
    public JobDAO() {
        super(Job.class, "Jobs");
    }
}