package com.rf.coin_analysis.dto;
import com.rf.coin_analysis.model.Predictions;
import com.rf.coin_analysis.model.User;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteCoinDto {
    private Long id;
    private UserDto user;
    private CoinDto coin;
}
