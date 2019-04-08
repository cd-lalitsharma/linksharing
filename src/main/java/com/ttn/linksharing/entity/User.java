/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttn.linksharing.entity;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lalit
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByPhoto", query = "SELECT u FROM User u WHERE u.photo = :photo"),
    @NamedQuery(name = "User.findByFacebook", query = "SELECT u FROM User u WHERE u.facebook = :facebook"),
    @NamedQuery(name = "User.findByTwitter", query = "SELECT u FROM User u WHERE u.twitter = :twitter"),
    @NamedQuery(name = "User.findByInstagram", query = "SELECT u FROM User u WHERE u.instagram = :instagram"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
    @NamedQuery(name = "User.findByIsActive", query = "SELECT u FROM User u WHERE u.isActive = :isActive"),
    @NamedQuery(name = "User.findByCreatedDate", query = "SELECT u FROM User u WHERE u.createdDate = :createdDate")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "photo")
    private String photo;
    @Column(name = "facebook")
    private String facebook;
    @Column(name = "twitter")
    private String twitter;
    @Column(name = "instagram")
    private String instagram;
    @Column(name = "role")
    private String role;
    @Column(name = "is_active")
    private Integer isActive;
    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Invitations> invitations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Ratings> ratings;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Topics> topics;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<ReadPosts> readPosts;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Posts> posts;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
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

    
    public Set<Invitations> getinvitations() {
        return invitations;
    }

    public void setinvitations(Set<Invitations> invitations) {
        this.invitations = invitations;
    }

    
    public Set<Ratings> getratings() {
        return ratings;
    }

    public void setratings(Set<Ratings> ratings) {
        this.ratings = ratings;
    }
 
    public Set<Topics> gettopics() {
        return topics;
    }

    public void settopics(Set<Topics> topics) {
        this.topics = topics;
    }

    
    public Set<ReadPosts> getreadPosts() {
        return readPosts;
    }

    public void setreadPosts(Set<ReadPosts> readPosts) {
        this.readPosts = readPosts;
    }

    
    public Set<Posts> getposts() {
        return posts;
    }

    public void setposts(Set<Posts> posts) {
        this.posts = posts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ttn.linksharing.entity.User[ id=" + id + " ]";
    }
    
}
