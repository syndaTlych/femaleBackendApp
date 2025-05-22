package com.example.female.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class PregnancyTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPregnancy;
    @Column(nullable = false)
    private LocalDate conceptionDate;
    private int currentWeek; // Semaine actuelle de grossesse

    private String notes; // Notes personnelles
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


}
