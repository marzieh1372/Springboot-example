package com.example.bank.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

   /* @Embedded
    private Address address;*/

   @OneToMany
   @JoinColumn(name = "address_fk")
   private List<Address> addressList;
}
