package com.example.claro_application.repositories;

import com.example.claro_application.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("customerRepository")
public interface ICustomerRepository extends JpaRepository<Customer, Serializable> {
    public abstract Customer findById (int id);
}
