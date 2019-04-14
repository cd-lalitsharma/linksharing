package com.ttn.linksharing.Dto;

import com.ttn.linksharing.entity.*;

import java.util.*;

public class UserDto {
    
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String photo;
    private String facebook;
    private String twitter;
    private String instagram;
    private String role;
    
    private List<Invitations> invitations= new ArrayList<>();
    
    private List<Ratings> ratings= new ArrayList<>();
    
    private List<Topics> topics= new ArrayList<>();
    
    private List<ReadPosts> readPosts= new ArrayList<>();
    private List<Posts> posts= new ArrayList<>();
    
    private List<Subscriptions> subscriptions= new ArrayList<>();
    private List<Integer> unreadPostsId= new ArrayList<>();
    
    
    private List<Integer> subscribedTopicId= new ArrayList<>();
    
    private List<Posts> unreadPosts=new ArrayList<>();
    
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
    
    public List<Integer> getUnreadPostsId() {
        return unreadPostsId;
    }
    
    public void setUnreadPostsId(List<Integer> unreadPostsId) {
        this.unreadPostsId = unreadPostsId;
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
    
    public List<Invitations> getInvitations() {
        return invitations;
    }
    
    public void setInvitations(List<Invitations> invitations) {
        this.invitations = invitations;
    }
    
    public List<Ratings> getRatings() {
        return ratings;
    }
    
    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }
    
    public List<Topics> getTopics() {
        return topics;
    }
    
    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }
    
    public List<ReadPosts> getReadPosts() {
        return readPosts;
    }
    
    public void setReadPosts(List<ReadPosts> readPosts) {
        this.readPosts = readPosts;
    }
    
    public List<Posts> getPosts() {
        return posts;
    }
    
    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }
    
    public List<Subscriptions> getSubscriptions() {
        return subscriptions;
    }
    
    public void setSubscriptions(List<Subscriptions> subscriptions) {
        this.subscriptions = subscriptions;
    }
    
    public List<Integer> getSubscribedTopicId() {
        return subscribedTopicId;
    }
    
    public void setSubscribedTopicId(List<Integer> subscribedTopicId) {
        this.subscribedTopicId = subscribedTopicId;
    }
    
    public List<Posts> getUnreadPosts() {
        return unreadPosts;
    }
    
    public void setUnreadPosts(List<Posts> unreadPosts) {
        this.unreadPosts = unreadPosts;
    }
}
