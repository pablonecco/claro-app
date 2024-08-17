package com.example.claro_application.repositories;

import com.example.claro_application.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("planRepository")
public interface IPlanRepository extends JpaRepository <Plan, Serializable> {
    public abstract Plan findById (int id);
}
