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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "enroll_utils")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnrollUtils.findAll", query = "SELECT e FROM EnrollUtils e")
    , @NamedQuery(name = "EnrollUtils.findById", query = "SELECT e FROM EnrollUtils e WHERE e.id = :id")
    , @NamedQuery(name = "EnrollUtils.findByType", query = "SELECT e FROM EnrollUtils e WHERE e.type = :type")
    , @NamedQuery(name = "EnrollUtils.findByName", query = "SELECT e FROM EnrollUtils e WHERE e.name = :name")
    , @NamedQuery(name = "EnrollUtils.findByColorCode", query = "SELECT e FROM EnrollUtils e WHERE e.colorCode = :colorCode")})
public class EnrollUtils implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "color_code")
    private String colorCode;
    @Column(name = "priority")
    private Integer priority;
    @Column(name = "remarks")
    private String remarks;

    public EnrollUtils() {
    }

    public EnrollUtils(String id) {
        this.id = id;
    }

    public EnrollUtils(String id, String type, String name, String colorCode, Integer priority, String remarks) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.colorCode = colorCode;
        this.priority = priority;
        this.remarks = remarks;
    }

    
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
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
        if (!(object instanceof EnrollUtils)) {
            return false;
        }
        EnrollUtils other = (EnrollUtils) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    
    
}
