package com.parent.persistence.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column (name = "TYPE")
    private String type;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PAYER")
    private TransactionMember payer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PAYEE")
    private TransactionMember payee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
