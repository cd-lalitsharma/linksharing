/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttn.linksharing.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author lalit
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p"),
        @NamedQuery(name = "Posts.findById", query = "SELECT p FROM Posts p WHERE p.id = :id"),
        @NamedQuery(name = "Posts.findByTitle", query = "SELECT p FROM Posts p WHERE p.title = :title"),
        @NamedQuery(name = "Posts.findByDescription", query = "SELECT p FROM Posts p WHERE p.description = :description")})
public class Posts implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<Ratings> ratings;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<ReadPosts> readPosts;
    @JoinColumn(name = "resources_id", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Resources resources;
    
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Topics topic;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private User user;
    
    public Posts() {
    }
    
    public Posts(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @XmlTransient
    public Set<Ratings> getratings() {
        return ratings;
    }
    
    public void setratings(Set<Ratings> ratings) {
        this.ratings = ratings;
    }
    
    @XmlTransient
    public Set<ReadPosts> getreadPosts() {
        return readPosts;
    }
    
    public void setreadPosts(Set<ReadPosts> readPosts) {
        this.readPosts = readPosts;
    }
    
    public Resources getresources() {
        return resources;
    }
    
    public void setresources(Resources resources) {
        this.resources = resources;
    }
    
    public Topics gettopic() {
        return topic;
    }
    
    public void settopic(Topics topic) {
        this.topic = topic;
    }
    
    public User getuser() {
        return user;
    }
    
    public void setuser(User user) {
        this.user = user;
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
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.ttn.linksharing.entity.Posts[ id=" + id + " ]";
    }
    
}
