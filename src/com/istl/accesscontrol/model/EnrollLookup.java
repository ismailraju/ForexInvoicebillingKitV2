/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.accesscontrol.model;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

public class EnrollLookup implements Serializable {

    private static final long serialVersionUID = 1L;
    private Short id;
    private String lookupType;
    private String code;
    private String nameEn;
    private String nameLocal;
    private short status;

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
        return "EnrollLookup{" + "id=" + id + ", lookupType=" + lookupType + ", code=" + code + ", nameEn=" + nameEn + ", nameLocal=" + nameLocal + ", status=" + status + '}';
    }

}
