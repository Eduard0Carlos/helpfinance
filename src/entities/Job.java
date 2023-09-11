package entities;

import entities.base.EntityBase;
import interfaces.IEntity;

import java.util.UUID;

public class Job extends EntityBase implements IEntity<Job> {
    private UUID userId;
    private String companyName;
    private String title;
    private int netSallary;

    public Job(UUID userId, String companyName, String title, int netSallary)
    {
        super();

        setUserId(userId);
        setCompanyName(companyName);
        setTitle(title);
        setNetSallary(netSallary);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNetSallary() {
        return netSallary;
    }

    public void setNetSallary(int netSallary) {
        this.netSallary = netSallary;
    }

    @Override
    public boolean add(Job job) {
        return false;
    }

    @Override
    public boolean delete(Job job) {
        return false;
    }

    @Override
    public Job getById(UUID id) {
        return null;
    }
}
