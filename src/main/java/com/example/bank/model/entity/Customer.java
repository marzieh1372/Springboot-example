package com.example.bank.model.entity;

import com.example.bank.model.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

@Data
@Entity(name = "customer")
@DiscriminatorValue("Customer")
@AllArgsConstructor
@Builder
@JsonDeserialize(builder = Customer.CustomerBuilder.class)
public class Customer extends Person {

  @Column(name = "CUSTOMERID", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer customerId;

  @OneToMany(
      mappedBy = "customer",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL) // har balaie sare pedar omad sare bacheham biad
  private Set<Account> accountSet = new HashSet<>();

  public Customer() {
    super();
  }

  public Customer(
      Integer customerId,
      Integer id,
      String userName,
      String firstName,
      String lastName,
      String email,
      String phone,
      String nationalId,
      Gender gender,
      LocalDate birthday) {
    super(id, userName, firstName, lastName, email, phone, nationalId, gender, birthday);
    this.customerId = customerId;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonPOJOBuilder(withPrefix = "")
  public static class CustomerBuilder {}
}
