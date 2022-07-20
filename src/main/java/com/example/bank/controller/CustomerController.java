package com.example.bank.controller;

import com.example.bank.api.CustomerAPI;
import com.example.bank.exceptions.CustomerNotFoundException;
import com.example.bank.model.dto.CustomerRequest;
import com.example.bank.model.entity.Customer;
import com.example.bank.service.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CustomerController implements CustomerAPI {

    private ModelMapper modelMapper;

    @Autowired
    private CustomerServiceImpl customerService;

    @Override
    public ResponseEntity<List<Customer>> getAllCustomer() {
        log.info("Starting getAllCustomer method {}");
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

    @Override
    public ResponseEntity<Customer> addCustomer(CustomerRequest customerRequest) {
        //TODO set validation on dto
        log.info("Starting addCustomer method {" + customerRequest.getId() + "}");
        Customer customer= modelMapper.map(customerRequest,Customer.class);
        Customer savedCustomer = customerService.registerCustomer(customer);
        return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Customer> getCustomerById(Integer customerId) {
        log.info("Starting getCustomerById method {" + customerId + "}");
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity updateCustomer(CustomerRequest customerRequest) {
        log.info("Starting updateCustomer method {" + customerRequest.getId() + "}");
        try {
            Customer customer = modelMapper.map(customerRequest, Customer.class);
            customerService.updateCustomer(customer.getId(),customer);
            return new ResponseEntity(HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity deleteCustomer(Integer customerId) {
        log.info("Starting deleteCustomer method {" +customerId + "}");
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
