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
import javax.persistence.CascadeType;
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
@Table(name = "enroll_district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnrollDistrict.findAll", query = "SELECT e FROM EnrollDistrict e")
    , @NamedQuery(name = "EnrollDistrict.findById", query = "SELECT e FROM EnrollDistrict e WHERE e.id = :id")
    , @NamedQuery(name = "EnrollDistrict.findByCode", query = "SELECT e FROM EnrollDistrict e WHERE e.code = :code")
    , @NamedQuery(name = "EnrollDistrict.findByNameLocal", query = "SELECT e FROM EnrollDistrict e WHERE e.nameLocal = :nameLocal")
    , @NamedQuery(name = "EnrollDistrict.findByNameEn", query = "SELECT e FROM EnrollDistrict e WHERE e.nameEn = :nameEn")
    , @NamedQuery(name = "EnrollDistrict.findBySyncFlag", query = "SELECT e FROM EnrollDistrict e WHERE e.syncFlag = :syncFlag")
    , @NamedQuery(name = "EnrollDistrict.findByStatus", query = "SELECT e FROM EnrollDistrict e WHERE e.status = :status")
    , @NamedQuery(name = "EnrollDistrict.findByVersionId", query = "SELECT e FROM EnrollDistrict e WHERE e.versionId = :versionId")
    , @NamedQuery(name = "EnrollDistrict.findByCreationDate", query = "SELECT e FROM EnrollDistrict e WHERE e.creationDate = :creationDate")
    , @NamedQuery(name = "EnrollDistrict.findByCreatedBy", query = "SELECT e FROM EnrollDistrict e WHERE e.createdBy = :createdBy")
    , @NamedQuery(name = "EnrollDistrict.findByLastUpdateDate", query = "SELECT e FROM EnrollDistrict e WHERE e.lastUpdateDate = :lastUpdateDate")
    , @NamedQuery(name = "EnrollDistrict.findByLastUpdatedBy", query = "SELECT e FROM EnrollDistrict e WHERE e.lastUpdatedBy = :lastUpdatedBy")})
public class EnrollDistrict implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placeOfBirth")
    private Collection<EnrollPerson> enrollPersonCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permanentDistrictId")
    private Collection<EnrollPerson> enrollPersonCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "districtCode")
    private Collection<EnrollUnion> enrollUnionCollection;
    @OneToMany(mappedBy = "districtId")
    private Collection<EnrollStation> enrollStationCollection;
    @JoinColumn(name = "division_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private EnrollDivision divisionCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "districtCode")
    private Collection<EnrollUpazila> enrollUpazilaCollection;

    public EnrollDistrict() {
    }

    public EnrollDistrict(Short id) {
        this.id = id;
    }

    public EnrollDistrict(Short id, short code, String nameLocal, String nameEn, short syncFlag, short status, int versionId, Date creationDate, int createdBy, Date lastUpdateDate, int lastUpdatedBy) {
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

    @XmlTransient
    public Collection<EnrollPerson> getEnrollPersonCollection1() {
        return enrollPersonCollection1;
    }

    public void setEnrollPersonCollection1(Collection<EnrollPerson> enrollPersonCollection1) {
        this.enrollPersonCollection1 = enrollPersonCollection1;
    }

    @XmlTransient
    public Collection<EnrollUnion> getEnrollUnionCollection() {
        return enrollUnionCollection;
    }

    public void setEnrollUnionCollection(Collection<EnrollUnion> enrollUnionCollection) {
        this.enrollUnionCollection = enrollUnionCollection;
    }

    @XmlTransient
    public Collection<EnrollStation> getEnrollStationCollection() {
        return enrollStationCollection;
    }

    public void setEnrollStationCollection(Collection<EnrollStation> enrollStationCollection) {
        this.enrollStationCollection = enrollStationCollection;
    }

    public EnrollDivision getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(EnrollDivision divisionCode) {
        this.divisionCode = divisionCode;
    }

    @XmlTransient
    public Collection<EnrollUpazila> getEnrollUpazilaCollection() {
        return enrollUpazilaCollection;
    }

    public void setEnrollUpazilaCollection(Collection<EnrollUpazila> enrollUpazilaCollection) {
        this.enrollUpazilaCollection = enrollUpazilaCollection;
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
        if (!(object instanceof EnrollDistrict)) {
            return false;
        }
        EnrollDistrict other = (EnrollDistrict) object;
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
