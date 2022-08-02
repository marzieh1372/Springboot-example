package com.example.bank.model.entity;

import com.example.bank.model.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
public abstract class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "USERNAME", nullable = false, unique = true)
  @NotBlank(message = "ahay ahay username!!!!!")
  @Size(min = 4, max = 12)
  private String userName;

  @Column(name = "FIRSTNAME")
  @NotNull(message = "heyyyyyyyyyyyyyyyy")
  private String firstName;

  @Column(name = "LASTNAME")
  private String lastName;

  @Column(name = "EMAIL")
  // @Email(message = "Ahay ahay email !!!")
  private String email;

  @Column(name = "PHONENUMBER")
  private String phone;

  @Column(name = "NATIONALID")
  private String nationalID;

  @Column(name = "GENDER")
  private Gender gender;

  @Column(name = "BIRTHDAY")
  private LocalDate birthday;

  public Person(Integer id, String userName, String firstName, String lastName, String email,
                String phone, String nationalID, Gender gender, LocalDate birthday) {
    this.id = id;
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.nationalID = nationalID;
    this.gender = gender;
    this.birthday = birthday;
  }

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "addressID", nullable = false)
  private Address address;
}
