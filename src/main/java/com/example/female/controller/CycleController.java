package com.example.female.controller;

import com.example.female.dto.CycleInfoDto;
import com.example.female.dto.DailyLogDto;
import com.example.female.dto.PeriodUpdateRequest;
import com.example.female.serviceimplement.CycleImplement;
import com.example.female.serviceimplement.DailyLogImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cycle")
@RequiredArgsConstructor
public class CycleController {
    private final CycleImplement cycleService;
    private final DailyLogImplement dailyLogImplement;


    @GetMapping("/{userId}")
    public ResponseEntity<CycleInfoDto> getCycleInfo(@PathVariable Long userId) {
        CycleInfoDto info = cycleService.getCurrentCycleInfo(userId);
        return ResponseEntity.ok(info);
    }

    @PostMapping("/update")
    public ResponseEntity<CycleInfoDto> updatePeriodDate(
            @RequestBody PeriodUpdateRequest request) {
        CycleInfoDto updatedInfo = cycleService.updatePeriodStartDate(
                request.getUserId(),
                request.getNewStartDate());
        return ResponseEntity.ok(updatedInfo);
    }

    @PostMapping("/log")
    public ResponseEntity<Void> addDailyLog(@RequestBody DailyLogDto log) {
        dailyLogImplement.addDailyLog(log);
        return ResponseEntity.ok().build();
    }
}