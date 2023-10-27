package entities;

import entities.base.EntityBase;
import interfaces.entity.IEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class User extends EntityBase implements IEntity<User> {
    private String name;
    private Byte[] image;
    private Timestamp birthdate;
    private BigDecimal childrenNumber;
    private String email;
    private String password;
    private BigDecimal balance;
    private BigDecimal monthlySpendingsLimit;
    private BigDecimal investmentProfileLevel;

    private User() { }

    public User(String name, Timestamp birthdate, String email, String password)
    {
        super();

        this.setName(name);
        this.setBirthdate(birthdate);
        this.setEmail(email);
        this.setPassword(password);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private String cpf;

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    public BigDecimal getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(BigDecimal childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void addBalance(BigDecimal balance) {
        this.balance.add(balance);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void removeBalance(BigDecimal balance)
    {
        this.balance.subtract(balance);
    }

    public BigDecimal getMonthlySpendingsLimit() {
        return monthlySpendingsLimit;
    }

    public void setMonthlySpendingsLimit(BigDecimal monthlySpendingsLimit) {
        this.monthlySpendingsLimit = monthlySpendingsLimit;
    }

    public BigDecimal getInvestmentProfileLevel() {
        return investmentProfileLevel;
    }

    public void setInvestmentProfileLevel(BigDecimal investmentProfileLevel) {
        this.investmentProfileLevel = investmentProfileLevel;
    }
}
