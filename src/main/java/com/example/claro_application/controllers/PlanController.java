package com.example.claro_application.controllers;

import com.example.claro_application.controllers.request.CreatePlanDTO;
import com.example.claro_application.entities.Plan;
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
@RequestMapping("/plan")
@Validated
public class PlanController {
    //Instancia de servicio de Plan
    @Autowired
    @Qualifier("planService")
    private PlanService planService;

    @Operation(summary = "Obtiene un plan a través de la variable ID en la URL.")
    @GetMapping("/{id}")
    public ResponseEntity<Plan> findPlanById (@PathVariable("id") int id) {
        //El servicio trae el Plan a través del ID que llega por URL
        Plan plan = planService.findById(id);
        if (plan==null) {
            //Si el plan no existe devuelve 404 Not Found
            return ResponseEntity.notFound().build();
        } else {
            //Si existe devuelve 200 OK y el objeto traído por ID
            return ResponseEntity.ok(plan);
        }
    }

    @Operation(summary = "Recupera una lista con todos los planes.")
    @GetMapping("/all")
    public ResponseEntity<List<Plan>> findAll () {
        //El servicio trae una lista con todos los planes
        List<Plan> plans = planService.getAll();
        if (plans.isEmpty()) {
            //Si la lista está vacía devuelve un 204 No Content
            return ResponseEntity.noContent().build();
        } else {
            //Si la lista tiene datos devuelve 200 OK y la lista
            return ResponseEntity.ok(plans);
        }
    }

    @Operation(summary = "Crea un nuevo plan. Enviar objeto con: name, mb, minutes, price y sms.")
    @PostMapping("/create")
    public ResponseEntity<Plan> createPlan (@Valid @RequestBody CreatePlanDTO planDTO) {

        //Se crea una instancia de plan con toda la información de CreatePlanDTO
        Plan plan = Plan.builder()
                .price(planDTO.getPrice())
                .mb(planDTO.getMb())
                .sms(planDTO.getSms())
                .minutes(planDTO.getMinutes())
                .name(planDTO.getName())
                .build();

        //Se guarda el plan creado en la bbdd
        planService.insertOrUpdate(plan);

        //Devuelve un 201 Created y el objeto creado
        return ResponseEntity.status(HttpStatus.CREATED).body(plan);
    }

    @Operation(summary = "Actualiza las caracteristicas del plan. Recibe objeto con los datos nuevos y ID del plan a modificar.")
    @PutMapping("/update/{id}")
    public ResponseEntity<Plan> updatePlan (@PathVariable("id") int id, @Valid @RequestBody CreatePlanDTO planUpdateDataDTO) {

        //El servicio trae el plan a través del ID que llega por URL
        Plan planAux = planService.findById(id);

        if (planAux == null) {
            //Si el plan no existe devuelve 404 Not Found
            return ResponseEntity.notFound().build();
        }

        //Se actualiza toda la información del plan por la que llega a través del CreatePlanDTO
        planAux.setMb(planUpdateDataDTO.getMb());
        planAux.setSms(planUpdateDataDTO.getSms());
        planAux.setMinutes(planUpdateDataDTO.getMinutes());
        planAux.setPrice(planUpdateDataDTO.getPrice());
        planAux.setName(planUpdateDataDTO.getName());

        //Se guarda la nueva información en la bbdd
        planService.insertOrUpdate(planAux);

        //Devuelve un 200 OK y el plan actualizado
        return ResponseEntity.ok(planAux);
    }

    @Operation(summary = "Elimina un plan a través de la variable ID en la URL.")
    @DeleteMapping("/delete/{id}")
    public boolean deletePlan (@PathVariable("id") int id) {
        return planService.delete(id);
    }
}
