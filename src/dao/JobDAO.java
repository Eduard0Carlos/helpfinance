package dao;

import dao.base.baseEntityDAO;
import entities.Job;
import interfaces.dao.IJobDAO;

public class JobDAO extends baseEntityDAO<Job> implements IJobDAO {
    public JobDAO() {
        super(Job.class, "Jobs");
    }
}