package com.istl.accesscontrol.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;

public class EnrollDivision implements Serializable {

    private static final long serialVersionUID = 1L;
    private Short id;
    private short code;
    private String nameLocal;
    private String nameEn;
    private short syncFlag;
    private short status;
    private int versionId;
    private Date creationDate;
    private int createdBy;
    private Date lastUpdateDate;
    private int lastUpdatedBy;
//    private Collection<Person> enrollPersonCollection;
//    private Collection<EnrollUnion> enrollUnionCollection;
//    private Collection<EnrollDistrict> enrollDistrictCollection;
//    private Collection<EnrollUpazila> enrollUpazilaCollection;

    public EnrollDivision() {
    }

    public EnrollDivision(Short id) {
        this.id = id;
    }

    public EnrollDivision(Short id, short code, String nameLocal, String nameEn, short syncFlag, short status, int versionId, Date creationDate, int createdBy, Date lastUpdateDate, int lastUpdatedBy) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnrollDivision)) {
            return false;
        }
        EnrollDivision other = (EnrollDivision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EnrollDivision{" + "id=" + id + ", code=" + code + ", nameLocal=" + nameLocal + ", nameEn=" + nameEn + ", syncFlag=" + syncFlag + ", status=" + status + ", versionId=" + versionId + ", creationDate=" + creationDate + ", createdBy=" + createdBy + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdatedBy=" + lastUpdatedBy + '}';
    }

}
