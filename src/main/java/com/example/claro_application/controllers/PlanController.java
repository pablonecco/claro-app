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
    @Autowired
    @Qualifier("planService")
    private PlanService planService;

    @Operation(summary = "Obtiene un plan a través de la variable ID en la URL.")
    @GetMapping("/{id}")
    public ResponseEntity<Plan> findPlanById (@PathVariable("id") int id) {
        Plan plan = planService.findById(id);
        if (plan==null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(plan);
        }
    }

    @Operation(summary = "Recupera una lista con todos los planes.")
    @GetMapping("/all")
    public ResponseEntity<List<Plan>> findAll () {
        List<Plan> plans = planService.getAll();
        if (plans == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(plans);
        }
    }

    @Operation(summary = "Crea un nuevo plan. Enviar objeto con: name, mb, minutes, price y sms.")
    @PostMapping("/create")
    public ResponseEntity<Plan> createPlan (@Valid @RequestBody CreatePlanDTO planDTO) {

        Plan plan = Plan.builder()
                .price(planDTO.getPrice())
                .mb(planDTO.getMb())
                .sms(planDTO.getSms())
                .minutes(planDTO.getMinutes())
                .name(planDTO.getName())
                .build();

        planService.insertOrUpdate(plan);

        return ResponseEntity.status(HttpStatus.CREATED).body(plan);
    }

    @Operation(summary = "Actualiza las caracteristicas del plan. Recibe objeto con los datos nuevos y ID del plan a modificar.")
    @PutMapping("/update/{id}")
    public ResponseEntity<Plan> updatePlan (@PathVariable("id") int id, @Valid @RequestBody CreatePlanDTO planUpdateDataDTO) {
        Plan planAux = planService.findById(id);
        if (planAux == null) {
            return ResponseEntity.notFound().build();
        }

        planAux.setMb(planUpdateDataDTO.getMb());
        planAux.setSms(planUpdateDataDTO.getSms());
        planAux.setMinutes(planUpdateDataDTO.getMinutes());
        planAux.setPrice(planUpdateDataDTO.getPrice());
        planAux.setName(planUpdateDataDTO.getName());

        planService.insertOrUpdate(planAux);
        return ResponseEntity.ok(planAux);
    }

    @Operation(summary = "Elimina un plan a través de la variable ID en la URL.")
    @DeleteMapping("/delete/{id}")
    public boolean deletePlan (@PathVariable("id") int id) {
        return planService.delete(id);
    }
}
