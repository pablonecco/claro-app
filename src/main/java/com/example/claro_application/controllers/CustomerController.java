package com.example.claro_application.controllers;

import com.example.claro_application.entities.Customer;
import com.example.claro_application.services.implementation.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    @Qualifier("customerService")
    private CustomerService customerService;

    @Operation(summary = "Obtiene un cliente a través de la variable ID en la URL.")
    @GetMapping("/{id}")
    public Customer findById (@PathVariable("id") int id) {
        return customerService.findById(id);
    }

    @Operation(summary = "Recupera una lista con todos los clientes.")
    @GetMapping("/all")
    public List<Customer> findAll () {
        return customerService.findAll();
    }

    @Operation(summary = "Crea un nuevo cliente. Enviar objeto con: name, lastName, document y ID de plan.")
    @PostMapping("/create")
    public Customer createCustomer (@RequestBody Customer customer) {
        customer.setNumber(customerService.generateNumber());
        return customerService.insertOrUpdate(customer);
    }

    @Operation(summary = "Actualiza los datos de un cliente. Recibe objeto con los datos nuevos y ID del cliente a modificar.")
    @PutMapping("/update/{id}")
    public Customer updateCustomer (@PathVariable("id") int id, @RequestBody Customer customerUpdateData) {
        Customer customerAux = customerService.findById(id);
        customerAux.setName(customerUpdateData.getName());
        customerAux.setLastName(customerUpdateData.getLastName());
        customerAux.setDocument(customerUpdateData.getDocument());
        return customerService.insertOrUpdate(customerAux);
    }

    @Operation(summary = "Elimina un cliente a través de la variable ID en la URL.")
    @DeleteMapping("/delete/{id}")
    public boolean deleteCustomer (@PathVariable("id") int id) {
        return customerService.delete(id);
    }

}
