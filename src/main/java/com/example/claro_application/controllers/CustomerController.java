package com.example.claro_application.controllers;

import com.example.claro_application.controllers.request.CreateCustomerDTO;
import com.example.claro_application.entities.Customer;
import com.example.claro_application.entities.Plan;
import com.example.claro_application.services.implementation.CustomerService;
import com.example.claro_application.services.implementation.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {
    @Autowired
    @Qualifier("customerService")
    private CustomerService customerService;

    @Autowired
    @Qualifier ("planService")
    private PlanService planService;

    @Operation(summary = "Obtiene un cliente a través de la variable ID en la URL.")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById (@PathVariable("id") int id) {
        Customer customer = customerService.findById(id);
        if (customer==null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(customer);
        }
    }

    @Operation(summary = "Recupera una lista con todos los clientes.")
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAll () {
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(customers);
        }
    }

    @Operation(summary = "Crea un nuevo cliente. Enviar objeto con: name, lastName, document y ID de plan.")
    @PostMapping("/create/{idPlan}")
    public ResponseEntity<Customer> createCustomer (@PathVariable("idPlan") int idPlan, @Valid @RequestBody CreateCustomerDTO customerDTO) {
        Plan plan = planService.findById(idPlan);

        if (plan == null) {
            return ResponseEntity.notFound().build();
        }

        Customer customer = Customer.builder()
                .name(customerDTO.getName())
                .lastName(customerDTO.getLastName())
                .document(customerDTO.getDocument())
                .plan(plan)
                .number(customerService.generateNumber())
                .build();

        customerService.insertOrUpdate(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @Operation(summary = "Actualiza los datos de un cliente. Recibe objeto con los datos nuevos y ID del cliente a modificar.")
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer (@PathVariable("id") int id, @Valid @RequestBody CreateCustomerDTO customerUpdateDataDTO) {
        Customer customerAux = customerService.findById(id);

        if (customerAux==null) {
            return ResponseEntity.notFound().build();
        }

        customerAux.setLastName(customerUpdateDataDTO.getLastName());
        customerAux.setName(customerUpdateDataDTO.getName());
        customerAux.setDocument(customerUpdateDataDTO.getDocument());

        customerService.insertOrUpdate(customerAux);

        return ResponseEntity.ok(customerAux);
    }

    @Operation(summary = "Elimina un cliente a través de la variable ID en la URL.")
    @DeleteMapping("/delete/{id}")
    public boolean deleteCustomer (@PathVariable("id") int id) {
        return customerService.delete(id);
    }

}
