package com.rf.coin_analysis.service;

import com.rf.coin_analysis.exception.CoinException;
import com.rf.coin_analysis.exception.NotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ClientService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "https://api.coingecko.com/api/v3/simple/price";

    public ClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double getCoinPriceInTry(String coinName) {
        String url = String.format("%s?ids=%s&vs_currencies=try", apiUrl, coinName);


        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ResponseEntity<Map<String, Map<String, Double>>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Map<String, Double> coinData = response.getBody().get(coinName);
            if (coinData != null) {
                return coinData.get("try");
            }
        }
        return 0.99;
    }
}
