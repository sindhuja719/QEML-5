package com.practice.api.models;

public class AuthenticationUser {
    private String username;
    private String password;
    private Boolean rememberMe;

    //default values (not necessary but helpful)
    private AuthenticationUser() {
        this.username = "";
        this.password = "";
        this.rememberMe = true;
    }

    public AuthenticationUser(String username, String password, Boolean rememberMe) {
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    //getters
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Boolean getRememberMe() {
        return this.rememberMe;
    }

    //private constructor for the class (as we initialize the object using builder class)
    private AuthenticationUser(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.rememberMe = builder.rememberMe;
    }

    //inner static builder class
    public static class Builder {
        private String username;
        private String password;
        private Boolean rememberMe;

        public Builder() {
            AuthenticationUser authenticationUser = new AuthenticationUser();
            this.username = authenticationUser.getUsername();
            this.password = authenticationUser.getPassword();
            this.rememberMe = authenticationUser.getRememberMe();
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRememberMe(Boolean rememberMe) {
            this.rememberMe = rememberMe;
            return this;
        }

        //returning the object
        public AuthenticationUser build() {
            return new AuthenticationUser(this);
        }
    }

    public String toString() {
        return "AuthenticationUser{username='" + this.username + '\'' + ", password='" + this.password + '\'' + ", rememberMe=" + this.rememberMe + '}';
    }
}
