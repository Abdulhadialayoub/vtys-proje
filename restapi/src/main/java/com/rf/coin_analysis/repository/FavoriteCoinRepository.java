package com.rf.coin_analysis.repository;

import com.rf.coin_analysis.model.FavoriteCoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteCoinRepository extends JpaRepository<FavoriteCoin,Long> {
    Optional<FavoriteCoin> findByUserIdAndPredictionsId(Long userId, Long coinID);
    List<FavoriteCoin> findAllByUserId(Long userId);
}
