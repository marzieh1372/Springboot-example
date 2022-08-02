package com.example.bank.mapper;

import com.example.bank.model.dto.CustomerRequest;
import com.example.bank.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  Customer mappToCustomer(CustomerRequest customerRequest);
  CustomerRequest mapToCustomerRequest(Customer customer);
}
