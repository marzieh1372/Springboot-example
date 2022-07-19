package com.example.bank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "CUSTOMER")
@JsonDeserialize(builder = Customer.CustomerBuilder.class)
public class Customer {

   /* @Embedded
    private Address address;*/

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID")
   private Integer id;

   @Column(name = "USERNAME", nullable = false, unique = true)
   private String userName;

   @Column(name = "FIRSTNAME")
   private String firstName;

   @Column(name = "LASTNAME")
   private String lastName;

   @Column(name = "EMAIL")
   private String email;

   @Column(name = "PHONENUMBER")
   private String phone;

   public Customer(Integer id, String userName, String firstName, String lastName, String email, String phone){
      this.id = id;
      this.firstName=firstName;
      this.userName=userName;
      this.lastName=lastName;
      this.email=email;
      this.phone=phone;
   }

   @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // har balaie sare pedar omad sare bacheham biad
   private Set<Account> accountSet = new HashSet<>();

   @JsonIgnoreProperties(ignoreUnknown = true)
   @JsonPOJOBuilder(withPrefix = "")
   public static class CustomerBuilder {}
}
