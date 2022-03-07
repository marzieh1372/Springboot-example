package com.example.bank.model.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ACCOUNT_ID")
    private String accountId;

    /*@Temporal(value = TemporalType.TIMESTAMP)
    private Date createdDate;*/




    public Account() {
    }

    public Account(Long id, String accountId) {
        this.id = id;
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
