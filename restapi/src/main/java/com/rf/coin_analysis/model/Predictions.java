package com.rf.coin_analysis.model;



import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Predictions {

    @Id
    private String coin;

    private String prediction;

    private String description;

    @Column(name = "positive_count")
    private int positiveCount;

    @Column(name = "negative_count")
    private int negativeCount;

    @Column(name = "risk_score")
    private double riskScore;

    private LocalDateTime date;
}
