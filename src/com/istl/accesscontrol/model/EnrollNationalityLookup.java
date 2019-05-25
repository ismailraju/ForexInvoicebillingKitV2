/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.accesscontrol.model;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

public class EnrollNationalityLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    private Short id;
    private String countryNameEn;
    private String countryNameLocal;
    private String countryNationality;
    private String countryIcaoCode;
    private short status;
    private Collection<Person> enrollPersonCollection;

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
    public Collection<Person> getEnrollPersonCollection() {
        return enrollPersonCollection;
    }

    public void setEnrollPersonCollection(Collection<Person> enrollPersonCollection) {
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
        return "EnrollNationalityLookup{" + "id=" + id + '}';
    }

   

}
