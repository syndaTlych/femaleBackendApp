package com.example.female.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PeriodUpdateRequest {
    private Long userId;
    private LocalDate newStartDate;
}
