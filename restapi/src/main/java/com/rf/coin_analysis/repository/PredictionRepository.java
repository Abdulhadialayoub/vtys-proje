package com.rf.coin_analysis.repository;

import com.rf.coin_analysis.model.Predictions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionRepository extends JpaRepository<Predictions,Long> {
}
