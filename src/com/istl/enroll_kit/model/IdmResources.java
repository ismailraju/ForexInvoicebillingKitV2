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
@Table(name = "idm_resources")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdmResources.findAll", query = "SELECT i FROM IdmResources i")
    , @NamedQuery(name = "IdmResources.findById", query = "SELECT i FROM IdmResources i WHERE i.id = :id")
    , @NamedQuery(name = "IdmResources.findByValue", query = "SELECT i FROM IdmResources i WHERE i.value = :value")
    , @NamedQuery(name = "IdmResources.findByStatus", query = "SELECT i FROM IdmResources i WHERE i.status = :status")})
public class IdmResources implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IdmModules moduleId;

    public IdmResources() {
    }

    public IdmResources(Integer id) {
        this.id = id;
    }

    public IdmResources(Integer id, String value, int status) {
        this.id = id;
        this.value = value;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public IdmModules getModuleId() {
        return moduleId;
    }

    public void setModuleId(IdmModules moduleId) {
        this.moduleId = moduleId;
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
        if (!(object instanceof IdmResources)) {
            return false;
        }
        IdmResources other = (IdmResources) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.istl.enroll_kit.model.IdmResources[ id=" + id + " ]";
    }
    
}
