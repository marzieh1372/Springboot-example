package com.example.bank.service;

import com.example.bank.exceptions.CustomerNotFoundException;
import com.example.bank.model.entity.Customer;
import com.example.bank.repo.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer() {
        LOGGER.info("Starting getAllCustomer method {}");
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        LOGGER.info("Starting getCustomerById method {" + id + "}");
        Optional<Customer> customerData = customerRepository.findById(id);
        return customerData.orElse(null);
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        LOGGER.info("Starting registerCustomer method {" + customer.getUserName() + "}");
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws CustomerNotFoundException {
        LOGGER.info("Starting updateCustomer method {" + id + "}");
        Optional<Customer> customerData = customerRepository.findById(id);
        if (customerData.isPresent()) {
            Customer updatedCustomer = customerData.get();
            updatedCustomer.setFirstName(customer.getFirstName());
            updatedCustomer.setLastName(customer.getLastName());
            //TODO other update
            return customerRepository.save(updatedCustomer);
        } else {
            LOGGER.error("Could no find customer!!!!! , Id is wrong : " + id);
            throw new CustomerNotFoundException("Could not find customer with id: " + id);
        }
    }

    @Override
    public void deleteCustomerById(Long id) {
        LOGGER.debug("Starting deleteCustomerById method {" + id + "}");
        //TODO find
        customerRepository.deleteById(id);
    }

}
