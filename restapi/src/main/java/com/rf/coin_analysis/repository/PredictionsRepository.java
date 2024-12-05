package com.rf.coin_analysis.repository;

import com.rf.coin_analysis.model.Predictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PredictionsRepository extends JpaRepository<Predictions,Long> {
    @Query("SELECT p FROM Predictions p ORDER BY p.riskScore ASC ")
    List<Predictions> orderByRiskScoreAsc();

}
