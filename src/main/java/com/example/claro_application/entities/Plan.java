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
    private Integer mb;

    @Column(name="minutes")
    private Integer minutes;

    @Column(name="sms")
    private Integer sms;

    //@OneToMany(fetch=FetchType.LAZY, mappedBy = "plan")
    //private List<Customer> customers;

    @Column(name = "price")
    private Integer price;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime cretedAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
