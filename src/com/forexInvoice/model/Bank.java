/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "bank")
@NamedQueries({
    @NamedQuery(name = "Bank.findAll", query = "SELECT b FROM Bank b"),
    @NamedQuery(name = "Bank.findById", query = "SELECT b FROM Bank b WHERE b.id = :id"),
    @NamedQuery(name = "Bank.findByName", query = "SELECT b FROM Bank b WHERE b.name = :name"),
    @NamedQuery(name = "Bank.findByBranchName", query = "SELECT b FROM Bank b WHERE b.branchName = :branchName"),
    @NamedQuery(name = "Bank.findByIfscCode", query = "SELECT b FROM Bank b WHERE b.ifscCode = :ifscCode")})
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "ifsc_code")
    private String ifscCode;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bank")
//    private List<Recipient> recipientList;

    public Bank() {
    }

    public Bank(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

//    public List<Recipient> getRecipientList() {
//        return recipientList;
//    }
//
//    public void setRecipientList(List<Recipient> recipientList) {
//        this.recipientList = recipientList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bank)) {
            return false;
        }
        Bank other = (Bank) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "com.forexInvoice.model.Bank[ id=" + id + " ]";
        return name+"->"+branchName;
    }
//    @Override
//    public String toString() {
//        return "com.forexInvoice.model.Bank{" + "id=" + id + ", name=" + name + ", branchName=" + branchName + ", ifscCode=" + ifscCode  + '}';
//    }

}
