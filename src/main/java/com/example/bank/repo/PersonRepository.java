package com.example.bank.repo;

import java.util.List;
import com.example.bank.model.entity.Customer;
import com.example.bank.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface PersonRepository extends JpaRepository<Person, Integer> {
  // @Query(value = "select * from customer where firstname=?",nativeQuery = true)
  List<Customer> findByFirstName(String firstName);

  Person findByUserName(String userName);
}
