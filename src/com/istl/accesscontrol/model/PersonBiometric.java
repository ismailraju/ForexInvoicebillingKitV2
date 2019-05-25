/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.accesscontrol.model;

import java.io.Serializable;

public class PersonBiometric implements Serializable {

    private Integer id;

    private byte[] photo;

    private byte[] signature;

    private byte[] wsqLi;

    private byte[] wsqLm;

    private byte[] wsqLr;

    private byte[] wsqLs;

    private byte[] wsqLt;

    private byte[] wsqRi;

    private byte[] wsqRm;

    private byte[] wsqRr;

    private byte[] wsqRs;

    private byte[] wsqRt;

    private byte[] irisLeft;
    private byte[] irisRight;
    private byte[] irisInfo;

    private Person personId;

    public PersonBiometric() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public byte[] getWsqLi() {
        return wsqLi;
    }

    public void setWsqLi(byte[] wsqLi) {
        this.wsqLi = wsqLi;
    }

    public byte[] getWsqLm() {
        return wsqLm;
    }

    public void setWsqLm(byte[] wsqLm) {
        this.wsqLm = wsqLm;
    }

    public byte[] getWsqLr() {
        return wsqLr;
    }

    public void setWsqLr(byte[] wsqLr) {
        this.wsqLr = wsqLr;
    }

    public byte[] getWsqLs() {
        return wsqLs;
    }

    public void setWsqLs(byte[] wsqLs) {
        this.wsqLs = wsqLs;
    }

    public byte[] getWsqLt() {
        return wsqLt;
    }

    public void setWsqLt(byte[] wsqLt) {
        this.wsqLt = wsqLt;
    }

    public byte[] getWsqRi() {
        return wsqRi;
    }

    public void setWsqRi(byte[] wsqRi) {
        this.wsqRi = wsqRi;
    }

    public byte[] getWsqRm() {
        return wsqRm;
    }

    public void setWsqRm(byte[] wsqRm) {
        this.wsqRm = wsqRm;
    }

    public byte[] getWsqRr() {
        return wsqRr;
    }

    public void setWsqRr(byte[] wsqRr) {
        this.wsqRr = wsqRr;
    }

    public byte[] getWsqRs() {
        return wsqRs;
    }

    public void setWsqRs(byte[] wsqRs) {
        this.wsqRs = wsqRs;
    }

    public byte[] getWsqRt() {
        return wsqRt;
    }

    public void setWsqRt(byte[] wsqRt) {
        this.wsqRt = wsqRt;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public byte[] getIrisLeft() {
        return irisLeft;
    }

    public void setIrisLeft(byte[] irisLeft) {
        this.irisLeft = irisLeft;
    }

    public byte[] getIrisRight() {
        return irisRight;
    }

    public void setIrisRight(byte[] irisRight) {
        this.irisRight = irisRight;
    }

    public byte[] getIrisInfo() {
        return irisInfo;
    }

    public void setIrisInfo(byte[] irisInfo) {
        this.irisInfo = irisInfo;
    }

}
