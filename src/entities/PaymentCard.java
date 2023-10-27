package entities;

import entities.base.EntityBase;
import enums.PaymentNetwork;
import enums.PaymentType;
import interfaces.entity.IEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentCard extends EntityBase implements IEntity<PaymentCard> {
    private UUID userId;
    private String cardNumber;
    private String nickname;
    private Timestamp expirationDate;
    private String cvv;
    private PaymentNetwork paymentNetwork;
    private PaymentType paymentType;

    private PaymentCard() { }

    public PaymentCard(UUID userId, String cardNumber, String nickname, Timestamp expirationDate, String cvv, PaymentNetwork paymentNetwork, PaymentType paymentType)
    {
        super();

        this.setUserId(userId);
        this.setCardNumber(cardNumber);
        this.setNickname(nickname);
        this.setExpirationDate(expirationDate);
        this.setCvv(cvv);
        this.setPaymentNetwork(paymentNetwork);
        this.setPaymentType(paymentType);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String number) {
        this.cardNumber = number;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType type) {
        this.paymentType = type;
    }
}
