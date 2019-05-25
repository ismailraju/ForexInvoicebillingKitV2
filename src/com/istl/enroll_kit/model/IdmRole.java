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
@Table(name = "idm_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdmRole.findAll", query = "SELECT i FROM IdmRole i")
    , @NamedQuery(name = "IdmRole.findById", query = "SELECT i FROM IdmRole i WHERE i.id = :id")
    , @NamedQuery(name = "IdmRole.findByRoleName", query = "SELECT i FROM IdmRole i WHERE i.roleName = :roleName")
    , @NamedQuery(name = "IdmRole.findByRoleDescription", query = "SELECT i FROM IdmRole i WHERE i.roleDescription = :roleDescription")})
public class IdmRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_description")
    private String roleDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<IdmUserRole> idmUserRoleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<IdmRoleModules> idmRoleModulesCollection;

    public IdmRole() {
    }

    public IdmRole(Integer id) {
        this.id = id;
    }

    public IdmRole(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @XmlTransient
    public Collection<IdmUserRole> getIdmUserRoleCollection() {
        return idmUserRoleCollection;
    }

    public void setIdmUserRoleCollection(Collection<IdmUserRole> idmUserRoleCollection) {
        this.idmUserRoleCollection = idmUserRoleCollection;
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
        if (!(object instanceof IdmRole)) {
            return false;
        }
        IdmRole other = (IdmRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.istl.enroll_kit.model.IdmRole[ id=" + id + " ]";
    }
    
}
