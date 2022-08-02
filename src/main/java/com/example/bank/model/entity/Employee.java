package com.example.bank.model.entity;


import com.example.bank.model.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity(name = "employee")
@Builder
@AllArgsConstructor
@DiscriminatorValue("employee")
@JsonDeserialize(builder = Employee.EmployeeBuilder.class)
public class Employee extends Person{

  @Column(name = "EMPLOYEEID", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer employeeId;

  @Column(name = "POSITION", nullable = false)
  private String position;

  @Column(name = "ROLE", nullable = false)
  private String role;

  public Employee(){
    super();
  }

  public Employee(
      Integer employeeId,
      Integer id,
      String userName,
      String firstName,
      String lastName,
      String email,
      String phone,
      String nationalId,
      Gender gender,
      String position,
      String role,
      LocalDate birthday){
    super(id, userName, firstName, lastName, email, phone, nationalId, gender, birthday);
    this.employeeId = employeeId;
    this.position = position;
    this.role = role;
  }


  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonPOJOBuilder(withPrefix = "")
  public static class EmployeeBuilder{}
}
