/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Arun
 */
@Entity
@Table(name = "enroll_lookup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnrollLookup.findAll", query = "SELECT e FROM EnrollLookup e")
    , @NamedQuery(name = "EnrollLookup.findById", query = "SELECT e FROM EnrollLookup e WHERE e.id = :id")
    , @NamedQuery(name = "EnrollLookup.findByLookupType", query = "SELECT e FROM EnrollLookup e WHERE e.lookupType = :lookupType")
    , @NamedQuery(name = "EnrollLookup.findByCode", query = "SELECT e FROM EnrollLookup e WHERE e.code = :code")
    , @NamedQuery(name = "EnrollLookup.findByNameEn", query = "SELECT e FROM EnrollLookup e WHERE e.nameEn = :nameEn")
    , @NamedQuery(name = "EnrollLookup.findByNameLocal", query = "SELECT e FROM EnrollLookup e WHERE e.nameLocal = :nameLocal")
    , @NamedQuery(name = "EnrollLookup.findByStatus", query = "SELECT e FROM EnrollLookup e WHERE e.status = :status")})
public class EnrollLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @Column(name = "lookup_type")
    private String lookupType;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "name_local")
    private String nameLocal;
    @Basic(optional = false)
    @Column(name = "status")
    private short status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    private Collection<EnrollPerson> enrollPersonCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationStatus")
    private Collection<EnrollPerson> enrollPersonCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "afisMatchStatus")
    private Collection<EnrollPerson> enrollPersonCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "textMatchStatus")
    private Collection<EnrollPerson> enrollPersonCollection3;

    public EnrollLookup() {
    }

    public EnrollLookup(Short id) {
        this.id = id;
    }

    public EnrollLookup(Short id, String lookupType, String code, String nameEn, short status) {
        this.id = id;
        this.lookupType = lookupType;
        this.code = code;
        this.nameEn = nameEn;
        this.status = status;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getLookupType() {
        return lookupType;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameLocal() {
        return nameLocal;
    }

    public void setNameLocal(String nameLocal) {
        this.nameLocal = nameLocal;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
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
    public Collection<EnrollPerson> getEnrollPersonCollection2() {
        return enrollPersonCollection2;
    }

    public void setEnrollPersonCollection2(Collection<EnrollPerson> enrollPersonCollection2) {
        this.enrollPersonCollection2 = enrollPersonCollection2;
    }

    @XmlTransient
    public Collection<EnrollPerson> getEnrollPersonCollection3() {
        return enrollPersonCollection3;
    }

    public void setEnrollPersonCollection3(Collection<EnrollPerson> enrollPersonCollection3) {
        this.enrollPersonCollection3 = enrollPersonCollection3;
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
        if (!(object instanceof EnrollLookup)) {
            return false;
        }
        EnrollLookup other = (EnrollLookup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.istl.enroll_kit.model.EnrollLookup[ id=" + id + " ]";
    }
    
}
