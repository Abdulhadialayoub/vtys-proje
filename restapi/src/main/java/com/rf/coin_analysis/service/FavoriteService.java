package com.rf.coin_analysis.service;

import com.rf.coin_analysis.dto.ApiResponse;
import com.rf.coin_analysis.dto.FavoriteCoinDto;
import com.rf.coin_analysis.dto.converter.DtoConverter;
import com.rf.coin_analysis.exception.FavoriteCoinException;
import com.rf.coin_analysis.exception.NoAuthorityException;
import com.rf.coin_analysis.exception.NotFoundException;
import com.rf.coin_analysis.model.FavoriteCoin;
import com.rf.coin_analysis.model.Predictions;
import com.rf.coin_analysis.model.User;
import com.rf.coin_analysis.repository.FavoriteCoinRepository;
import com.rf.coin_analysis.security.UserIdentityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteCoinRepository repository;
    private final CoinService coinService;
    private final UserService userService;
    private final DtoConverter converter;
    private final UserIdentityManager manager;

    public ApiResponse<Void> addFavorite(Long coinID, Long userId) {
        User user=userService.findById(userId);
        Predictions coin=coinService.findById(coinID);
        Optional<FavoriteCoin> exist=repository.findByUserIdAndPredictionsId(userId,coinID);
        if(exist.isPresent()) throw new FavoriteCoinException();
        if(!manager.isAuthorized(manager.getAuthenticationId(), userId)) throw new NoAuthorityException();
        FavoriteCoin favoriteCoin=FavoriteCoin.builder().user(user).predictions(coin).build();
        repository.save(favoriteCoin);
        return ApiResponse.ok("Coin Favoriye eklendi");
    }
    protected FavoriteCoin findById(Long id){
        return repository.findById(id).orElseThrow(()->new NotFoundException("Coin"));
    }

    public ApiResponse<Void> delete(Long id) {
        FavoriteCoin coin=findById(id);
        if(!manager.isAuthorized(manager.getAuthenticationId(), coin.getUser().getId())) throw new NoAuthorityException();
        repository.delete(coin);
        return ApiResponse.ok("Coin Favoriteden çıkarıldı");
    }

    public List<FavoriteCoinDto> list(Long userId) {
        if(!manager.isAuthorized(manager.getAuthenticationId(),userId)) return null;
        return repository.findAllByUserId(userId).stream().map(converter::convertFavorite).collect(Collectors.toList());
    }
}
