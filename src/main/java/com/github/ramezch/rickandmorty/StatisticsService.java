package com.github.ramezch.rickandmorty;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class StatisticsService {
    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://rickandmortyapi.com/api").build();

    public int getSpeciesCount(String species) {
        StatisticsResponse response = restClient.get().uri("/character/?species=" + species).retrieve().body(StatisticsResponse.class);
        assert response != null;
        return response.info().count();
    }
}
