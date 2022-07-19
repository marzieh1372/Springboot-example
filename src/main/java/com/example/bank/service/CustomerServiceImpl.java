package com.example.bank.service;

import com.example.bank.exceptions.CustomerNotFoundException;
import com.example.bank.model.entity.Customer;
import com.example.bank.repo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer() {
        log.info("Starting getAllCustomer method {}");
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        log.info("Starting getCustomerById method {" + id + "}");
        Optional<Customer> customerData = customerRepository.findById(id);
        return customerData.orElse(null);
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        log.info("Starting registerCustomer method {" + customer.getUserName() + "}");
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customer) throws CustomerNotFoundException {
        log.info("Starting updateCustomer method {" + id + "}");
        Optional<Customer> customerData = customerRepository.findById(id);
        if (customerData.isPresent()) {
            Customer updatedCustomer = customerData.get();
            updatedCustomer.setFirstName(customer.getFirstName());
            updatedCustomer.setLastName(customer.getLastName());
            //TODO other update
            return customerRepository.save(updatedCustomer);
        } else {
            log.error("Could no find customer!!!!! , Id is wrong : " + id);
            throw new CustomerNotFoundException("Could not find customer with id: " + id);
        }
    }

    @Override
    public void deleteCustomerById(Integer id) {
        log.debug("Starting deleteCustomerById method {" + id + "}");
        //TODO find
        customerRepository.deleteById(id);
    }

}
