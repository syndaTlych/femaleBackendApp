package com.example.female.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class DailyLogDto {
    private Long userId;
    private LocalDate logDate;
    private Map<String, Integer> symptoms;
    private String notes;
    private Integer moodRating;
    private Integer flowIntensity;
}
