package com.ttn.linksharing.co;

import javax.validation.constraints.Size;

public class LoginCo {
    
    @Size(min = 2,message = "Username should be greater than 2 character")
    private String Username;
    @Size(min = 2,message = "password should be greater than 2 character")
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
