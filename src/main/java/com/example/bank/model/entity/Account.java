package com.example.bank.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id // to make it primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // to generate the auto id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CUSTOMER-ID")
    private Integer customerId;

    /*@Column(name = "ACCOUNT_ID")
    private String accountId;*/

    /*@Temporal(value = TemporalType.TIMESTAMP)
    private Date createdDate;*/


    public Account() {
    }

    public Account(Long id, Integer customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    /*public Account(Long id, String accountId) {
        this.id = id;
        this.accountId = accountId;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /*public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }*/
}
