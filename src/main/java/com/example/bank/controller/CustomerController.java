package com.example.bank.controller;

import com.example.bank.model.entity.Customer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @PutMapping("/update-customer")
    public void updateCustomer(Customer customer){}

    @PostMapping("/add-customer")
    public void addCustomer(@RequestBody Customer customer){}

    @GetMapping("/{customerId}")
    public void getCustomerById(@PathVariable("customerId") Long customerId){}

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId){}
}
