package com.example.bank.service;

import com.example.bank.exceptions.CustomerNotFoundException;
import com.example.bank.model.dto.CustomerRequest;
import com.example.bank.model.entity.Customer;

import java.util.List;

public interface CustomerService {

  List<Customer> getAllCustomer();

  CustomerRequest getCustomerById(Integer id) throws CustomerNotFoundException;

  void registerCustomer(CustomerRequest customerRequest);

  void updateCustomer(CustomerRequest customerRequest) throws CustomerNotFoundException;

  void deleteCustomerById(Integer id) throws CustomerNotFoundException;
}
