/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.util;

import com.istl.accesscontrol.model.PersonBiometric;
import com.istl.enroll_kit.model.EnrollPersonBiometric;

/**
 *
 * @author Arun
 */
public class EnrollPersonBiometricConvert {

    public static PersonBiometric PersonBiometricConvertTOEnrollPersonBiometric(EnrollPersonBiometric eBiometric) {
        PersonBiometric biometric = new PersonBiometric();
        biometric.setPhoto(eBiometric.getPhoto());
        biometric.setSignature(eBiometric.getSignature());
        biometric.setWsqLi(eBiometric.getWsqLi());
        biometric.setWsqLm(eBiometric.getWsqLm());
        biometric.setWsqLr(eBiometric.getWsqLr());
        biometric.setWsqLs(eBiometric.getWsqLs());
        biometric.setWsqLt(eBiometric.getWsqLt());
        biometric.setWsqRi(eBiometric.getWsqRi());
        biometric.setWsqRm(eBiometric.getWsqRm());
        biometric.setWsqRr(eBiometric.getWsqRr());
        biometric.setWsqRs(eBiometric.getWsqRs());
        biometric.setWsqRt(eBiometric.getWsqRt());
        biometric.setIrisLeft(eBiometric.getIrisLeft());
        biometric.setIrisRight(eBiometric.getIrisRight());

        return biometric;
    }

}
