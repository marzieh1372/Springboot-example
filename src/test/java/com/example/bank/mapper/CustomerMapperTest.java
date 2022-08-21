package com.example.bank.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.example.bank.model.dto.CustomerRequest;
import com.example.bank.model.entity.Customer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class CustomerMapperTest {
  private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

  @Test
  public void mappToCustomerTest() {
    CustomerRequest request =
        CustomerRequest.builder()
            .userName("maas1219")
            .firstName("Maryam")
            .lastName("Askari")
            .email("maryam@gmail.com")
            .phone("0793359556")
            .build();

    Customer customer = customerMapper.mappToCustomer(request);

    assertEquals(request.getUserName(), customer.getUserName());
    assertEquals(request.getFirstName(), customer.getFirstName());
    assertEquals(request.getLastName(), customer.getLastName());
    assertEquals(request.getEmail(), customer.getEmail());
    assertEquals(request.getPhone(), customer.getPhone());
  }

  @Test
  public void mapToCustomerRequest() {
    Customer customer =
        Customer.builder()
            .userName("Alimali")
            .firstName("Ali")
            .lastName("gholizade")
            .email("ali@gmail.com")
            .phone("091231265432")
            .build();
    CustomerRequest request = customerMapper.mapToCustomerRequest(customer);
    assertEquals(customer.getUserName(), request.getUserName());
    assertEquals(customer.getFirstName(), request.getFirstName());
    assertEquals(customer.getLastName(), request.getLastName());
    assertEquals(customer.getPhone(), request.getPhone());
    assertEquals(customer.getEmail(), request.getEmail());
  }
}
