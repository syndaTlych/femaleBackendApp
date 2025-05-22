package com.example.female.serviceimplement;

import com.example.female.dto.CycleInfoDto;
import com.example.female.entities.CycleInfo;
import com.example.female.repository.CycleInfoRepository;
import com.example.female.services.CycleInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
@Transactional
public class CycleImplement implements CycleInterface {
    private final CycleInfoRepository cycleInfoRepository;

    private static final int DEFAULT_CYCLE_LENGTH = 28;
    private static final int DEFAULT_PERIOD_LENGTH = 5;

    @Override
    public CycleInfo add(CycleInfo cycleInfo) {
        try {
            return cycleInfoRepository.save(cycleInfo);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save cycle info: " + e.getMessage());
        }
    }

    @Override
    public CycleInfoDto getCurrentCycleInfo(Long userId) {
        CycleInfo cycleInfo = cycleInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cycle info not found for user ID: " + userId));

        try {
            return calculateCycleInfo(cycleInfo);
        } catch (Exception e) {
            throw new RuntimeException("Error calculating cycle info: " + e.getMessage());
        }
    }

    @Override
    public CycleInfoDto updatePeriodStartDate(Long userId, LocalDate newStartDate) {
        // Validate date
        if (newStartDate.isAfter(LocalDate.now())) {
            throw new RuntimeException("Period start date cannot be in the future");
        }

        CycleInfo cycleInfo = cycleInfoRepository.findByUserId(userId)
                .orElseGet(() -> {
                    try {
                        return createNewCycleInfo(userId);
                    } catch (Exception e) {
                        throw new RuntimeException("Error creating new cycle info: " + e.getMessage());
                    }
                });

        try {
            cycleInfo.setLastPeriodDate(newStartDate);
            cycleInfo.setNextPeriodDate(calculateNextPeriodDate(newStartDate, cycleInfo.getAverageCycleLength()));

            CycleInfo savedInfo = cycleInfoRepository.save(cycleInfo);
            return calculateCycleInfo(savedInfo);
        } catch (Exception e) {
            throw new RuntimeException("Error updating period date: " + e.getMessage());
        }
    }

    // Add the missing helper methods
    private CycleInfoDto calculateCycleInfo(CycleInfo cycleInfo) {
        LocalDate today = LocalDate.now();
        LocalDate lastPeriod = cycleInfo.getLastPeriodDate();

        int dayNumber = Period.between(lastPeriod, today).getDays() + 1;
        String currentPhase = determinePhase(dayNumber, cycleInfo.getAverageCycleLength());
        int daysUntilOvulation = calculateDaysUntilOvulation(dayNumber, cycleInfo.getAverageCycleLength());

        CycleInfoDto dto = new CycleInfoDto();
        dto.setCurrentPhase(currentPhase);
        dto.setDayNumber(dayNumber);
        dto.setDaysUntilOvulation(daysUntilOvulation);
        dto.setLastPeriodDate(cycleInfo.getLastPeriodDate());
        dto.setNextPeriodDate(cycleInfo.getNextPeriodDate());
        dto.setFertilityMessage(getFertilityMessage(currentPhase));

        return dto;
    }

    private String determinePhase(int dayNumber, int cycleLength) {
        if (dayNumber <= 5) return "Period";
        if (dayNumber <= cycleLength - 14) return "Follicular";
        if (dayNumber <= cycleLength - 12) return "Ovulation";
        return "Luteal";
    }

    private int calculateDaysUntilOvulation(int dayNumber, int cycleLength) {
        int ovulationDay = cycleLength - 14;
        return Math.max(0, ovulationDay - dayNumber);
    }

    private String getFertilityMessage(String phase) {
        return switch (phase) {
            case "Ovulation" -> "High chance today";
            case "Follicular" -> "Fertility rising";
            case "Luteal" -> "Fertility low";
            default -> "Not fertile";
        };
    }

    private LocalDate calculateNextPeriodDate(LocalDate lastPeriod, int cycleLength) {
        return lastPeriod.plusDays(cycleLength);
    }

    private CycleInfo createNewCycleInfo(Long userId) {
        CycleInfo newInfo = new CycleInfo();
        newInfo.setUserId(userId);
        newInfo.setLastPeriodDate(LocalDate.now());
        newInfo.setAverageCycleLength(DEFAULT_CYCLE_LENGTH);
        newInfo.setAveragePeriodLength(DEFAULT_PERIOD_LENGTH);
        newInfo.setNextPeriodDate(calculateNextPeriodDate(
                newInfo.getLastPeriodDate(),
                newInfo.getAverageCycleLength()
        ));
        return newInfo;
    }
}