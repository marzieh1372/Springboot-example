package com.example.bank.mapper;

import com.example.bank.model.dto.CustomerRequest;
import com.example.bank.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {

  public abstract Customer mappToCustomer(CustomerRequest customerRequest);
  public abstract CustomerRequest mapToCustomerRequest(Customer customer);
}
