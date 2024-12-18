package com.rf.coin_analysis.service;

import com.rf.coin_analysis.dto.ApiResponse;
import com.rf.coin_analysis.dto.CoinDto;
import com.rf.coin_analysis.dto.converter.DtoConverter;
import com.rf.coin_analysis.exception.NotFoundException;
import com.rf.coin_analysis.model.Predictions;
import com.rf.coin_analysis.repository.PredictionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CoinServiceTest {
    @Mock
    private PredictionsRepository repository;
    @Mock
    private DtoConverter converter;
    @Mock
    private ClientService clientService;
    @InjectMocks
    private CoinService coinService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_ShouldReturnPredictions_WhenIdExist() {
        // arrange
        Long mockId = 1L;
        Predictions mockPredictions = new Predictions();
        mockPredictions.setId(1L);
        Mockito.when(repository.findById(mockId)).thenReturn(Optional.of(mockPredictions));
        // act
        Predictions result = coinService.findById(mockId);
        // assert
        assertNotNull(result);
        assertEquals(mockId, result.getId());
        Mockito.verify(repository, Mockito.times(1)).findById(mockId);
    }

    @Test
    void findById_ShouldThrowNotFoundException_WhenIdDoesNotExist() {
        // arrange
        Long id = 1L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        // assert
        assertThrows(NotFoundException.class, () -> coinService.findById(id));
        Mockito.verify(repository, Mockito.times(1)).findById(id);

    }
    @Test
    void getCoin_ShouldReturnApiResponse_WhenIdExist(){
        // Arrange
        Long id = 1L;
        Predictions prediction = new Predictions();
        prediction.setId(id);
        CoinDto coinDto = new CoinDto();
        coinDto.setId(id);

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(prediction));
        Mockito.when(converter.convertCoin(prediction)).thenReturn(coinDto);

        // Act
        ApiResponse<CoinDto> response = coinService.getCoin(id);
        //Assert
        assertNotNull(response);
        assertEquals(response.data.getId(),id);
        Mockito.verify(repository,Mockito.times(1)).findById(1L);
        Mockito.verify(converter,Mockito.times(1)).convertCoin(prediction);
    }
    @Test
    void getList_ShouldReturnConvertedList_WhenFindAllIsCalled() {
        // Arrange
        Predictions prediction1 = new Predictions();
        Predictions prediction2 = new Predictions();
        List<Predictions> predictionsList = List.of(prediction1, prediction2);

        CoinDto coinDto1 = new CoinDto();
        CoinDto coinDto2 = new CoinDto();

        Mockito.when(repository.findAll()).thenReturn(predictionsList);
        Mockito.when(converter.convertCoin(prediction1)).thenReturn(coinDto1);
        Mockito.when(converter.convertCoin(prediction2)).thenReturn(coinDto2);

        // Act
        List<CoinDto> result = coinService.getList();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(coinDto1));
        assertTrue(result.contains(coinDto2));
        Mockito.verify(repository, Mockito.times(1)).findAll();

    }

    @Test
    void findByOrderRiskScoreAsc_ShouldReturnOrderedList_WhenRepositoryReturnsData() {
        // Arrange
        Predictions prediction1 = new Predictions();
        Predictions prediction2 = new Predictions();
        List<Predictions> orderedPredictions = List.of(prediction1, prediction2);

        CoinDto coinDto1 = new CoinDto();
        CoinDto coinDto2 = new CoinDto();

        Mockito.when(repository.orderByRiskScoreAsc()).thenReturn(orderedPredictions);
        Mockito.when(converter.convertCoin(prediction1)).thenReturn(coinDto1);
        Mockito.when(converter.convertCoin(prediction2)).thenReturn(coinDto2);

        // Act
        List<CoinDto> result = coinService.findByOrderRiskScoreAsc();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(coinDto1));
        assertTrue(result.contains(coinDto2));
        Mockito.verify(repository, Mockito.times(1)).orderByRiskScoreAsc();

    }

    @Test
    void getPrice_ShouldReturnPrice_WhenClientServiceIsCalled() {
        // Arrange
        String coinName = "Bitcoin";
        double expectedPrice = 50000.0;

        Mockito.when(clientService.getCoinPriceInTry(coinName)).thenReturn(expectedPrice);

        // Act
        double result = coinService.getPrice(coinName);

        // Assert
        assertEquals(expectedPrice, result);
        Mockito.verify(clientService, Mockito.times(1)).getCoinPriceInTry(coinName);
    }


}