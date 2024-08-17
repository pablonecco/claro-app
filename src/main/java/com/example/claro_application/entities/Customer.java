package com.example.claro_application.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
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

    @Column(name="created_at")
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
