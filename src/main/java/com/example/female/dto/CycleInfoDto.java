package com.example.female.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CycleInfoDto {
    private String currentPhase;
    private int dayNumber;
    private int daysUntilOvulation;
    private LocalDate lastPeriodDate;
    private LocalDate nextPeriodDate;
    private String fertilityMessage;
}
