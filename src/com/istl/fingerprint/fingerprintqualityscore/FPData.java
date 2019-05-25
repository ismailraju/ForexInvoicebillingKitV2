/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.fingerprint.fingerprintqualityscore;

/**
 *
 * @author Arun
 */
public class FPData {
    private byte[] segData;
    private int segDataSz;
    private int segErr;
    private int score;
    private int w;
    private int h;
    
    public FPData(){
        this.segData = null;
        this.segDataSz = 0;
        this.segErr = 0;
        this.score = -1;
    }

    public byte[] getSegData() {
        return segData;
    }

    public void setSegData(byte[] segData) {
        this.segData = segData;
    }
    
    public int getSegDataSz() {
        return segDataSz;
    }

    public void setSegDataSz(int segDataSz) {
        this.segDataSz = segDataSz;
    }

    public int getSegErr() {
        return segErr;
    }

    public void setSegErr(int segErr) {
        this.segErr = segErr;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
    
    
    
    
}
