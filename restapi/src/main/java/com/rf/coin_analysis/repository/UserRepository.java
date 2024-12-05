package com.rf.coin_analysis.repository;

import com.rf.coin_analysis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public boolean existsByEmail(String email);
    public Optional<User> findByEmail(String email);
}
