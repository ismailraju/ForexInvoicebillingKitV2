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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arun
 */
@Entity
@Table(name = "enroll_person_biometric")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnrollPersonBiometric.findAll", query = "SELECT e FROM EnrollPersonBiometric e")
    , @NamedQuery(name = "EnrollPersonBiometric.findById", query = "SELECT e FROM EnrollPersonBiometric e WHERE e.id = :id")})
public class EnrollPersonBiometric implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Lob
    @Column(name = "signature")
    private byte[] signature;
    @Lob
    @Column(name = "wsq_rt")
    private byte[] wsqRt;
    @Lob
    @Column(name = "wsq_ri")
    private byte[] wsqRi;
    @Lob
    @Column(name = "wsq_rm")
    private byte[] wsqRm;
    @Lob
    @Column(name = "wsq_rr")
    private byte[] wsqRr;
    @Lob
    @Column(name = "wsq_rs")
    private byte[] wsqRs;
    @Lob
    @Column(name = "wsq_lt")
    private byte[] wsqLt;
    @Lob
    @Column(name = "wsq_li")
    private byte[] wsqLi;
    @Lob
    @Column(name = "wsq_lm")
    private byte[] wsqLm;
    @Lob
    @Column(name = "wsq_lr")
    private byte[] wsqLr;
    @Lob
    @Column(name = "wsq_ls")
    private byte[] wsqLs;
    @Lob
    @Column(name = "iris_left")
    private byte[] irisLeft;
    @Lob
    @Column(name = "iris_right")
    private byte[] irisRight;
    @Lob
    @Column(name = "iris_info")
    private byte[] irisInfo;
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ManyToOne
    private EnrollPerson personId;

    public EnrollPersonBiometric() {
    }

    public EnrollPersonBiometric(Integer id) {
        this.id = id;
    }

    public EnrollPersonBiometric(Integer id, byte[] photo) {
        this.id = id;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public byte[] getWsqRt() {
        return wsqRt;
    }

    public void setWsqRt(byte[] wsqRt) {
        this.wsqRt = wsqRt;
    }

    public byte[] getWsqRi() {
        return wsqRi;
    }

    public void setWsqRi(byte[] wsqRi) {
        this.wsqRi = wsqRi;
    }

    public byte[] getWsqRm() {
        return wsqRm;
    }

    public void setWsqRm(byte[] wsqRm) {
        this.wsqRm = wsqRm;
    }

    public byte[] getWsqRr() {
        return wsqRr;
    }

    public void setWsqRr(byte[] wsqRr) {
        this.wsqRr = wsqRr;
    }

    public byte[] getWsqRs() {
        return wsqRs;
    }

    public void setWsqRs(byte[] wsqRs) {
        this.wsqRs = wsqRs;
    }

    public byte[] getWsqLt() {
        return wsqLt;
    }

    public void setWsqLt(byte[] wsqLt) {
        this.wsqLt = wsqLt;
    }

    public byte[] getWsqLi() {
        return wsqLi;
    }

    public void setWsqLi(byte[] wsqLi) {
        this.wsqLi = wsqLi;
    }

    public byte[] getWsqLm() {
        return wsqLm;
    }

    public void setWsqLm(byte[] wsqLm) {
        this.wsqLm = wsqLm;
    }

    public byte[] getWsqLr() {
        return wsqLr;
    }

    public void setWsqLr(byte[] wsqLr) {
        this.wsqLr = wsqLr;
    }

    public byte[] getWsqLs() {
        return wsqLs;
    }

    public void setWsqLs(byte[] wsqLs) {
        this.wsqLs = wsqLs;
    }

    public byte[] getIrisLeft() {
        return irisLeft;
    }

    public void setIrisLeft(byte[] irisLeft) {
        this.irisLeft = irisLeft;
    }

    public byte[] getIrisRight() {
        return irisRight;
    }

    public void setIrisRight(byte[] irisRight) {
        this.irisRight = irisRight;
    }

    public byte[] getIrisInfo() {
        return irisInfo;
    }

    public void setIrisInfo(byte[] irisInfo) {
        this.irisInfo = irisInfo;
    }

    public EnrollPerson getPersonId() {
        return personId;
    }

    public void setPersonId(EnrollPerson personId) {
        this.personId = personId;
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
        if (!(object instanceof EnrollPersonBiometric)) {
            return false;
        }
        EnrollPersonBiometric other = (EnrollPersonBiometric) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.istl.enroll_kit.model.EnrollPersonBiometric[ id=" + id + " ]";
    }
    
}
