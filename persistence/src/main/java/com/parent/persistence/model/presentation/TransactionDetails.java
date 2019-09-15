package com.parent.persistence.model.presentation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransactionDetails {

    @JsonIgnore
    private String type;

    @JsonProperty("Suma tranzactionata")
    private BigDecimal amount;

    @JsonProperty("Descriere tranzactie")
    private String description;

    @JsonProperty("Nume destinatar")
    private String payeeName;

    @JsonProperty("CNP destinatar")
    private String payeeCnp;

    @JsonProperty("IBAN destinatar")
    private String payeeIban;

    public TransactionDetails(String type, BigDecimal amount, String description, String payeeName, String payeeCnp, String payeeIban) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.payeeName = payeeName;
        this.payeeCnp = payeeCnp;
        this.payeeIban = payeeIban;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeCnp() {
        return payeeCnp;
    }

    public void setPayeeCnp(String payeeCnp) {
        this.payeeCnp = payeeCnp;
    }

    public String getPayeeIban() {
        return payeeIban;
    }

    public void setPayeeIban(String payeeIban) {
        this.payeeIban = payeeIban;
    }
}
