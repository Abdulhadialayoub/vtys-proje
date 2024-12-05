package com.rf.coin_analysis.repository;

import com.rf.coin_analysis.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, String> {

}
