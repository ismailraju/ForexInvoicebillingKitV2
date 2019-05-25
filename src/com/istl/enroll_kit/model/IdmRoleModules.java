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
@Table(name = "idm_role_modules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdmRoleModules.findAll", query = "SELECT i FROM IdmRoleModules i")
    , @NamedQuery(name = "IdmRoleModules.findById", query = "SELECT i FROM IdmRoleModules i WHERE i.id = :id")})
public class IdmRoleModules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IdmModules moduleId;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IdmRole roleId;

    public IdmRoleModules() {
    }

    public IdmRoleModules(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IdmModules getModuleId() {
        return moduleId;
    }

    public void setModuleId(IdmModules moduleId) {
        this.moduleId = moduleId;
    }

    public IdmRole getRoleId() {
        return roleId;
    }

    public void setRoleId(IdmRole roleId) {
        this.roleId = roleId;
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
        if (!(object instanceof IdmRoleModules)) {
            return false;
        }
        IdmRoleModules other = (IdmRoleModules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.istl.enroll_kit.model.IdmRoleModules[ id=" + id + " ]";
    }
    
}
