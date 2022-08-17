/*
package com.example.bank.mapper;

import com.example.bank.model.dto.CustomerRequest;
import com.example.bank.model.entity.Customer;

public class CustomerMapperImpl implements CustomerMapper{

  @Override
  public Customer mappToCustomer(CustomerRequest customerRequest) {
    if (customerRequest == null){
      return  null;
    }
    Customer customer = new Customer();
    customer.setUserName(customerRequest.getUserName());
    customer.setFirstName(customerRequest.getFirstName());
    customer.setLastName(customerRequest.getLastName());
    customer.setEmail(customerRequest.getEmail());
    customer.setPhone(customerRequest.getPhone());
    return customer;
  }

  @Override
  public CustomerRequest mapToCustomerRequest(Customer customer) {
    if (customer == null){
      return null;
    }
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.setUserName(customer.getUserName());
    customerRequest.setFirstName(customer.getFirstName());
    customerRequest.setLastName(customer.getLastName());
    customerRequest.setEmail(customer.getEmail());
    customerRequest.setPhone(customer.getPhone());
    return customerRequest;
  }
}
*/
