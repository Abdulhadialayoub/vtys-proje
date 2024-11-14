package com.rf.coin_analysis.controller;

import com.rf.coin_analysis.repository.PredictionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CoinController {
    private final PredictionRepository repository;

    public CoinController(PredictionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<?> getCoinList(){
        return ResponseEntity.ok(repository.findAll());
    }
    // coin bilgisini getir
    // coin listesini getir
    // coin favoriye ekle
    // coini favoriden çıkar
    // favori coin listesini getir
    // risk skoru en düşükten en yükseğe sırasıyla coinleri getir
    // coinin tl bilgisini döndür


}
