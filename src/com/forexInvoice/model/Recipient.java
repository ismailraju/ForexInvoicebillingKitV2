/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User
 */
@Entity
@Table(name = "recipient")
@NamedQueries({
    @NamedQuery(name = "Recipient.findAll", query = "SELECT r FROM Recipient r"),
    @NamedQuery(name = "Recipient.findById", query = "SELECT r FROM Recipient r WHERE r.id = :id"),
    @NamedQuery(name = "Recipient.findByFulName", query = "SELECT r FROM Recipient r WHERE r.fulName = :fulName"),
    @NamedQuery(name = "Recipient.findByDob", query = "SELECT r FROM Recipient r WHERE r.dob = :dob"),
    @NamedQuery(name = "Recipient.findByTelephone", query = "SELECT r FROM Recipient r WHERE r.telephone = :telephone"),
    @NamedQuery(name = "Recipient.findByAddress", query = "SELECT r FROM Recipient r WHERE r.address = :address"),
    @NamedQuery(name = "Recipient.findByIdNumber", query = "SELECT r FROM Recipient r WHERE r.idNumber = :idNumber"),
    @NamedQuery(name = "Recipient.findByReceivedMethod", query = "SELECT r FROM Recipient r WHERE r.receivedMethod = :receivedMethod")})
public class Recipient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ful_name")
    private String fulName;
    @Basic(optional = false)
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @Column(name = "telephone")
    private String telephone;
    @Basic(optional = false)
    @Column(name = "Address")
    private String address;
    @Column(name = "id_number")
    private String idNumber;
    @Basic(optional = false)
    @Column(name = "received_method")
    private String receivedMethod;
    @JoinColumn(name = "bank", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bank bank;

    public Recipient() {
    }

    public Recipient(Integer id) {
        this.id = id;
    }

    public Recipient(Integer id, String fulName, Date dob, String telephone, String address, String receivedMethod) {
        this.id = id;
        this.fulName = fulName;
        this.dob = dob;
        this.telephone = telephone;
        this.address = address;
        this.receivedMethod = receivedMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFulName() {
        return fulName;
    }

    public void setFulName(String fulName) {
        this.fulName = fulName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getReceivedMethod() {
        return receivedMethod;
    }

    public void setReceivedMethod(String receivedMethod) {
        this.receivedMethod = receivedMethod;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
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
        if (!(object instanceof Recipient)) {
            return false;
        }
        Recipient other = (Recipient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.forexInvoice.model.Recipient[ id=" + id + " ]";
    }
    
}
