package com.example.female.services;

import com.example.female.dto.CycleInfoDto;
import com.example.female.entities.CycleInfo;

import java.time.LocalDate;


public interface CycleInterface {
    CycleInfo add(CycleInfo cycleInfo);

    CycleInfoDto getCurrentCycleInfo(Long userId);

    CycleInfoDto updatePeriodStartDate(Long userId, LocalDate newStartDate);
}
