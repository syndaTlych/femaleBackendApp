package com.example.female.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Map;

@Entity
@Data
public class DailyLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private LocalDate logDate;

    @ElementCollection
    @CollectionTable(name = "log_symptoms", joinColumns = @JoinColumn(name = "log_id"))
    @MapKeyColumn(name = "symptom")
    @Column(name = "severity")
    private Map<String, Integer> symptoms;

    private String notes;
    private Integer moodRating;
    private Integer flowIntensity;

    @Version
    private Long version;
}

