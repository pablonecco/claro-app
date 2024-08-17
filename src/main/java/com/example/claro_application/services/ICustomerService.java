package com.example.claro_application.services;

import com.example.claro_application.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICustomerService {
    public abstract Customer findById(int id);
    public abstract List<Customer> findAll();
    public abstract Customer insertOrUpdate (Customer customer);
    public abstract boolean delete (int id);
    public abstract int generateNumber();

}
