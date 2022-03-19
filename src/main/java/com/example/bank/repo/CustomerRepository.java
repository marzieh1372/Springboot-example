package com.example.bank.repo;

import com.example.bank.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
   // @Query(value = "select * from customer where firstname=?",nativeQuery = true)
    List<Customer> findByFirstName(String firstName);
}
