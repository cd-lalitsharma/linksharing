package com.ttn.linksharing.co;

import javax.validation.constraints.Size;

public class LoginCo {
    
    @Size(min = 2,message = "nai lega")
    private String Username;
    private String password;
    
    public String getUsername() {
        return Username;
    }
    
    public void setUsername(String username) {
        Username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "LoginCo{" +
                "Username='" + Username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
