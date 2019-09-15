package com.parent.validation.model;


import com.parent.validation.validators.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Conditional(selected = "type", values = {"IBAN_TO_IBAN","IBAN_TO_WALLET"}, required = {"payer","payer.iban","payee"} )
@Conditional(selected = "type", values = {"IBAN_TO_IBAN","WALLET_TO_IBAN"}, required = {"payer","payee","payee.iban"} )
public class Transaction {

    @NotBlank(message = "Tipul tranzactiei este obligatoriu")
    @TypeConstraint
    private String type;

    @NotBlank(message = "Descrierea este obligatorie")
    private String description;

    @NotNull(message = "Suma este obligatorie")
    @DecimalMin(value = "0.01", message = "Suma este prea mica pentru a putea fi tranzactionata")
    private BigDecimal amount;

    @NotNull(message = "Expeditorul este obligatoriu")
    @Valid
    private TransactionMember payer;

    @NotNull(message = "Destinatarul este obligatoriu")
    @Valid
    private TransactionMember payee;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionMember getPayer() {
        return payer;
    }

    public void setPayer(TransactionMember payer) {
        this.payer = payer;
    }

    public TransactionMember getPayee() {
        return payee;
    }

    public void setPayee(TransactionMember payee) {
        this.payee = payee;
    }
}