package com.rf.coin_analysis.service;

import com.rf.coin_analysis.dto.ApiResponse;
import com.rf.coin_analysis.dto.FavoriteCoinDto;
import com.rf.coin_analysis.dto.converter.DtoConverter;
import com.rf.coin_analysis.exception.FavoriteCoinException;
import com.rf.coin_analysis.exception.NoAuthorityException;
import com.rf.coin_analysis.model.FavoriteCoin;
import com.rf.coin_analysis.model.Predictions;
import com.rf.coin_analysis.model.User;
import com.rf.coin_analysis.repository.FavoriteCoinRepository;
import com.rf.coin_analysis.security.UserIdentityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FavoriteServiceTest {
    @Mock
    private FavoriteCoinRepository repository;

    @Mock
    private CoinService coinService;

    @Mock
    private UserService userService;

    @Mock
    private DtoConverter converter;

    @Mock
    private UserIdentityManager manager;

    @InjectMocks
    private FavoriteService favoriteService;

    @Test
    void addFavorite_ShouldThrowFavoriteCoinException_WhenCoinAlreadyFavorited() {
        // Arrange
        Long coinId = 1L;
        Long userId = 1L;

        User user = new User();
        Predictions coin = new Predictions();

        when(userService.findById(userId)).thenReturn(user);
        when(coinService.findById(coinId)).thenReturn(coin);
        when(repository.findByUserIdAndPredictionsId(userId, coinId)).thenReturn(Optional.of(new FavoriteCoin()));

        // Act & Assert
        assertThrows(FavoriteCoinException.class, () -> favoriteService.addFavorite(coinId, userId));
    }

    @Test
    void addFavorite_ShouldThrowNoAuthorityException_WhenUserIsNotAuthorized() {
        // Arrange
        Long coinId = 1L;
        Long userId = 1L;

        User user = new User();
        Predictions coin = new Predictions();

        when(userService.findById(userId)).thenReturn(user);
        when(coinService.findById(coinId)).thenReturn(coin);
        when(repository.findByUserIdAndPredictionsId(userId, coinId)).thenReturn(Optional.empty());
        when(manager.isAuthorized(manager.getAuthenticationId(), userId)).thenReturn(false);

        // Act & Assert
        assertThrows(NoAuthorityException.class, () -> favoriteService.addFavorite(coinId, userId));
    }

    @Test
    void addFavorite_ShouldSaveFavoriteCoin_WhenValidRequest() {
        // Arrange
        Long coinId = 1L;
        Long userId = 1L;

        User user = new User();
        Predictions coin = new Predictions();
        FavoriteCoin favoriteCoin = FavoriteCoin.builder().user(user).predictions(coin).build();

        when(userService.findById(userId)).thenReturn(user);
        when(coinService.findById(coinId)).thenReturn(coin);
        when(repository.findByUserIdAndPredictionsId(userId, coinId)).thenReturn(Optional.empty());
        when(manager.isAuthorized(manager.getAuthenticationId(), userId)).thenReturn(true);
        when(repository.save(any(FavoriteCoin.class))).thenReturn(favoriteCoin);

        // Act
        ApiResponse<Void> response = favoriteService.addFavorite(coinId, userId);

        // Assert
        assertEquals("Coin Favoriye eklendi", response.getMessage());
        verify(repository, times(1)).save(any(FavoriteCoin.class));
    }

    @Test
    void delete_ShouldThrowNoAuthorityException_WhenUserIsNotAuthorized() {
        // Arrange
        Long favoriteCoinId = 1L;
        FavoriteCoin favoriteCoin = new FavoriteCoin();
        favoriteCoin.setUser(new User());

        when(repository.findById(favoriteCoinId)).thenReturn(Optional.of(favoriteCoin));
        when(manager.isAuthorized(manager.getAuthenticationId(), favoriteCoin.getUser().getId())).thenReturn(false);

        // Act & Assert
        assertThrows(NoAuthorityException.class, () -> favoriteService.delete(favoriteCoinId));
    }

    @Test
    void delete_ShouldDeleteFavoriteCoin_WhenValidRequest() {
        // Arrange
        Long favoriteCoinId = 1L;
        FavoriteCoin favoriteCoin = new FavoriteCoin();
        favoriteCoin.setUser(new User());

        when(repository.findById(favoriteCoinId)).thenReturn(Optional.of(favoriteCoin));
        when(manager.isAuthorized(manager.getAuthenticationId(), favoriteCoin.getUser().getId())).thenReturn(true);

        // Act
        ApiResponse<Void> response = favoriteService.delete(favoriteCoinId);

        // Assert
        assertEquals("Coin Favoriteden çıkarıldı", response.getMessage());
        verify(repository, times(1)).delete(favoriteCoin);
    }

    @Test
    void list_ShouldReturnNull_WhenUserIsNotAuthorized() {
        // Arrange
        Long userId = 1L;
        when(manager.isAuthorized(manager.getAuthenticationId(), userId)).thenReturn(false);

        // Act
        List<FavoriteCoinDto> result = favoriteService.list(userId);

        // Assert
        assertNull(result);
    }

    @Test
    void list_ShouldReturnFavoriteCoins_WhenUserIsAuthorized() {
        // Arrange
        Long userId = 1L;
        FavoriteCoin favoriteCoin = new FavoriteCoin();
        FavoriteCoinDto favoriteCoinDto = new FavoriteCoinDto();
        List<FavoriteCoin> favoriteCoins = List.of(favoriteCoin);

        when(manager.isAuthorized(manager.getAuthenticationId(), userId)).thenReturn(true);
        when(repository.findAllByUserId(userId)).thenReturn(favoriteCoins);
        when(converter.convertFavorite(favoriteCoin)).thenReturn(favoriteCoinDto);

        // Act
        List<FavoriteCoinDto> result = favoriteService.list(userId);

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(favoriteCoinDto));
        verify(repository, times(1)).findAllByUserId(userId);
    }

}