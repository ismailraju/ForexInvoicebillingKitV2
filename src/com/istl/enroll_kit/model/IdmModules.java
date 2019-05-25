/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Arun
 */
@Entity
@Table(name = "idm_modules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdmModules.findAll", query = "SELECT i FROM IdmModules i")
    , @NamedQuery(name = "IdmModules.findById", query = "SELECT i FROM IdmModules i WHERE i.id = :id")
    , @NamedQuery(name = "IdmModules.findByModuleName", query = "SELECT i FROM IdmModules i WHERE i.moduleName = :moduleName")
    , @NamedQuery(name = "IdmModules.findByStatus", query = "SELECT i FROM IdmModules i WHERE i.status = :status")})
public class IdmModules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "module_name")
    private String moduleName;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moduleId")
    private Collection<IdmResources> idmResourcesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moduleId")
    private Collection<IdmRoleModules> idmRoleModulesCollection;

    public IdmModules() {
    }

    public IdmModules(Integer id) {
        this.id = id;
    }

    public IdmModules(Integer id, String moduleName, int status) {
        this.id = id;
        this.moduleName = moduleName;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<IdmResources> getIdmResourcesCollection() {
        return idmResourcesCollection;
    }

    public void setIdmResourcesCollection(Collection<IdmResources> idmResourcesCollection) {
        this.idmResourcesCollection = idmResourcesCollection;
    }

    @XmlTransient
    public Collection<IdmRoleModules> getIdmRoleModulesCollection() {
        return idmRoleModulesCollection;
    }

    public void setIdmRoleModulesCollection(Collection<IdmRoleModules> idmRoleModulesCollection) {
        this.idmRoleModulesCollection = idmRoleModulesCollection;
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
        if (!(object instanceof IdmModules)) {
            return false;
        }
        IdmModules other = (IdmModules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.istl.enroll_kit.model.IdmModules[ id=" + id + " ]";
    }
    
}
