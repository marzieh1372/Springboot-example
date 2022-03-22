package com.example.bank.controller;

import com.example.bank.exceptions.CustomerNotFoundException;
import com.example.bank.model.entity.Customer;
import com.example.bank.service.CustomerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        LOGGER.info("Starting getAllCustomer method {}");
        try {
            List<Customer> personsList = customerService.getAllCustomer();
            if (personsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        //TODO set validation on dto
        LOGGER.info("Starting addCustomer method {" + customer.getUserName() + "}");
        Customer savedCustomer = customerService.registerCustomer(customer);
        return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);

    }

    @GetMapping("/find-customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long customerId) {
        LOGGER.info("Starting getCustomerById method {" + customerId + "}");
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-customer")
    public ResponseEntity updateCustomer(Customer customer) {
        LOGGER.info("Starting updateCustomer method {" + customer.getId() + "}");
        try {
            customerService.updateCustomer(customer.getId(),customer);
            return new ResponseEntity(HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-customer/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable("customerId") Long customerId) {
        LOGGER.info("Starting deleteCustomer method {" +customerId + "}");
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
