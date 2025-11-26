package com.mercadolibre.mutant_detector.controller;

import com.mercadolibre.mutant_detector.repository.DnaRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private DnaRecordRepository repository;

    @GetMapping
    public Map<String, Object> getStats() {
        long mutantCount = repository.countByIsMutant(true);
        long humanCount = repository.countByIsMutant(false);
        double ratio = humanCount == 0 ? 0 : (double) mutantCount / humanCount;
        return Map.of(
                "count_mutant_dna", mutantCount,
                "count_human_dna", humanCount,
                "ratio", ratio
        );
    }
}
