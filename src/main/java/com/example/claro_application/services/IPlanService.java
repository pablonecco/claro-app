package com.example.claro_application.services;

import com.example.claro_application.entities.Plan;

import java.util.List;

public interface IPlanService {
    public abstract Plan findById (int id);
    public abstract List<Plan> getAll();
    public abstract Plan insertOrUpdate (Plan plan);
    public abstract boolean delete (int id);
}
