package com.roselinorozco.pokedex.userservice.application.dto;

import com.roselinorozco.pokedex.userservice.domain.model.User;

/**
 * @author Roselin Orozco
 */
public class UserCreateRequest {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser() {
        User user = new User();
        user.setEmail(this.email);
        user.setPassword(this.password);

        return user;
    }
}
