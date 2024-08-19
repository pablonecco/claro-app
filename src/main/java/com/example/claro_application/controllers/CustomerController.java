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
    //Instancia de servicio de Customer
    @Autowired
    @Qualifier("customerService")
    private CustomerService customerService;

    //Instancia se servicio de Plan
    @Autowired
    @Qualifier ("planService")
    private PlanService planService;

    @Operation(summary = "Obtiene un cliente a través de la variable ID en la URL.")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById (@PathVariable("id") int id) {
        //Llama al servicio para traer un Customer por ID que pasa la URL
        Customer customer = customerService.findById(id);

        if (customer==null) {
            //Si no existe ningún Customer con ese ID devuelve un 404 Not Found
            return ResponseEntity.notFound().build();
        } else {
            //Si existe devuelve el objeto y un 200 OK
            return ResponseEntity.ok(customer);
        }
    }

    @Operation(summary = "Recupera una lista con todos los clientes.")
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAll () {
        //El servicio trae una lista con todos los Customers
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()) {
            //Si la lista está vacía devuelve un 204 No Content
            return ResponseEntity.noContent().build();
        } else {
            //Si la lista tiene datos devuelve la lista y un 200 OK
            return ResponseEntity.ok(customers);
        }
    }

    @Operation(summary = "Crea un nuevo cliente. Enviar objeto con: name, lastName, document y ID de plan.")
    @PostMapping("/create/{idPlan}")
    public ResponseEntity<Customer> createCustomer (@PathVariable("idPlan") int idPlan, @Valid @RequestBody CreateCustomerDTO customerDTO) {
        //El servicio trae el plan a través del ID que pasa por URL
        Plan plan = planService.findById(idPlan);

        if (plan == null) {
            //Si el plan no existe devuelve un 404 Not Found
            return ResponseEntity.notFound().build();
        }

        //Se crea una instancia de Customer con la información que llega desde el CreateCustomerDTO
        Customer customer = Customer.builder()
                .name(customerDTO.getName())
                .lastName(customerDTO.getLastName())
                .document(customerDTO.getDocument())
                .plan(plan)
                .number(customerService.generateNumber())
                .build();

        //System.out.println(customer.getNumber());

        //Se guarda el Customer en la bbdd
        customerService.insertOrUpdate(customer);

        //Devuelve un 201 Created y el objeto creado
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @Operation(summary = "Actualiza los datos de un cliente. Recibe objeto con los datos nuevos y ID del cliente a modificar.")
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer (@PathVariable("id") int id, @Valid @RequestBody CreateCustomerDTO customerUpdateDataDTO) {
        //Se llama al Customer que se va a editar
        Customer customerAux = customerService.findById(id);

        if (customerAux==null) {
            //Si el Customer a modificar no existe devuelve un 404 Not Found
            return ResponseEntity.notFound().build();
        }

        //Se actualiza la información del Customer con la que llega a de CreateCustomerDTO
        customerAux.setLastName(customerUpdateDataDTO.getLastName());
        customerAux.setName(customerUpdateDataDTO.getName());
        customerAux.setDocument(customerUpdateDataDTO.getDocument());

        //Se guarda la info en la bbdd
        customerService.insertOrUpdate(customerAux);

        //Devuelve un 200 OK y el objeto actualizado
        return ResponseEntity.ok(customerAux);
    }

    @Operation(summary = "Elimina un cliente a través de la variable ID en la URL.")
    @DeleteMapping("/delete/{id}")
    public boolean deleteCustomer (@PathVariable("id") int id) {
        return customerService.delete(id);
    }

    @Operation(summary = "Devuelve una lista de clientes con un determinado plan que llega por ID en la URL")
    @GetMapping("/plan/{idPlan}")
    public ResponseEntity<List<Customer>> findByPlan (@PathVariable("idPlan") int idPlan) {
        //El servicio llama al plan a través del ID que llega por URL
        Plan plan = planService.findById(idPlan);

        //El servicio trae una lista con ese plan
        List<Customer> customers = customerService.findByPlan(plan);

        if (plan == null || customers.isEmpty()) {
            //Si el plan no existe, o no hay Customers con ese plan, devuelve un 204 No Content
            return ResponseEntity.noContent().build();
        }

        //Devuelve 200 OK y el la lista de Customers con ese plan
        return ResponseEntity.ok(customers);
    }

}
