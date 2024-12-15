package com.rf.coin_analysis.controller;

import com.rf.coin_analysis.config.ApiPaths;
import com.rf.coin_analysis.dto.ApiResponse;
import com.rf.coin_analysis.dto.CoinDto;
import com.rf.coin_analysis.repository.PredictionsRepository;
import com.rf.coin_analysis.service.CoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.COIN)
@CrossOrigin
public class CoinController {
   private final CoinService service;

    public CoinController(CoinService service) {
        this.service = service;
    }

    // coin bilgisini getir
    @GetMapping(ApiPaths.GET)
    ResponseEntity<ApiResponse<CoinDto>> getCoin(@PathVariable Long id){
        return ResponseEntity.ok(service.getCoin(id));
    }
    // coin listesini getir
    @GetMapping(ApiPaths.LIST)
    ResponseEntity<List<CoinDto>> getList(){
        return ResponseEntity.ok(service.getList());
    }
    // risk skoru en düşükten en yükseğe sırasıyla coinleri getir
    @GetMapping(ApiPaths.ORDER_BY_ASC)
    ResponseEntity<List<CoinDto>> sortAsc(){
        return ResponseEntity.ok(service.findByOrderRiskScoreAsc());
    }
    // coinin tl bilgisini döndür
    @GetMapping(ApiPaths.GET_COIN_PRICE)
    ResponseEntity<Double> getPrice(@PathVariable String name){
        return ResponseEntity.ok(service.getPrice(name));
    }
}
