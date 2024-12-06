package com.rf.coin_analysis.service;

import com.rf.coin_analysis.dto.ApiResponse;
import com.rf.coin_analysis.dto.CoinDto;
import com.rf.coin_analysis.dto.converter.DtoConverter;
import com.rf.coin_analysis.exception.NotFoundException;
import com.rf.coin_analysis.model.Predictions;
import com.rf.coin_analysis.repository.PredictionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinService {
    private final PredictionsRepository repository;
    private final DtoConverter converter;
    private final ClientService clientService;

    protected Predictions findById(Long id){
        return repository.findById(id).orElseThrow((()->new NotFoundException("Coin")));
    }
    public ApiResponse<CoinDto> getCoin(Long id){
        CoinDto dto=converter.convertCoin(findById(id));
        return ApiResponse.ok("Coin Bilgisi",dto);
    }

    private List<Predictions> findAll() {
        return repository.findAll();
    }

    public List<CoinDto> getList() {
        return findAll().stream().map(converter::convertCoin).collect(Collectors.toList());
    }
    public List<CoinDto> findByOrderRiskScoreAsc(){
        return repository.orderByRiskScoreAsc().stream().map(converter::convertCoin).collect(Collectors.toList());
    }
    public double getPrice(String coinName){
        return clientService.getCoinPriceInTry(coinName);
    }


}
