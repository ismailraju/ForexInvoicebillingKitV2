/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.accesscontrol.model;

import java.io.Serializable;

public class EnrollUtils implements Serializable {

    private String id;

    private String type;

    private String name;

    private String colorCode;

    private Integer priority;

    private String remarks;

    public EnrollUtils() {
    }

    public EnrollUtils(String id) {
        this.id = id;
    }

    public EnrollUtils(String id, String type, String name, String colorCode) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
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

    @Override
    public String toString() {
        return "EnrollUtils{" + "id=" + id + ", type=" + type + ", name=" + name + ", colorCode=" + colorCode + ", priority=" + priority + ", remarks=" + remarks + '}';
    }

  

}
