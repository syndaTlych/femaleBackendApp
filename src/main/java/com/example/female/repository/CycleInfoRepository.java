package com.example.female.repository;

import com.example.female.entities.CycleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CycleInfoRepository  extends JpaRepository<CycleInfo, Long> {
    Optional <CycleInfo> findByUserId(Long userId);

}
