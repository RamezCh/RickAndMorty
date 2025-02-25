package com.github.ramezch.rickandmorty;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/species-statistic")
public class StatisticsController {
    private final StatisticsService service;

    @GetMapping
    public int getSpeciesStatistic(@RequestParam String species) {
        return service.getSpeciesCount(species);
    }

}
