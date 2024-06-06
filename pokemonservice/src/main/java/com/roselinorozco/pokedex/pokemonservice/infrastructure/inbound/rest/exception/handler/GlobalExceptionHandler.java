package com.roselinorozco.pokedex.pokemonservice.infrastructure.inbound.rest.exception.handler;

import com.roselinorozco.pokedex.pokemonservice.domain.exception.*;
import com.roselinorozco.pokedex.pokemonservice.infrastructure.inbound.rest.exception.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

/**
 * @author Roselin Orozco
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnableToGetRandomNumberException.class)
    public ResponseEntity<Object> handleUnableToGetRandomNumberException(final UnableToGetRandomNumberException exception) {
        final ApiError apiError = new ApiError(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Random Number service is not available",
                Collections.singletonList(exception.getMessage())
        );
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidPageOrSizeException.class)
    public ResponseEntity<Object> handleInvalidPageOrSizeException(final InvalidPageOrSizeException exception) {
        final ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid Page or Size",
                Collections.singletonList(exception.getMessage())
        );
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(RegisterNotAllowedException.class)
    public ResponseEntity<Object> handleRegisterNotAllowedException(final RegisterNotAllowedException exception) {
        final ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Register not permitted",
                Collections.singletonList(exception.getMessage())
        );
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DeleteNotAllowedException.class)
    public ResponseEntity<Object> handleDeleteNotAllowedException(final DeleteNotAllowedException exception) {
        final ApiError apiError = new ApiError(
                HttpStatus.CONFLICT,
                "Deletion not permitted",
                Collections.singletonList(exception.getMessage())
        );
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(PokemonDoesNotExistsException.class)
    public ResponseEntity<Object> handlePokemonDoesNotExistsException(final PokemonDoesNotExistsException exception) {
        final ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                "Pokemon Not Found",
                Collections.singletonList(exception.getMessage())
        );
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(PokemonBelongToOtherOwnerException.class)
    public ResponseEntity<Object> handlePokemonBelongToOtherOwnerException(final PokemonBelongToOtherOwnerException exception) {
        final ApiError apiError = new ApiError(
                HttpStatus.UNAUTHORIZED,
                "Pokémon belongs to another owner",
                Collections.singletonList(exception.getMessage())
        );
        return buildResponseEntity(apiError);
    }



    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<Object> handleInvalidFieldException(InvalidFieldException exception) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid field "
                + exception.getFieldName() + ".",
                Collections.singletonList(exception.getMessage()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UpdateNotAllowedException.class)
    public ResponseEntity<Object> handleUpdateNotAllowedException(final UpdateNotAllowedException exception) {
        final ApiError apiError = new ApiError(
                HttpStatus.UNAUTHORIZED,
                "Update Forbidden",
                Collections.singletonList(exception.getMessage())
        );
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ValidateTokenException.class)
    public ResponseEntity<Object> handleValidateTokenException(final ValidateTokenException exception) {
        final ApiError apiError = new ApiError(
                HttpStatus.UNAUTHORIZED,
                "Invalid Token",
                Collections.singletonList(exception.getMessage())
        );
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException exception) {
        String error = "Malformed JSON request";
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Malformed JSON request",
                Collections.singletonList(exception.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralExceptions(final Exception exception) {

        final ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred.",
                Collections.singletonList(exception.getMessage()));
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
