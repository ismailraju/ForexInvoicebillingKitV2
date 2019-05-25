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
@Table(name = "enroll_nationality_lookup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnrollNationalityLookup.findAll", query = "SELECT e FROM EnrollNationalityLookup e")
    , @NamedQuery(name = "EnrollNationalityLookup.findById", query = "SELECT e FROM EnrollNationalityLookup e WHERE e.id = :id")
    , @NamedQuery(name = "EnrollNationalityLookup.findByCountryNameEn", query = "SELECT e FROM EnrollNationalityLookup e WHERE e.countryNameEn = :countryNameEn")
    , @NamedQuery(name = "EnrollNationalityLookup.findByCountryNameLocal", query = "SELECT e FROM EnrollNationalityLookup e WHERE e.countryNameLocal = :countryNameLocal")
    , @NamedQuery(name = "EnrollNationalityLookup.findByCountryNationality", query = "SELECT e FROM EnrollNationalityLookup e WHERE e.countryNationality = :countryNationality")
    , @NamedQuery(name = "EnrollNationalityLookup.findByCountryIcaoCode", query = "SELECT e FROM EnrollNationalityLookup e WHERE e.countryIcaoCode = :countryIcaoCode")
    , @NamedQuery(name = "EnrollNationalityLookup.findByStatus", query = "SELECT e FROM EnrollNationalityLookup e WHERE e.status = :status")})
public class EnrollNationalityLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @Column(name = "country_name_en")
    private String countryNameEn;
    @Column(name = "country_name_local")
    private String countryNameLocal;
    @Basic(optional = false)
    @Column(name = "country_nationality")
    private String countryNationality;
    @Basic(optional = false)
    @Column(name = "country_icao_code")
    private String countryIcaoCode;
    @Basic(optional = false)
    @Column(name = "status")
    private short status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nationalityId")
    private Collection<EnrollPerson> enrollPersonCollection;

    public EnrollNationalityLookup() {
    }

    public EnrollNationalityLookup(Short id) {
        this.id = id;
    }

    public EnrollNationalityLookup(Short id, String countryNameEn, String countryNationality, String countryIcaoCode, short status) {
        this.id = id;
        this.countryNameEn = countryNameEn;
        this.countryNationality = countryNationality;
        this.countryIcaoCode = countryIcaoCode;
        this.status = status;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public String getCountryNameLocal() {
        return countryNameLocal;
    }

    public void setCountryNameLocal(String countryNameLocal) {
        this.countryNameLocal = countryNameLocal;
    }

    public String getCountryNationality() {
        return countryNationality;
    }

    public void setCountryNationality(String countryNationality) {
        this.countryNationality = countryNationality;
    }

    public String getCountryIcaoCode() {
        return countryIcaoCode;
    }

    public void setCountryIcaoCode(String countryIcaoCode) {
        this.countryIcaoCode = countryIcaoCode;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnrollNationalityLookup)) {
            return false;
        }
        EnrollNationalityLookup other = (EnrollNationalityLookup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return countryNationality;
    }
    
}
