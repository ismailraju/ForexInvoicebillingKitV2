/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arun
 */
@Entity
@Table(name = "idm_access_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdmAccessLog.findAll", query = "SELECT i FROM IdmAccessLog i")
    , @NamedQuery(name = "IdmAccessLog.findById", query = "SELECT i FROM IdmAccessLog i WHERE i.id = :id")
    , @NamedQuery(name = "IdmAccessLog.findByUserName", query = "SELECT i FROM IdmAccessLog i WHERE i.userName = :userName")
    , @NamedQuery(name = "IdmAccessLog.findByModuleName", query = "SELECT i FROM IdmAccessLog i WHERE i.moduleName = :moduleName")
    , @NamedQuery(name = "IdmAccessLog.findByResource", query = "SELECT i FROM IdmAccessLog i WHERE i.resource = :resource")
    , @NamedQuery(name = "IdmAccessLog.findByRequestedTime", query = "SELECT i FROM IdmAccessLog i WHERE i.requestedTime = :requestedTime")
    , @NamedQuery(name = "IdmAccessLog.findByResponseStatus", query = "SELECT i FROM IdmAccessLog i WHERE i.responseStatus = :responseStatus")})
public class IdmAccessLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @Column(name = "module_name")
    private String moduleName;
    @Basic(optional = false)
    @Column(name = "resource")
    private String resource;
    @Basic(optional = false)
    @Column(name = "requested_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestedTime;
    @Basic(optional = false)
    @Column(name = "response_status")
    private short responseStatus;

    public IdmAccessLog() {
    }

    public IdmAccessLog(Integer id) {
        this.id = id;
    }

    public IdmAccessLog(Integer id, String userName, String moduleName, String resource, Date requestedTime, short responseStatus) {
        this.id = id;
        this.userName = userName;
        this.moduleName = moduleName;
        this.resource = resource;
        this.requestedTime = requestedTime;
        this.responseStatus = responseStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Date getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(Date requestedTime) {
        this.requestedTime = requestedTime;
    }

    public short getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(short responseStatus) {
        this.responseStatus = responseStatus;
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
        if (!(object instanceof IdmAccessLog)) {
            return false;
        }
        IdmAccessLog other = (IdmAccessLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.istl.enroll_kit.model.IdmAccessLog[ id=" + id + " ]";
    }
    
}
