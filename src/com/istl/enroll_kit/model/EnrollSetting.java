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
import javax.persistence.Id;
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
@Table(name = "enroll_setting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnrollSetting.findAll", query = "SELECT e FROM EnrollSetting e")
    , @NamedQuery(name = "EnrollSetting.findById", query = "SELECT e FROM EnrollSetting e WHERE e.id = :id")
    , @NamedQuery(name = "EnrollSetting.findByName", query = "SELECT e FROM EnrollSetting e WHERE e.name = :name")
    , @NamedQuery(name = "EnrollSetting.findByDisplayNameEn", query = "SELECT e FROM EnrollSetting e WHERE e.displayNameEn = :displayNameEn")
    , @NamedQuery(name = "EnrollSetting.findByDisplayNameLocal", query = "SELECT e FROM EnrollSetting e WHERE e.displayNameLocal = :displayNameLocal")
    , @NamedQuery(name = "EnrollSetting.findByValue", query = "SELECT e FROM EnrollSetting e WHERE e.value = :value")
    , @NamedQuery(name = "EnrollSetting.findByCreatedBy", query = "SELECT e FROM EnrollSetting e WHERE e.createdBy = :createdBy")
    , @NamedQuery(name = "EnrollSetting.findByCreationDate", query = "SELECT e FROM EnrollSetting e WHERE e.creationDate = :creationDate")
    , @NamedQuery(name = "EnrollSetting.findByLastUpdatedBy", query = "SELECT e FROM EnrollSetting e WHERE e.lastUpdatedBy = :lastUpdatedBy")
    , @NamedQuery(name = "EnrollSetting.findByLastUpdateDate", query = "SELECT e FROM EnrollSetting e WHERE e.lastUpdateDate = :lastUpdateDate")
    , @NamedQuery(name = "EnrollSetting.findByStatus", query = "SELECT e FROM EnrollSetting e WHERE e.status = :status")
    , @NamedQuery(name = "EnrollSetting.findByVersion", query = "SELECT e FROM EnrollSetting e WHERE e.version = :version")})
public class EnrollSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "display_name_en")
    private String displayNameEn;
    @Column(name = "display_name_local")
    private String displayNameLocal;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "created_by")
    private int createdBy;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "last_updated_by")
    private int lastUpdatedBy;
    @Basic(optional = false)
    @Column(name = "last_update_date")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @Column(name = "version")
    private int version;

    public EnrollSetting() {
    }

    public EnrollSetting(Integer id) {
        this.id = id;
    }

    public EnrollSetting(Integer id, String name, String value, int createdBy, Date creationDate, int lastUpdatedBy, Date lastUpdateDate, int status, int version) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdateDate = lastUpdateDate;
        this.status = status;
        this.version = version;
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

    public String getDisplayNameEn() {
        return displayNameEn;
    }

    public void setDisplayNameEn(String displayNameEn) {
        this.displayNameEn = displayNameEn;
    }

    public String getDisplayNameLocal() {
        return displayNameLocal;
    }

    public void setDisplayNameLocal(String displayNameLocal) {
        this.displayNameLocal = displayNameLocal;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(int lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
        if (!(object instanceof EnrollSetting)) {
            return false;
        }
        EnrollSetting other = (EnrollSetting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.istl.enroll_kit.model.EnrollSetting[ id=" + id + " ]";
    }
    
}
