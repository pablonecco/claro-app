package com.example.claro_application.controllers;

import com.example.claro_application.controllers.request.CreatePlanDTO;
import com.example.claro_application.entities.Plan;
import com.example.claro_application.services.implementation.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    @Qualifier("planService")
    private PlanService planService;

    @Operation(summary = "Obtiene un plan a través de la variable ID en la URL.")
    @GetMapping("/{id}")
    public Plan findPlanById (@PathVariable("id") int id) {
        return planService.findById(id);
    }

    @Operation(summary = "Recupera una lista con todos los planes.")
    @GetMapping("/all")
    public List<Plan> findAll () {
        return planService.getAll();
    }

    @Operation(summary = "Crea un nuevo plan. Enviar objeto con: name, mb, minutes, price y sms.")
    @PostMapping("/create")
    public Plan createPlan (@RequestBody CreatePlanDTO planDTO) {

        Plan plan = Plan.builder()
                .price(planDTO.getPrice())
                .mb(planDTO.getMb())
                .sms(planDTO.getSms())
                .minutes(planDTO.getMinutes())
                .name(planDTO.getName())
                .build();

        return planService.insertOrUpdate(plan);
    }

    @Operation(summary = "Actualiza las caracteristicas del plan. Recibe objeto con los datos nuevos y ID del plan a modificar.")
    @PutMapping("/update/{id}")
    public Plan updatePlan (@PathVariable("id") int id, @RequestBody CreatePlanDTO planUpdateDataDTO) {
        Plan planAux = planService.findById(id);
        if (planUpdateDataDTO.getMb() != null) {
            planAux.setMb(planUpdateDataDTO.getMb());
        }
        if (planUpdateDataDTO.getSms() != null) {
            planAux.setSms(planUpdateDataDTO.getSms());
        }
        if (planUpdateDataDTO.getMinutes() != null) {
            planAux.setMinutes(planUpdateDataDTO.getMinutes());
        }
        if (planUpdateDataDTO.getPrice() != null) {
            planAux.setPrice(planUpdateDataDTO.getPrice());
        }
        if (planUpdateDataDTO.getName() != null) {
            planAux.setName(planUpdateDataDTO.getName());
        }
        return planService.insertOrUpdate(planAux);
    }

    @Operation(summary = "Elimina un plan a través de la variable ID en la URL.")
    @DeleteMapping("/delete/{id}")
    public boolean deletePlan (@PathVariable("id") int id) {
        return planService.delete(id);
    }
}
