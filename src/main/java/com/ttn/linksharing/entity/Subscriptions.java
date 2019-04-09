/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttn.linksharing.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lalit
 */
@Entity
@Table(name = "subscriptions")
public class Subscriptions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "topic_seriousness")
    private String topicSeriousness;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Topics topic;

    public Subscriptions() {
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTopicSeriousness() {
        return topicSeriousness;
    }
    
    public void setTopicSeriousness(String topicSeriousness) {
        this.topicSeriousness = topicSeriousness;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Topics getTopic() {
        return topic;
    }
    
    public void setTopic(Topics topic) {
        this.topic = topic;
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
        if (!(object instanceof Subscriptions)) {
            return false;
        }
        Subscriptions other = (Subscriptions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ttn.linksharing.entity.Subscriptions[ id=" + id + " ]";
    }
    
}
