package com.roselinorozco.pokedex.userservice.infrastructure.inbound.rest.auth.adapter.controller;

import com.roselinorozco.pokedex.userservice.domain.port.in.RegisterUserInputPort;
import com.roselinorozco.pokedex.userservice.domain.exception.EmailIsAlreadyRegisteredException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidFieldException;
import com.roselinorozco.pokedex.userservice.domain.exception.InvalidPasswordException;
import com.roselinorozco.pokedex.userservice.domain.model.User;
import com.roselinorozco.pokedex.userservice.application.dto.UserCreateRequest;
import com.roselinorozco.pokedex.userservice.application.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Roselin Orozco
 */
@RestController
@RequestMapping("/api/user")
public class AuthRestController {

    final RegisterUserInputPort registerUserInputPort;

    public AuthRestController(RegisterUserInputPort registerUserInputPort) {
        this.registerUserInputPort = registerUserInputPort;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest userCreateRequest)
            throws EmailIsAlreadyRegisteredException, InvalidPasswordException, InvalidFieldException {

        final User user = new User(userCreateRequest.getEmail(), userCreateRequest.getPassword());
        final User createdUser = registerUserInputPort.execute(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(createdUser.getEmail()));
    }
}
