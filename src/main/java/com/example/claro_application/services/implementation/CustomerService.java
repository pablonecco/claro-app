package com.example.claro_application.services.implementation;

import com.example.claro_application.entities.Customer;
import com.example.claro_application.repositories.ICustomerRepository;
import com.example.claro_application.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("customerService")
public class CustomerService implements ICustomerService {
    @Autowired
    @Qualifier("customerRepository")
    private ICustomerRepository customerRepository;

    public Customer findById (int id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAll () {
        return customerRepository.findAll();
    }

    public Customer insertOrUpdate (Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean delete (int id) {
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int generateNumber () {
        boolean exists = false;
        int number;
        do {
            Random rand = new Random();
            int random = rand.nextInt(90000000)+10000000;
            int prefix = 11;
            String value1 = String.valueOf(prefix);
            String value2 = String.valueOf(random);
            String numberString = value1 + value2;
            number = Integer.parseInt(numberString);

            List<Customer> customers = this.findAll();

            for (Customer c : customers) {
                if (c.getNumber()==number) {
                    exists=true;
                    break;
                }
            }
        } while (exists);

        return number;
    }
}
