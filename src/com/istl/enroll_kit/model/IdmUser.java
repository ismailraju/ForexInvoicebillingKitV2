/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Arun
 */
@Entity
@Table(name = "idm_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdmUser.findAll", query = "SELECT i FROM IdmUser i")
    , @NamedQuery(name = "IdmUser.findById", query = "SELECT i FROM IdmUser i WHERE i.id = :id")
    , @NamedQuery(name = "IdmUser.findByFirstName", query = "SELECT i FROM IdmUser i WHERE i.firstName = :firstName")
    , @NamedQuery(name = "IdmUser.findByLastName", query = "SELECT i FROM IdmUser i WHERE i.lastName = :lastName")
    , @NamedQuery(name = "IdmUser.findByUserName", query = "SELECT i FROM IdmUser i WHERE i.userName = :userName")
    , @NamedQuery(name = "IdmUser.findByPassword", query = "SELECT i FROM IdmUser i WHERE i.password = :password")
    , @NamedQuery(name = "IdmUser.findByEmail", query = "SELECT i FROM IdmUser i WHERE i.email = :email")
    , @NamedQuery(name = "IdmUser.findByPhone", query = "SELECT i FROM IdmUser i WHERE i.phone = :phone")
    , @NamedQuery(name = "IdmUser.findByOfficeId", query = "SELECT i FROM IdmUser i WHERE i.officeId = :officeId")
    , @NamedQuery(name = "IdmUser.findByGender", query = "SELECT i FROM IdmUser i WHERE i.gender = :gender")
    , @NamedQuery(name = "IdmUser.findByStatus", query = "SELECT i FROM IdmUser i WHERE i.status = :status")
    , @NamedQuery(name = "IdmUser.findByDateOfBirth", query = "SELECT i FROM IdmUser i WHERE i.dateOfBirth = :dateOfBirth")})
public class IdmUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "phone")
    private String phone;
    @Column(name = "office_id")
    private Integer officeId;
    @Basic(optional = false)
    @Column(name = "gender")
    private int gender;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<IdmUserRole> idmUserRoleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<IdmChangeLog> idmChangeLogCollection;

    public IdmUser() {
    }

    public IdmUser(Integer id) {
        this.id = id;
    }

    public IdmUser(Integer id, String firstName, String userName, String password, String email, String phone, int gender, int status, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.status = status;
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @XmlTransient
    public Collection<IdmUserRole> getIdmUserRoleCollection() {
        return idmUserRoleCollection;
    }

    public void setIdmUserRoleCollection(Collection<IdmUserRole> idmUserRoleCollection) {
        this.idmUserRoleCollection = idmUserRoleCollection;
    }

    @XmlTransient
    public Collection<IdmChangeLog> getIdmChangeLogCollection() {
        return idmChangeLogCollection;
    }

    public void setIdmChangeLogCollection(Collection<IdmChangeLog> idmChangeLogCollection) {
        this.idmChangeLogCollection = idmChangeLogCollection;
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
        if (!(object instanceof IdmUser)) {
            return false;
        }
        IdmUser other = (IdmUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.istl.enroll_kit.model.IdmUser[ id=" + id + " ]";
    }
    
}
