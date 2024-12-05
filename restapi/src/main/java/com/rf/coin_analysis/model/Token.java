package com.rf.coin_analysis.model;

import jakarta.persistence.*;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {
    @Id
    private String token;
    @ManyToOne
    private User user;
}
