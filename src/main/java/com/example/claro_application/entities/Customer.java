package com.example.claro_application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
@Table (name="customer")
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column(name="document")
    private int document;

    @Column(name="number")
    private int number;

    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp //Fecha y hora de creación
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp //Fecha y hora de actualización
    private LocalDateTime updatedAt;

    @ManyToOne //Relacion muchos a uno con Plan
    @JoinColumn(name="plan_id")
    private Plan plan;
}
