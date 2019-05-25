/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.fingerprint.fingerprintqualityscore;

import com.istl.fingerprint.GBNFIQ2_JAVA_Functions.GBNFIQ2_DllWRapper;
import com.sun.jna.ptr.IntByReference;

/**
 *
 * @author Arun
 */
public class GBNFIQ2_ImageQuality {

    GBNFIQ2_DllWRapper.GBNfiq2_Library nfiqLib = null;

    public FPData computeQS(FPData data) {
        try {
            System.out.println(">>IN>>");
            nfiqLib = GBNFIQ2_DllWRapper.GBNfiq2_Library.INSTANCE;
            System.out.println(">>IN>>>");
            IntByReference imageQuality = new IntByReference();
            System.out.println(">>IN>>>");
            nfiqLib.GBNFIQ2_Load();
            System.out.println(">>IN>>>>");
            nfiqLib.GBNFIQ2_GetImageQuality(data.getSegData(), data.getW(), data.getH(), imageQuality);
            System.out.println(">>IN>>>>>");
            nfiqLib.GBNFIQ2_Unload();
            System.out.println(">>IN>>>>>>");
            data.setScore(imageQuality.getValue());
            System.out.println(">>IN>>>>>>");
            System.out.println("Image Quality = " + imageQuality.getValue());

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
