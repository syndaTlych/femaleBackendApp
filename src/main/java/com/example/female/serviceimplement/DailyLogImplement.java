package com.example.female.serviceimplement;

import com.example.female.dto.DailyLogDto;
import com.example.female.entities.DailyLog;
import com.example.female.repository.DailyLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class DailyLogImplement {
    private final DailyLogRepository dailyLogRepository;

    @Transactional
    public void addDailyLog(DailyLogDto logDto) {

        DailyLog existingLog = dailyLogRepository.findByUserIdAndLogDate(
                logDto.getUserId(),
                logDto.getLogDate()
        ).orElse(new DailyLog());

        existingLog.setUserId(logDto.getUserId());
        existingLog.setLogDate(logDto.getLogDate());
        existingLog.setSymptoms(logDto.getSymptoms());
        existingLog.setNotes(logDto.getNotes());
        existingLog.setMoodRating(logDto.getMoodRating());
        existingLog.setFlowIntensity(logDto.getFlowIntensity());

        dailyLogRepository.save(existingLog);
    }
}

