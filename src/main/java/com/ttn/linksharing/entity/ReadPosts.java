/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttn.linksharing.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lalit
 */
@Entity
@Table(name = "read_posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReadPosts.findAll", query = "SELECT r FROM ReadPosts r"),
    @NamedQuery(name = "ReadPosts.findById", query = "SELECT r FROM ReadPosts r WHERE r.id = :id"),
    @NamedQuery(name = "ReadPosts.findByPostsResourcesId", query = "SELECT r FROM ReadPosts r WHERE r.postsResourcesId = :postsResourcesId"),
    @NamedQuery(name = "ReadPosts.findByIsRead", query = "SELECT r FROM ReadPosts r WHERE r.isRead = :isRead"),
    @NamedQuery(name = "ReadPosts.findByReadDate", query = "SELECT r FROM ReadPosts r WHERE r.readDate = :readDate")})
public class ReadPosts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "posts_resources_id")
    private int postsResourcesId;
    @Basic(optional = false)
    @Column(name = "is_read")
    private String isRead;
    @Column(name = "read_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date readDate;
    @JoinColumn(name = "posts_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Posts post;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    public ReadPosts() {
    }

    public ReadPosts(Integer id) {
        this.id = id;
    }

    public ReadPosts(Integer id, int postsResourcesId, String isRead) {
        this.id = id;
        this.postsResourcesId = postsResourcesId;
        this.isRead = isRead;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPostsResourcesId() {
        return postsResourcesId;
    }

    public void setPostsResourcesId(int postsResourcesId) {
        this.postsResourcesId = postsResourcesId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    public Posts getpost() {
        return post;
    }

    public void setpost(Posts post) {
        this.post = post;
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
        if (!(object instanceof ReadPosts)) {
            return false;
        }
        ReadPosts other = (ReadPosts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ttn.linksharing.entity.ReadPosts[ id=" + id + " ]";
    }
    
}
