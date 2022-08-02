package com.example.bank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BANK")
@Builder
@JsonDeserialize(builder = Bank.BankBuilder.class)
public class Bank {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "CITY", nullable = false)
  private String city;

  @Column(name = "CODE", nullable = false)
  private String code;

  @Column(name = "PHONE")
  private String phone;

  @Column(name = "EMAIL")
  private String email;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "addressId", referencedColumnName = "id", nullable = false)
  private Address address;

  @OneToMany(
      mappedBy = "bank",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL) // har balaie sare pedar omad sare bacheham biad
  private Set<Account> accountSet = new HashSet<>();


  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonPOJOBuilder(withPrefix = "")
  public static class BankBuilder {}
}
