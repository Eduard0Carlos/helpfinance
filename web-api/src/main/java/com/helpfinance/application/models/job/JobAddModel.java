package com.helpfinance.application.models.job;

import java.util.Optional;
import java.util.UUID;

public class JobAddModel {
    public Optional<UUID> userId;
    public String companyName;
    public String title;
    public int netSallary;
}
