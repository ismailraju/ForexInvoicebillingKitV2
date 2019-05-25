/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.accesscontrol.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;

public class EnrollUnion implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private short code;
    private short upazilaCode;
    private String nameLocal;
    private String nameEn;
    private short syncFlag;
    private short status;
    private int versionId;
    private Date creationDate;
    private int createdBy;
    private Date lastUpdateDate;
    private int lastUpdatedBy;
    private EnrollDistrict districtCode;
    private EnrollDivision divisionCode;
    private Collection<Person> enrollPersonCollection;

    public EnrollUnion() {
    }

    public EnrollUnion(Integer id) {
        this.id = id;
    }

    public EnrollUnion(Integer id, short code, short upazilaCode, String nameLocal, String nameEn, short syncFlag, short status, int versionId, Date creationDate, int createdBy, Date lastUpdateDate, int lastUpdatedBy) {
        this.id = id;
        this.code = code;
        this.upazilaCode = upazilaCode;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public short getUpazilaCode() {
        return upazilaCode;
    }

    public void setUpazilaCode(short upazilaCode) {
        this.upazilaCode = upazilaCode;
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

   
    public Collection<Person> getEnrollPersonCollection() {
        return enrollPersonCollection;
    }

    public void setEnrollPersonCollection(Collection<Person> enrollPersonCollection) {
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
        if (!(object instanceof EnrollUnion)) {
            return false;
        }
        EnrollUnion other = (EnrollUnion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EnrollUnion{" + "id=" + id + ", code=" + code + ", upazilaCode=" + upazilaCode + ", nameLocal=" + nameLocal + ", nameEn=" + nameEn + ", syncFlag=" + syncFlag + ", status=" + status + ", versionId=" + versionId + ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdatedBy=" + lastUpdatedBy + ", enrollPersonCollection=" + enrollPersonCollection + ", districtCode=" + districtCode + ", divisionCode=" + divisionCode + '}';
    }

 

}
