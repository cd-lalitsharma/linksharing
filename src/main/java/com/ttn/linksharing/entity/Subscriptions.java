/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttn.linksharing.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lalit
 */
@Entity
@Table(name = "subscriptions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subscriptions.findAll", query = "SELECT s FROM Subscriptions s"),
    @NamedQuery(name = "Subscriptions.findById", query = "SELECT s FROM Subscriptions s WHERE s.id = :id"),
    @NamedQuery(name = "Subscriptions.findByTopicSeriousness", query = "SELECT s FROM Subscriptions s WHERE s.topicSeriousness = :topicSeriousness"),
    @NamedQuery(name = "Subscriptions.findByUserId", query = "SELECT s FROM Subscriptions s WHERE s.userId = :userId"),
    @NamedQuery(name = "Subscriptions.findByTopicsId", query = "SELECT s FROM Subscriptions s WHERE s.topicsId = :topicsId")})
public class Subscriptions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "topic_seriousness")
    private Integer topicSeriousness;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "topics_id")
    private int topicsId;

    public Subscriptions() {
    }

    public Subscriptions(Integer id) {
        this.id = id;
    }

    public Subscriptions(Integer id, int userId, int topicsId) {
        this.id = id;
        this.userId = userId;
        this.topicsId = topicsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicSeriousness() {
        return topicSeriousness;
    }

    public void setTopicSeriousness(Integer topicSeriousness) {
        this.topicSeriousness = topicSeriousness;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTopicsId() {
        return topicsId;
    }

    public void setTopicsId(int topicsId) {
        this.topicsId = topicsId;
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
