package com.example.female.repository;

import com.example.female.entities.DailyLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {
    List<DailyLog> findByUserIdAndLogDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
    Optional<DailyLog> findByUserIdAndLogDate(Long userId, LocalDate logDate);
}
