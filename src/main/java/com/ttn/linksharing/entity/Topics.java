/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttn.linksharing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lalit
 */
@Entity
@Table(name = "topics")
@NamedQueries({
    @NamedQuery(name = "Topics.findAll", query = "SELECT t FROM Topics t"),
    @NamedQuery(name = "Topics.findById", query = "SELECT t FROM Topics t WHERE t.id = :id"),
    @NamedQuery(name = "Topics.findByName", query = "SELECT t FROM Topics t WHERE t.name = :name"),
    @NamedQuery(name = "Topics.findByVisibility", query = "SELECT t FROM Topics t WHERE t.visibility = :visibility")})
public class Topics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    
    @Column(name = "visibility")
    private String visibility;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private Set<Invitations> invitations;
    
    @ManyToOne(optional = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private Set<Posts> posts= new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "topic",orphanRemoval = true)
    private Set<Subscriptions> subscription= new HashSet<>();
    
    public Set<Subscriptions> getSubscription() {
        return subscription;
    }
    
    public void setSubscription(Set<Subscriptions> subscription) {
        this.subscription = subscription;
    }
    
    public Topics() {
    }

    public Topics(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
 
    public Set<Invitations> getinvitations() {
        return invitations;
    }

    public void setinvitations(Set<Invitations> invitations) {
        this.invitations = invitations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Topics)) {
            return false;
        }
        Topics other = (Topics) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ttn.linksharing.entity.Topics[ id=" + id + " ]";
    }
    
}
