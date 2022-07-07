package org.example.entity;

import org.example.dao.PaymentDAO;

import javax.persistence.*;

@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "confidential_code")
    private String confidentialCode;
    private String bank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getConfidentialCode() {
        return confidentialCode;
    }

    public void setConfidentialCode(String confidentialCode) {
        this.confidentialCode = confidentialCode;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Payment()
    {
    }

    public Payment (String cardNumber,String confidentialCode,String bank)
    {
        this.cardNumber = cardNumber;
        this.confidentialCode = confidentialCode;
        this.bank = bank;
    }

    public void setNotNullDataPayment(Payment newPaymentData) {
        if (newPaymentData.getCardNumber() != null) {
            this.setCardNumber(newPaymentData.getCardNumber());
        }
        if (newPaymentData.getConfidentialCode() != null) {
            this.setConfidentialCode(newPaymentData.getConfidentialCode());
        }
        if (newPaymentData.getBank() != null) {
            this.setBank(newPaymentData.getBank());
        }
    }
}
