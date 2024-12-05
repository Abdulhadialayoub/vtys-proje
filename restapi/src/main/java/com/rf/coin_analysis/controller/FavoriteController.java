package com.rf.coin_analysis.controller;

import com.rf.coin_analysis.config.ApiPaths;
import com.rf.coin_analysis.dto.ApiResponse;
import com.rf.coin_analysis.dto.FavoriteCoinDto;
import com.rf.coin_analysis.model.FavoriteCoin;
import com.rf.coin_analysis.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.FAVORITE)
public class FavoriteController {
    private final FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    // favoriye ekle
    @PostMapping(ApiPaths.ADD)
    ResponseEntity<ApiResponse<Void>> addFavorite(@PathVariable Long coinID,@PathVariable Long userId){
        return ResponseEntity.ok(service.addFavorite(coinID,userId));
    }
    // favoriden çıkar
    @DeleteMapping(ApiPaths.DELETE)
    ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
    // kullanıya ait favori listesini getir
    @GetMapping(ApiPaths.FAVORITE_LIST)
    ResponseEntity<List<FavoriteCoinDto>> list(@PathVariable Long userId){
        return ResponseEntity.ok(service.list(userId));
    }


}
