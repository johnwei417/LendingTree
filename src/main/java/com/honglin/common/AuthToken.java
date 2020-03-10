package com.honglin.common;

import java.util.List;

public class AuthToken {

    private String token;
    private String username;
    private int id;
    private List<String> role;

    public AuthToken() {

    }

    public AuthToken(String token, String username, int id, List<String> role) {
        this.token = token;
        this.username = username;
        this.id = id;
        this.role = role;
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
