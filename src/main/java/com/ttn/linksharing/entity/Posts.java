/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttn.linksharing.entity;

import java.io.Serializable;
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
    private Set<Ratings> ratingsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<ReadPosts> readPostsSet;
    @JoinColumn(name = "resources_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Resources resourcesId;
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Topics topic;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
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
    public Set<Ratings> getRatingsSet() {
        return ratingsSet;
    }

    public void setRatingsSet(Set<Ratings> ratingsSet) {
        this.ratingsSet = ratingsSet;
    }

    @XmlTransient
    public Set<ReadPosts> getReadPostsSet() {
        return readPostsSet;
    }

    public void setReadPostsSet(Set<ReadPosts> readPostsSet) {
        this.readPostsSet = readPostsSet;
    }

    public Resources getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Resources resourcesId) {
        this.resourcesId = resourcesId;
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
