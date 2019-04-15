package com.ttn.linksharing.co;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class SignupCo {
    
    @Size(min = 3, max = 10,message = "firstName should be between 3 to 10 characters")
    private String firstName;
    @Size(min = 3, max = 10,message = "lastName should be between 3 to 10 characters")
    private String lastName;
    @Email
    private String email;
    @Size(min = 3, max = 10,message = "username should be between 3 to 10 characters")
    private String username;
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Size(min = 3, max = 10,message = "password should be between 3  to 10 characters")
    private String password;
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    
    @Override
    public String toString() {
        return "SignupCo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
