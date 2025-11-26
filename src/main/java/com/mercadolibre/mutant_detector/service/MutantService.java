package com.mercadolibre.mutant_detector.service;

import com.mercadolibre.mutant_detector.model.DnaRecord;
import com.mercadolibre.mutant_detector.repository.DnaRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MutantService {

    @Autowired
    private DnaRecordRepository repository;

    public boolean isMutant(String[] dna) {
        int n = dna.length;
        int count = 0;

        // Validar matriz NxN y caracteres
        for (String row : dna) {
            if (row.length() != n || !row.matches("[ATCG]+")) {
                return false;
            }
        }

        // Buscar secuencias horizontales
        for (String row : dna) {
            for (int i = 0; i <= n - 4; i++) {
                if (row.charAt(i) == row.charAt(i + 1) &&
                        row.charAt(i) == row.charAt(i + 2) &&
                        row.charAt(i) == row.charAt(i + 3)) {
                    count++;
                    if (count > 1) return true;
                }
            }
        }

        // Buscar secuencias verticales
        for (int col = 0; col < n; col++) {
            for (int row = 0; row <= n - 4; row++) {
                if (dna[row].charAt(col) == dna[row + 1].charAt(col) &&
                        dna[row].charAt(col) == dna[row + 2].charAt(col) &&
                        dna[row].charAt(col) == dna[row + 3].charAt(col)) {
                    count++;
                    if (count > 1) return true;
                }
            }
        }

        // Buscar secuencias diagonales (abajo-derecha)
        for (int row = 0; row <= n - 4; row++) {
            for (int col = 0; col <= n - 4; col++) {
                if (dna[row].charAt(col) == dna[row + 1].charAt(col + 1) &&
                        dna[row].charAt(col) == dna[row + 2].charAt(col + 2) &&
                        dna[row].charAt(col) == dna[row + 3].charAt(col + 3)) {
                    count++;
                    if (count > 1) return true;
                }
            }
        }

        // Buscar secuencias diagonales (abajo-izquierda)
        for (int row = 0; row <= n - 4; row++) {
            for (int col = 3; col < n; col++) {
                if (dna[row].charAt(col) == dna[row + 1].charAt(col - 1) &&
                        dna[row].charAt(col) == dna[row + 2].charAt(col - 2) &&
                        dna[row].charAt(col) == dna[row + 3].charAt(col - 3)) {
                    count++;
                    if (count > 1) return true;
                }
            }
        }

        return false;
    }

    public boolean checkAndSave(String[] dna) {
        boolean result = isMutant(dna);
        String dnaHash = String.valueOf(Arrays.deepHashCode(dna));
        repository.save(new DnaRecord(dnaHash, result));
        return result;
    }
}
