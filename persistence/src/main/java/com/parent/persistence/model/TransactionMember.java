package com.parent.persistence.model;

/*
* Din structura raportului inteleg ca o persoana poate avea un singur cont(voi merge pe aceasta varianta pentru a simplifica schema Entitatilor)
* In cazul in care o persoana poate avea mai multe conturi:
*
    * - se creeaza o alta entitate Account in care se vor muta: IBAN,
    * - entitatea Account va contine o coloana de legatura cu TransactionActor (@ManyToOne)
    * - in entitatea Transaction coloanele payer si payee nu vor mai contine TransactionActor, ci Account
 */

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
public class TransactionMember {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CNP", nullable = false, length = 13, unique = true)
    private String cnp;

    @Column(name = "IBAN", length = 26)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String iban;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

}
