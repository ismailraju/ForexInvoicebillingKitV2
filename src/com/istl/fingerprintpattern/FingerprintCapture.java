/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.fingerprintpattern;


/**
 *
 * @author Arun
 */
public class FingerprintCapture implements FingerprintStrategy{

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public boolean executeDone() {
        return true;
    }
    
}
