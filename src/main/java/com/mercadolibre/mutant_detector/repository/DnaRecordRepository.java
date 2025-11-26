package com.mercadolibre.mutant_detector.repository;

import com.mercadolibre.mutant_detector.model.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, String> {
    long countByIsMutant(boolean isMutant);
}
