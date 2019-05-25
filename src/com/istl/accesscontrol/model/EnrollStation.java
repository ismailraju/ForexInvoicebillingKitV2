/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.accesscontrol.model;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

public class EnrollStation implements Serializable {

    private static final long serialVersionUID = 1L;
    private Short id;
    private String code;
    private String nameEn;
    private String nameLocal;
    private short status;
    private EnrollDistrict districtId;
    private Integer officeType;
    private Short officeId;
//    private Collection<Person> enrollPersonCollection;

    public EnrollStation() {
    }

    public EnrollStation(Short id) {
        this.id = id;
    }

    public EnrollStation(Short id, String nameEn, String nameLocal, short status) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameLocal = nameLocal;
        this.status = status;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

//    public Collection<Person> getEnrollPersonCollection() {
//        return enrollPersonCollection;
//    }
//
//    public void setEnrollPersonCollection(Collection<Person> enrollPersonCollection) {
//        this.enrollPersonCollection = enrollPersonCollection;
//    }
    public EnrollDistrict getDistrictId() {
        return districtId;
    }

    public void setDistrictId(EnrollDistrict districtId) {
        this.districtId = districtId;
    }

    public Integer getOfficeType() {
        return officeType;
    }

    public void setOfficeType(Integer officeType) {
        this.officeType = officeType;
    }

    public Short getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Short officeId) {
        this.officeId = officeId;
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
        if (!(object instanceof EnrollStation)) {
            return false;
        }
        EnrollStation other = (EnrollStation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//     
//    public String toString1() {
//        return "EnrollStation{" + "id=" + id + ", code=" + code + ", nameEn=" + nameEn + ", nameLocal=" + nameLocal + ", status=" + status + ", districtId=" + districtId + ", officeType=" + officeType + ", officeId=" + officeId + '}';
//    }
    @Override
    public String toString() {
        return "EnrollStation{" + "id=" + id + ", code=" + code + ", nameEn=" + nameEn + ", nameLocal=" + nameLocal + ", status=" + status + ", districtId=" + districtId + ", officeType=" + officeType + ", officeId=" + officeId + '}';
    }

}
