package com.mercadolibre.mutant_detector.controller;

import com.mercadolibre.mutant_detector.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping
    public ResponseEntity<Void> isMutant(@RequestBody Map<String, String[]> request) {
        String[] dna = request.get("dna");
        boolean result = mutantService.checkAndSave(dna);
        return result ? ResponseEntity.ok().build() : ResponseEntity.status(403).build();
    }
}
