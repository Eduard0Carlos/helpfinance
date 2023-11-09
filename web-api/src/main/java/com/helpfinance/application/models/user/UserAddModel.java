package com.helpfinance.application.models.user;

import java.sql.Timestamp;

import com.helpfinance.application.models.address.AddressAddModel;
import com.helpfinance.application.models.job.JobAddModel;

public class UserAddModel {
    public String name;
    public String email;
    public String password;
    public Timestamp birthdate;
    public AddressAddModel address;
    public JobAddModel job;
}
