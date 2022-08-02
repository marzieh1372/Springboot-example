package com.example.bank.service;

import com.example.bank.exceptions.CustomerNotFoundException;
import com.example.bank.mapper.CustomerMapper;
import com.example.bank.model.dto.CustomerRequest;
import com.example.bank.model.entity.Customer;
import com.example.bank.repo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired private CustomerMapper mapper;

  @Autowired private CustomerRepository customerRepository;

  @Override
  public List<Customer> getAllCustomer() {
    log.info("Starting getAllCustomer method {}");
    return customerRepository.findAll();
  }

  @Override
  public CustomerRequest getCustomerById(Integer id) {
    log.info("Starting getCustomerById method {" + id + "}");
    Optional<Customer> customerData = customerRepository.findById(id);
    if (customerData.isPresent()) {
      Customer customer = customerData.get();
      return mapper.mapToCustomerRequest(customer);
    } else {
      log.warn("Can not find customer by id {}", id);
      throw new CustomerNotFoundException("Customer not found by id: " + id);
    }
  }

  @Override
  public void registerCustomer(CustomerRequest customerRequest) {
    // TODO set validation on dto
    log.info("Starting registerCustomer method {" + customerRequest.getUserName() + "}");
    Customer customer = mapper.mappToCustomer(customerRequest);
    customerRepository.save(customer);
  }

  @Override
  public void updateCustomer(CustomerRequest customerRequest) throws CustomerNotFoundException {
    log.info("Starting updateCustomer method {" + customerRequest.getUserName() + "}");
    Customer customerData = customerRepository.findByUserName(customerRequest.getUserName());
    if (customerData != null) {
      customerData = mapper.mappToCustomer(customerRequest);
      // TODO other update
      customerRepository.save(customerData);
    } else {
      log.error(
          "Could no find customer!!!!! , username is wrong : " + customerRequest.getUserName());
      throw new CustomerNotFoundException(
          "Could not find customer with userName: " + customerRequest.getUserName());
    }
  }

  @Override
  public void deleteCustomerById(Integer id) throws CustomerNotFoundException {
    log.debug("Starting deleteCustomerById method {" + id + "}");
    Optional<Customer> customerInfo = customerRepository.findById(id);
    if (customerInfo.isPresent()) {
      customerRepository.deleteById(id);
    } else {
      throw new CustomerNotFoundException("Could not find customer with id: " + id);
    }
  }
}
