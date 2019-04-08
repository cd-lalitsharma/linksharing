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
@Table(name = "invitations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitations.findAll", query = "SELECT i FROM Invitations i"),
    @NamedQuery(name = "Invitations.findById", query = "SELECT i FROM Invitations i WHERE i.id = :id"),
    @NamedQuery(name = "Invitations.findByEmail", query = "SELECT i FROM Invitations i WHERE i.email = :email"),
    @NamedQuery(name = "Invitations.findByInvitationAccepted", query = "SELECT i FROM Invitations i WHERE i.invitationAccepted = :invitationAccepted"),
    @NamedQuery(name = "Invitations.findByInvitationDate", query = "SELECT i FROM Invitations i WHERE i.invitationDate = :invitationDate")})
public class Invitations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "invitation_accepted")
    private String invitationAccepted;
    @Column(name = "invitation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invitationDate;
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Topics topic;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    public Invitations() {
    }

    public Invitations(Integer id) {
        this.id = id;
    }

    public Invitations(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInvitationAccepted() {
        return invitationAccepted;
    }

    public void setInvitationAccepted(String invitationAccepted) {
        this.invitationAccepted = invitationAccepted;
    }

    public Date getInvitationDate() {
        return invitationDate;
    }

    public void setInvitationDate(Date invitationDate) {
        this.invitationDate = invitationDate;
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
        if (!(object instanceof Invitations)) {
            return false;
        }
        Invitations other = (Invitations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ttn.linksharing.entity.Invitations[ id=" + id + " ]";
    }
    
}
