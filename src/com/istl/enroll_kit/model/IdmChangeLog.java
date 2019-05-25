/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.model;

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
 * @author Arun
 */
@Entity
@Table(name = "idm_change_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdmChangeLog.findAll", query = "SELECT i FROM IdmChangeLog i")
    , @NamedQuery(name = "IdmChangeLog.findById", query = "SELECT i FROM IdmChangeLog i WHERE i.id = :id")
    , @NamedQuery(name = "IdmChangeLog.findByRecordId", query = "SELECT i FROM IdmChangeLog i WHERE i.recordId = :recordId")
    , @NamedQuery(name = "IdmChangeLog.findByOldValue", query = "SELECT i FROM IdmChangeLog i WHERE i.oldValue = :oldValue")
    , @NamedQuery(name = "IdmChangeLog.findByNewValue", query = "SELECT i FROM IdmChangeLog i WHERE i.newValue = :newValue")
    , @NamedQuery(name = "IdmChangeLog.findByCreationDate", query = "SELECT i FROM IdmChangeLog i WHERE i.creationDate = :creationDate")})
public class IdmChangeLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "record_id")
    private int recordId;
    @Column(name = "old_value")
    private String oldValue;
    @Column(name = "new_value")
    private String newValue;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IdmUser createdBy;

    public IdmChangeLog() {
    }

    public IdmChangeLog(Integer id) {
        this.id = id;
    }

    public IdmChangeLog(Integer id, int recordId, Date creationDate) {
        this.id = id;
        this.recordId = recordId;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public IdmUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(IdmUser createdBy) {
        this.createdBy = createdBy;
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
        if (!(object instanceof IdmChangeLog)) {
            return false;
        }
        IdmChangeLog other = (IdmChangeLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.istl.enroll_kit.model.IdmChangeLog[ id=" + id + " ]";
    }
    
}
