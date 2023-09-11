package entities;

import entities.base.EntityBase;
import interfaces.IEntity;
import java.time.LocalDateTime;
import java.util.UUID;

public class User extends EntityBase implements IEntity<User> {
    private String name;
    private Byte[] image;
    private LocalDateTime birthdate;
    private int childrenNumber;
    private String email;
    private String password;
    private int balance;
    private int monthlySpendingsLimit;
    private int investmentProfileLevel;

    public User(String name, LocalDateTime birthdate, String email, String password)
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

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(int childrenNumber) {
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

    public int getBalance() {
        return balance;
    }

    public void addBalance(int balance) {
        this.balance += balance;
    }

    public void removeBalance(int balance)
    {
        this.balance -= balance;
    }

    public int getMonthlySpendingsLimit() {
        return monthlySpendingsLimit;
    }

    public void setMonthlySpendingsLimit(int monthlySpendingsLimit) {
        this.monthlySpendingsLimit = monthlySpendingsLimit;
    }

    public int getInvestmentProfileLevel() {
        return investmentProfileLevel;
    }

    public void setInvestmentProfileLevel(int investmentProfileLevel) {
        this.investmentProfileLevel = investmentProfileLevel;
    }

    @Override
    public boolean add(User user) {
        // TODO: Insert in database
        return false;
    }

    @Override
    public boolean delete(User user) {
        // TODO: Delete on the database
        return false;
    }

    @Override
    public User getById(UUID id) {
        // TODO: Search for entity in database
        return null;
    }
}
