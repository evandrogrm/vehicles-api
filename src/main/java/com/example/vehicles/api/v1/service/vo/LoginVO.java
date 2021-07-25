package com.example.vehicles.api.v1.service.vo;

public class LoginVO {

    private String id;
    private String name;
    private String email;
    private String token;

    public String getId() {
        return id;
    }

    public LoginVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LoginVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginVO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginVO setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
