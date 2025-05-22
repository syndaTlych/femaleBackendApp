package com.example.female.repository;

import com.example.female.entities.PregnancyTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PregnancyTrackerRepo extends JpaRepository<PregnancyTracker, Long> {
    Optional<PregnancyTracker> findByUserId(Long userId);
}
