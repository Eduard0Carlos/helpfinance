package entities;

import entities.base.EntityBase;
import enums.PaymentNetwork;
import enums.PaymentType;
import interfaces.IEntity;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentCard extends EntityBase implements IEntity<PaymentCard> {
    private UUID userId;
    private String number;
    private String nickname;
    private LocalDateTime expirationDate;
    private String cvv;
    private PaymentNetwork paymentNetwork;
    private PaymentType type;

    public PaymentCard(UUID userId, String number, String nickname, LocalDateTime expirationDate, String cvv, PaymentNetwork paymentNetwork, PaymentType type)
    {
        super();

        this.setUserId(userId);
        this.setNumber(number);
        this.setNickname(nickname);
        this.setExpirationDate(expirationDate);
        this.setCvv(cvv);
        this.setPaymentNetwork(paymentNetwork);
        this.setType(type);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public PaymentNetwork getPaymentNetwork() {
        return paymentNetwork;
    }

    public void setPaymentNetwork(PaymentNetwork paymentNetwork) {
        this.paymentNetwork = paymentNetwork;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    @Override
    public boolean add(PaymentCard paymentCard) {
        return false;
    }

    @Override
    public boolean delete(PaymentCard paymentCard) {
        return false;
    }

    @Override
    public PaymentCard getById(UUID id) {
        return null;
    }
}
