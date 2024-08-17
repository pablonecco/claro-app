package com.example.claro_application.services.implementation;

import com.example.claro_application.entities.Plan;
import com.example.claro_application.repositories.IPlanRepository;
import com.example.claro_application.services.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("planService")
public class PlanService implements IPlanService {
    @Autowired
    @Qualifier("planRepository")
    IPlanRepository planRepository;

    public Plan findById (int id) {
        return planRepository.findById(id);
    }

    public List<Plan> getAll() {
        return planRepository.findAll();
    }

    public Plan insertOrUpdate (Plan plan) {
        return planRepository.save(plan);
    }

    public boolean delete (int id) {
        try {
            planRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
