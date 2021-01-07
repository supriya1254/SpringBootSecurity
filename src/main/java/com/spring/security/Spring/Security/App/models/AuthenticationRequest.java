package com.spring.security.Spring.Security.App.models;

//this calss contain a couple of member variables.
// Hence it will provide input argument to my authenticate method.
public class AuthenticationRequest {

    private String username;
    private String password;

    //this default constructor i need for Serialization.
    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
