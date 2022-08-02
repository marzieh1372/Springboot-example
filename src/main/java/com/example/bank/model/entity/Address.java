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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ADDRESS")
@JsonDeserialize(builder = Address.AddressBuilder.class)
public class Address {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO, generator = "address_seq_gen")
  @SequenceGenerator(name="address_seq_gen", sequenceName="ADDRESS_SEQ")
  private Integer id;

  @Column(name = "StreetName", nullable = false)
  private String streetName;

  @Column(name = "houseNumber", nullable = false)
  private Integer houseNumber;

  @Column(name = "state")
  private String state;

  @Column(name = "zipCode", nullable = false)
  private String zipCode;


  @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Person> personSet = new HashSet<>();

  @OneToOne(mappedBy = "address")
  private Bank bank;

  public Address(Integer id, String streetName, Integer houseNumber, String state, String zipCode) {
    this.id = id;
    this.streetName = streetName;
    this.houseNumber = houseNumber;
    this.state = state;
    this.zipCode = zipCode;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonPOJOBuilder(withPrefix = "")
  public static class AddressBuilder{}
}
