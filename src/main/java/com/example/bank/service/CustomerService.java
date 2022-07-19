package com.example.bank.service;

import com.example.bank.exceptions.CustomerNotFoundException;
import com.example.bank.model.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomer();
    Customer getCustomerById(Integer id);
    Customer registerCustomer(Customer customer);
    Customer updateCustomer(Integer id, Customer customer) throws CustomerNotFoundException;
    void deleteCustomerById(Integer id);
}
