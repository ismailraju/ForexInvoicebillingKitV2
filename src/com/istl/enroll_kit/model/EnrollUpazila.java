/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Arun
 */
@Entity
@Table(name = "enroll_upazila")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnrollUpazila.findAll", query = "SELECT e FROM EnrollUpazila e")
    , @NamedQuery(name = "EnrollUpazila.findById", query = "SELECT e FROM EnrollUpazila e WHERE e.id = :id")
    , @NamedQuery(name = "EnrollUpazila.findByCode", query = "SELECT e FROM EnrollUpazila e WHERE e.code = :code")
    , @NamedQuery(name = "EnrollUpazila.findByNameLocal", query = "SELECT e FROM EnrollUpazila e WHERE e.nameLocal = :nameLocal")
    , @NamedQuery(name = "EnrollUpazila.findByNameEn", query = "SELECT e FROM EnrollUpazila e WHERE e.nameEn = :nameEn")
    , @NamedQuery(name = "EnrollUpazila.findBySyncFlag", query = "SELECT e FROM EnrollUpazila e WHERE e.syncFlag = :syncFlag")
    , @NamedQuery(name = "EnrollUpazila.findByStatus", query = "SELECT e FROM EnrollUpazila e WHERE e.status = :status")
    , @NamedQuery(name = "EnrollUpazila.findByVersionId", query = "SELECT e FROM EnrollUpazila e WHERE e.versionId = :versionId")
    , @NamedQuery(name = "EnrollUpazila.findByCreationDate", query = "SELECT e FROM EnrollUpazila e WHERE e.creationDate = :creationDate")
    , @NamedQuery(name = "EnrollUpazila.findByCreatedBy", query = "SELECT e FROM EnrollUpazila e WHERE e.createdBy = :createdBy")
    , @NamedQuery(name = "EnrollUpazila.findByLastUpdateDate", query = "SELECT e FROM EnrollUpazila e WHERE e.lastUpdateDate = :lastUpdateDate")
    , @NamedQuery(name = "EnrollUpazila.findByLastUpdatedBy", query = "SELECT e FROM EnrollUpazila e WHERE e.lastUpdatedBy = :lastUpdatedBy")})
public class EnrollUpazila implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @Column(name = "code")
    private short code;
    @Basic(optional = false)
    @Column(name = "name_local")
    private String nameLocal;
    @Basic(optional = false)
    @Column(name = "name_en")
    private String nameEn;
    @Basic(optional = false)
    @Column(name = "sync_flag")
    private short syncFlag;
    @Basic(optional = false)
    @Column(name = "status")
    private short status;
    @Basic(optional = false)
    @Column(name = "version_id")
    private int versionId;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "created_by")
    private int createdBy;
    @Basic(optional = false)
    @Column(name = "last_update_date")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @Column(name = "last_updated_by")
    private int lastUpdatedBy;
    @OneToMany(mappedBy = "permanentUpazilaId")
    private Collection<EnrollPerson> enrollPersonCollection;
    @JoinColumn(name = "district_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private EnrollDistrict districtCode;
    @JoinColumn(name = "division_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private EnrollDivision divisionCode;

    public EnrollUpazila() {
    }

    public EnrollUpazila(Short id) {
        this.id = id;
    }

    public EnrollUpazila(Short id, short code, String nameLocal, String nameEn, short syncFlag, short status, int versionId, Date creationDate, int createdBy, Date lastUpdateDate, int lastUpdatedBy) {
        this.id = id;
        this.code = code;
        this.nameLocal = nameLocal;
        this.nameEn = nameEn;
        this.syncFlag = syncFlag;
        this.status = status;
        this.versionId = versionId;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public String getNameLocal() {
        return nameLocal;
    }

    public void setNameLocal(String nameLocal) {
        this.nameLocal = nameLocal;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public short getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(short syncFlag) {
        this.syncFlag = syncFlag;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(int lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @XmlTransient
    public Collection<EnrollPerson> getEnrollPersonCollection() {
        return enrollPersonCollection;
    }

    public void setEnrollPersonCollection(Collection<EnrollPerson> enrollPersonCollection) {
        this.enrollPersonCollection = enrollPersonCollection;
    }

    public EnrollDistrict getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(EnrollDistrict districtCode) {
        this.districtCode = districtCode;
    }

    public EnrollDivision getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(EnrollDivision divisionCode) {
        this.divisionCode = divisionCode;
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
        if (!(object instanceof EnrollUpazila)) {
            return false;
        }
        EnrollUpazila other = (EnrollUpazila) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nameEn;
    }
    
}
