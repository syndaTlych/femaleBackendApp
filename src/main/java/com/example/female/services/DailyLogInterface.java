package com.example.female.services;


import com.example.female.dto.DailyLogDto;
import com.example.female.entities.DailyLog;

import java.time.LocalDate;
import java.util.List;

public interface DailyLogInterface {
    DailyLog addDailyLog(DailyLogDto dailyLogDto);
    DailyLog updateDailyLog(Long id, DailyLogDto dailyLogDto);
    void deleteDailyLog(Long id);
    DailyLog getDailyLogById(Long id);
    List<DailyLog> getAllDailyLogsByUserId(Long userId);
    List<DailyLog> getDailyLogsByDateRange(Long userId, LocalDate startDate, LocalDate endDate);
    DailyLog getDailyLogByDate(Long userId, LocalDate date);
}