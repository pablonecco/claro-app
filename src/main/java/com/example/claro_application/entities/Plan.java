package com.example.claro_application.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name="plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="mb")
    private int mb;

    @Column(name="minutes")
    private int minutes;

    @Column(name="sms")
    private int sms;

    @Column(name = "price")
    private int price;

    @CreationTimestamp //Fecha y hora de creación
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime cretedAt;

    @UpdateTimestamp //Fecha y hora de actualización
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
