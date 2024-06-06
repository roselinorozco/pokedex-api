package com.roselinorozco.pokedex.pokemonservice.application.validation;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.InvalidPageOrSizeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Roselin Orozco
 */
public class PageValidationServiceTest {

    @Test
    public void testValidateValidPageAndSizeNoExceptionThrown() {

        final PageValidationService pageValidationService = new PageValidationService();

        assertDoesNotThrow(() -> {
            pageValidationService.validate(1, 10);
        });
    }

    @Test
    public void testValidateNullPageThrowsException() {

        final PageValidationService pageValidationService = new PageValidationService();

        final InvalidPageOrSizeException exception = assertThrows(InvalidPageOrSizeException.class, () -> {
            pageValidationService.validate(null, 10);
        });

        assertEquals("Page and size must be provided", exception.getMessage());
    }

    @Test
    public void testValidateNullSizeThrowsException() {

        final PageValidationService pageValidationService = new PageValidationService();

        final InvalidPageOrSizeException exception = assertThrows(InvalidPageOrSizeException.class, () -> {
            pageValidationService.validate(1, null);
        });

        assertEquals("Page and size must be provided", exception.getMessage());
    }

    @Test
    public void testValidateNegativePageThrowsException() {

        final PageValidationService pageValidationService = new PageValidationService();

        final InvalidPageOrSizeException exception = assertThrows(InvalidPageOrSizeException.class, () -> {
            pageValidationService.validate(-1, 10);
        });

        assertEquals("Page number must be non-negative", exception.getMessage());
    }

    @Test
    public void testValidate_NegativeSize_ThrowsException() {

        final PageValidationService pageValidationService = new PageValidationService();

        final InvalidPageOrSizeException exception = assertThrows(InvalidPageOrSizeException.class, () -> {
            pageValidationService.validate(1, -10);
        });

        // Verify the exception message
        assertEquals("Page size must be greater than 0", exception.getMessage());
    }

    @Test
    public void testValidate_ZeroSize_ThrowsException() {
        // Create an instance of PageValidationService
        PageValidationService pageValidationService = new PageValidationService();

        // Call the validate method with zero size
        InvalidPageOrSizeException exception = assertThrows(InvalidPageOrSizeException.class, () -> {
            pageValidationService.validate(1, 0);
        });

        // Verify the exception message
        assertEquals("Page size must be greater than 0", exception.getMessage());
    }
}
