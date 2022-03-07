package com.example.bank.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

   /* @Embedded
    private Address address;*/

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID")
   private Long id;

   @Column(name = "USER-NAME", nullable = false)
   private String userName;

   @Column(name = "FIRST-NAME")
   private String firstName;

   @Column(name = "LAST-NAME")
   private String lastName;

   @Column(name = "EMAIL")
   private String email;

   @Column(name = "PHONE-NUMBER")
   private String phone;

   /*@OneToMany
   @JoinColumn(name = "address_fk")
   private List<Address> addressList;*/

   public Customer() {
   }

   public Customer(Long id, String userName, String firstName, String lastName, String email, String phone) {
      this.id = id;
      this.userName = userName;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.phone = phone;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }
}
