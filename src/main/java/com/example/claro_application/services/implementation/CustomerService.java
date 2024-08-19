package com.example.claro_application.services.implementation;

import com.example.claro_application.entities.Customer;
import com.example.claro_application.entities.Plan;
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
            //Se instancia la clase Random
            Random rand = new Random();
            //Se crea un número aleatorio de 8 dígitos
            int random = rand.nextInt(90000000)+10000000;
            int prefix = 11;
            //Se convierte el numero aleatorio y el prefix a String
            String value1 = String.valueOf(prefix);
            String value2 = String.valueOf(random);
            //Se concatenan los dos String
            String numberString = value1 + value2;
            //Se lo vuelve a pasar a int
            number = Integer.parseInt(numberString);

            //El servicio llama a la lista con todos los clientes
            List<Customer> customers = this.findAll();

            //Se verifica en la lista que no exista un cliente con el mismo número
            for (Customer c : customers) {
                if (c.getNumber()==number) {
                    exists=true;
                    break;
                }
            }
        } while (exists); //Si existe vuelve a hacer el Do While, si queda en false sale

        //Devuelve el número de 10 dígitos 11XXXXXXXX
        return number;
    }

    public List<Customer> findByPlan (Plan plan) {
        return customerRepository.findByPlan(plan);
    }
}
