package com.mercadolibre.mutant_detector.service;

import org.testng.annotations.Test;




import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class MutantServiceTest {

    @Test
    public void testIsMutant_WithMutantDNA_ReturnsTrue() {
        // Crear una instancia de MutantService
        MutantService mutantService = new MutantService();

        // ADN mutante de ejemplo
        String[] mutantDna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        // Verificar que el método isMutant devuelva true para un ADN mutante
        boolean result = mutantService.isMutant(mutantDna);
        assertTrue("El ADN proporcionado debería ser detectado como mutante", result);
    }

    @Test
    public void testIsMutant_WithHumanDNA_ReturnsFalse() {
        // Crear una instancia de MutantService
        MutantService mutantService = new MutantService();

        // ADN no mutante de ejemplo
        String[] humanDna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};

        // Verificar que el método isMutant devuelva false para un ADN no mutante
        boolean result = mutantService.isMutant(humanDna);
        assertFalse(result, "El ADN proporcionado no debería ser detectado como mutante");
    }

    @Test
    public void testIsMutant_WithHorizontalSequence_ReturnsTrue() {
        // Crear una instancia de MutantService
        MutantService mutantService = new MutantService();

        // ADN con secuencia horizontal de 4 letras iguales
        String[] dnaWithHorizontalSequence = {"ATGCGA", "CAGTGC", "TTTTTT", "AGACGG", "GCGTCA", "TCACTG"};

        // Verificar que el método isMutant devuelva true para una secuencia horizontal
        boolean result = mutantService.isMutant(dnaWithHorizontalSequence);
        assertTrue("El ADN con secuencia horizontal debería ser detectado como mutante", result);
    }

    @Test
    public void testIsMutant_WithVerticalSequence_ReturnsTrue() {
        // Crear una instancia de MutantService
        MutantService mutantService = new MutantService();

        // ADN con secuencia vertical de 4 letras iguales
        String[] dnaWithVerticalSequence = {"ATGCGA", "CAGTGC", "TTATGT", "AGACGG", "GCGTCA", "TCACTG"};

        // Verificar que el método isMutant devuelva true para una secuencia vertical
        boolean result = mutantService.isMutant(dnaWithVerticalSequence);
        assertTrue("El ADN con secuencia vertical debería ser detectado como mutante", result);
    }

    @Test
    public void testIsMutant_WithDiagonalSequence_ReturnsTrue() {
        // Crear una instancia de MutantService
        MutantService mutantService = new MutantService();

        // ADN con secuencia diagonal de 4 letras iguales
        String[] dnaWithDiagonalSequence = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        // Verificar que el método isMutant devuelva true para una secuencia diagonal
        boolean result = mutantService.isMutant(dnaWithDiagonalSequence);
        assertTrue("El ADN con secuencia diagonal debería ser detectado como mutante", result);
    }

    @Test
    public void testIsMutant_WithInvalidDNA_ReturnsFalse() {
        // Crear una instancia de MutantService
        MutantService mutantService = new MutantService();

        // ADN inválido (contiene una letra no permitida 'X')
        String[] invalidDna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAXG", "CCCCTA", "TCACTG"};

        // Verificar que el método isMutant devuelva false para un ADN inválido
        boolean result = mutantService.isMutant(invalidDna);
        assertFalse(result, "El ADN inválido no debería ser detectado como mutante");
    }
}
