package com.ttn.linksharing.Dto;

import com.ttn.linksharing.entity.*;

import java.util.Date;
import java.util.Set;

public class UserDto {
    
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String photo;
    private String facebook;
    private String twitter;
    private String instagram;
    private String role;
    private Integer isActive;
    private Date createdDate;
    
    private Set<Invitations> invitations;
    
    private Set<Ratings> ratings;
    
    private Set<Topics> topics;
    
    private Set<ReadPosts> readPosts;
    private Set<Posts> posts;
    
    
    
    public UserDto() {
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
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
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
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
    
    public String getPhoto() {
        return photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public String getFacebook() {
        return facebook;
    }
    
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
    
    public String getTwitter() {
        return twitter;
    }
    
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
    
    public String getInstagram() {
        return instagram;
    }
    
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public Integer getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public Set<Invitations> getInvitations() {
        return invitations;
    }
    
    public void setInvitations(Set<Invitations> invitations) {
        this.invitations = invitations;
    }
    
    public Set<Ratings> getRatings() {
        return ratings;
    }
    
    public void setRatings(Set<Ratings> ratings) {
        this.ratings = ratings;
    }
    
    public Set<Topics> getTopics() {
        return topics;
    }
    
    public void setTopics(Set<Topics> topics) {
        this.topics = topics;
    }
    
    public Set<ReadPosts> getReadPosts() {
        return readPosts;
    }
    
    public void setReadPosts(Set<ReadPosts> readPosts) {
        this.readPosts = readPosts;
    }
    
    public Set<Posts> getPosts() {
        return posts;
    }
    
    public void setPosts(Set<Posts> posts) {
        this.posts = posts;
    }
}
