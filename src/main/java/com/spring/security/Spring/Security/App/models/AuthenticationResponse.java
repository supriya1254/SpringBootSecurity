package com.spring.security.Spring.Security.App.models;

//This class contain a JSON web token.
//Hence it will provide ouput argument to my authenticate method.
public class AuthenticationResponse {
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
