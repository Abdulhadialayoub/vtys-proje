package com.rf.coin_analysis.model;



import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Predictions {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String coin;

    private String prediction;

    private String description;

    private Double price;

    @Column(name = "positive_count")
    private int positiveCount;

    @Column(name = "negative_count")
    private int negativeCount;

    @Column(name = "risk_score")
    private double riskScore;

    private LocalDateTime date;

    @OneToMany(mappedBy = "predictions",cascade = CascadeType.ALL)
    List<FavoriteCoin> favoriteCoins;
}
