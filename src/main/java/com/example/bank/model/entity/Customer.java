package com.example.bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID")
   private Long id;

   @Column(name = "USERNAME", nullable = false,unique = true)
   private String userName;

   @Column(name = "FIRSTNAME")
   private String firstName;

   @Column(name = "LASTNAME")
   private String lastName;

   @Column(name = "EMAIL")
   private String email;

   @Column(name = "PHONENUMBER")
   private String phone;

   @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   private Set<Account> accountSet = new HashSet<>();

}
