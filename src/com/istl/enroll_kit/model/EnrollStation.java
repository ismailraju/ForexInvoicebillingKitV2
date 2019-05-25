/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
//@Table(name = "enroll_station", catalog = "enroll_kit_sb", schema = "")
@Table(name = "enroll_station")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnrollStation.findAll", query = "SELECT e FROM EnrollStation e")
    , @NamedQuery(name = "EnrollStation.findById", query = "SELECT e FROM EnrollStation e WHERE e.id = :id")
    , @NamedQuery(name = "EnrollStation.findByNameEn", query = "SELECT e FROM EnrollStation e WHERE e.nameEn = :nameEn")
    , @NamedQuery(name = "EnrollStation.findByNameLocal", query = "SELECT e FROM EnrollStation e WHERE e.nameLocal = :nameLocal")
    , @NamedQuery(name = "EnrollStation.findByCode", query = "SELECT e FROM EnrollStation e WHERE e.code = :code")
    , @NamedQuery(name = "EnrollStation.findByStatus", query = "SELECT e FROM EnrollStation e WHERE e.status = :status")
    , @NamedQuery(name = "EnrollStation.findByOfficeType", query = "SELECT e FROM EnrollStation e WHERE e.officeType = :officeType")
    , @NamedQuery(name = "EnrollStation.findByOfficeId", query = "SELECT e FROM EnrollStation e WHERE e.officeId = :officeId")})
public class EnrollStation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @Column(name = "name_en")
    private String nameEn;
    @Basic(optional = false)
    @Column(name = "name_local")
    private String nameLocal;
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "status")
    private short status;
    @Column(name = "office_type")
    private Integer officeType;
    @Column(name = "office_id")
    private Short officeId;
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    @ManyToOne
    private EnrollDistrict districtId;

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

    public EnrollDistrict getDistrictId() {
        return districtId;
    }

    public void setDistrictId(EnrollDistrict districtId) {
        this.districtId = districtId;
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

    @Override
    public String toString() {
        return nameEn;
    }

}
