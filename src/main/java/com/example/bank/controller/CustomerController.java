package com.example.bank.controller;

import com.example.bank.api.CustomerAPI;
import com.example.bank.exceptions.CustomerNotFoundException;
import com.example.bank.model.dto.CustomerRequest;
import com.example.bank.model.entity.Customer;
import com.example.bank.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CustomerController implements CustomerAPI {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  public ResponseEntity<List<Customer>> getAllCustomer() {
    log.info("Starting getAllCustomer method {}");
    try {
      List<Customer> customerList = customerService.getAllCustomer();
      if (customerList.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(customerList, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public ResponseEntity addCustomer(CustomerRequest customerRequest) {
    // TODO set validation on dto
    log.info("Starting addCustomer method {" + customerRequest.getUserName() + "}");
    customerService.registerCustomer(customerRequest);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<CustomerRequest> getCustomerById(Integer customerId) {
    log.info("Starting getCustomerById method {" + customerId + "}");
    try {
      return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    } catch (CustomerNotFoundException e) {
      log.warn(e.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity updateCustomer(CustomerRequest customerRequest) {
    log.info("Starting updateCustomer method {" + customerRequest.getUserName() + "}");
    try {
      customerService.updateCustomer(customerRequest);
      return new ResponseEntity(HttpStatus.OK);
    } catch (CustomerNotFoundException e) {
      log.error(e.getMessage());
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity deleteCustomer(Integer customerId) {
    log.info("Starting deleteCustomer method {" + customerId + "}");
    try {
      customerService.deleteCustomerById(customerId);
      return new ResponseEntity(HttpStatus.OK);
    } catch (CustomerNotFoundException e) {
      log.warn(e.getMessage());
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }
}
