package com.roselinorozco.pokedex.pokemonservice.application.validation;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.InvalidPageOrSizeException;

/**
 * @author Roselin Orozco
 */
public class PageValidationService {

    public void validate(final Integer page, final Integer size) {

        if (page == null || size == null) {
            throw new InvalidPageOrSizeException("Page and size must be provided");
        }

        if (page < 0) {
            throw new InvalidPageOrSizeException("Page number must be non-negative");
        }

        if (size < 1) {
            throw new InvalidPageOrSizeException("Page size must be greater than 0");
        }
    }
}
