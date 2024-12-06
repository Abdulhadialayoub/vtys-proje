package com.rf.coin_analysis.dto;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoinDto {
    private Long id;
    private String coin;

    private String prediction;

    private String description;

    @Column(name = "positive_count")
    private int positiveCount;

    @Column(name = "negative_count")
    private int negativeCount;

    @Column(name = "risk_score")
    private double riskScore;

    private double price;

    private LocalDateTime date;
}
