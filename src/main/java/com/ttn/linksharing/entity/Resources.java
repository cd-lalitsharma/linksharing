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
@Table(name = "resources")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resources.findAll", query = "SELECT r FROM Resources r"),
    @NamedQuery(name = "Resources.findById", query = "SELECT r FROM Resources r WHERE r.id = :id"),
    @NamedQuery(name = "Resources.findByLocation", query = "SELECT r FROM Resources r WHERE r.location = :location"),
    @NamedQuery(name = "Resources.findByType", query = "SELECT r FROM Resources r WHERE r.type = :type")})
public class Resources implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "location")
    private String location;
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resources")
    private Set<Posts> posts;

    public Resources() {
    }

    public Resources(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
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
        if (!(object instanceof Resources)) {
            return false;
        }
        Resources other = (Resources) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ttn.linksharing.entity.Resources[ id=" + id + " ]";
    }
    
}
