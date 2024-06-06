package com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.adapter.exception.handler;

import com.roselinorozco.pokedex.userservice.domain.exception.AuthenticateUserException;
import com.roselinorozco.pokedex.userservice.domain.exception.EmailIsAlreadyRegisteredException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidFieldException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidPasswordException;
import com.roselinorozco.pokedex.userservice.domain.exception.UserNotFoundException;
import com.roselinorozco.pokedex.userservice.domain.exception.GenerateTokenException;
import com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.adapter.exception.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

/**
 * @author Roselin Orozco
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailIsAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleEmailIsAlreadyRegistered(EmailIsAlreadyRegisteredException exception) {

        final ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Email is already registered.",
                Collections.singletonList(exception.getMessage()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({InvalidFieldException.class})
    public ResponseEntity<Object> handleInvalidFieldException(InvalidFieldException exception) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid field "
                + exception.getFieldName() + ".",
                Collections.singletonList(exception.getMessage()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({InvalidPasswordException.class})
    public ResponseEntity<Object> handleInvalidPasswordException(InvalidPasswordException exception) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid password.",
                Collections.singletonList(exception.getMessage()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException exception) {

        final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED,
                "Authentication failed due to invalid credentials. Please verify your login information and try again.",
                Collections.singletonList(exception.getMessage()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({AuthenticateUserException.class})
    public ResponseEntity<Object> handleAuthenticateUserException(AuthenticateUserException exception) {

        final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Authentication failed.",
                Collections.singletonList(exception.getMessage()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {

        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "User not found.",
                Collections.singletonList(exception.getMessage()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(GenerateTokenException.class)
    public ResponseEntity<Object> handleGenerateTokenException(GenerateTokenException exception) {

        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Token generation failed",
                Collections.singletonList(exception.getMessage()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String error = "Malformed JSON request";
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Malformed JSON request",
                Collections.singletonList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralExceptions(Exception exception) {

        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred.",
                Collections.singletonList(exception.getMessage()));
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}