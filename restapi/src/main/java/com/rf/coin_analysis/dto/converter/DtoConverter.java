package com.rf.coin_analysis.dto.converter;

import com.rf.coin_analysis.dto.CoinDto;
import com.rf.coin_analysis.dto.FavoriteCoinDto;
import com.rf.coin_analysis.dto.UserDto;
import com.rf.coin_analysis.model.FavoriteCoin;
import com.rf.coin_analysis.model.Predictions;
import com.rf.coin_analysis.model.User;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoConverter {
    public CoinDto convertCoin(Predictions predictions){
        return CoinDto.builder().
                id(predictions.getId()).prediction(predictions.getPrediction()).date(predictions.getDate())
                        .coin(predictions.getCoin()).description(predictions.getDescription()).positiveCount(predictions.getPositiveCount())
                        .negativeCount(predictions.getNegativeCount()).price(predictions.getPrice())
                                .riskScore(predictions.getRiskScore()).build();
    }
    public UserDto convertUser(User user){
        return UserDto.builder().email(user.getEmail()).name(user.getName()).id(user.getId()).build();
    }

    public FavoriteCoinDto convertFavorite(FavoriteCoin favoriteCoin) {
        return FavoriteCoinDto.builder().id(favoriteCoin.getId()).coin(convertCoin(favoriteCoin.getPredictions())).user(convertUser(favoriteCoin.getUser()))
                .build();
    }
}
