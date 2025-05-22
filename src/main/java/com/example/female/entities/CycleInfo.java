package com.example.female.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class CycleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String currentPhase;
    private int dayNumber;
    private int daysUntilOvulation;
    private LocalDate lastPeriodDate;
    private LocalDate nextPeriodDate;
    private int averageCycleLength;
    private int averagePeriodLength;

    @Version
    private Long version;
}