package com.rf.coin_analysis;

import com.rf.coin_analysis.config.Initializer;
import com.rf.coin_analysis.model.Predictions;
import com.rf.coin_analysis.repository.PredictionsRepository;
import com.rf.coin_analysis.service.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CoinAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinAnalysisApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(PredictionsRepository repository, ClientService clientService){
		return args -> {
			List<Predictions> predictions=repository.findAll();
			for (Predictions p : predictions){
				if(p.getDescription()==null){
                   p.setDescription(Initializer.getRandomDescription(p.getRiskScore()));
				   repository.save(p);
				}
				if(p.getPrice()==null){
					p.setPrice(clientService.getCoinPriceInTry(p.getCoin()));
					repository.save(p);
				}
			}

		};
	}

}
