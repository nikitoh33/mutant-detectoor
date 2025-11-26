package com.mercadolibre.mutant_detector.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DnaRecord {
    @Id
    private String dnaHash;
    private boolean isMutant;

    // Constructor sin argumentos (requerido por JPA)
    public DnaRecord() {}

    // Constructor con argumentos
    public DnaRecord(String dnaHash, boolean isMutant) {
        this.dnaHash = dnaHash;
        this.isMutant = isMutant;
    }

    // Getters y setters
    public String getDnaHash() {
        return dnaHash;
    }

    public void setDnaHash(String dnaHash) {
        this.dnaHash = dnaHash;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
