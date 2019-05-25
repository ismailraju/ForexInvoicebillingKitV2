/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.fingerprintpattern;

import com.istl.fingerprint.GBNFIQ2_JAVA_Functions.GBNFIQ2_DllWRapper;
import com.istl.fingerprint.data.FingerprintData;
import com.istl.fingerprint.segmentation.FPSegment;
import com.istl.nfiq.NFIQLibrary;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;

/**
 *
 * @author Arun
 */
public class GBNFIQ2_ImageQuality implements FingerprintStrategy{
    
    GBNFIQ2_DllWRapper.GBNfiq2_Library nfiqLib = null;

    @Override
    public boolean execute() {
        
        nfiqLib = GBNFIQ2_DllWRapper.GBNfiq2_Library.INSTANCE;
        int respCode = 0, i = 0;
        FPSegment[] segments = FingerprintData.getSegment();
        int nf = FingerprintData.getCaptureData().fingerCount;
        
        IntByReference onfiq=null;
        FloatByReference oconf=null;
        IntByReference optflag=null;
         if (nf > 1) {
             for (i = 0; i < nf; i++) {
                 System.out.println(">>>sout>>"+segments[i].getSegDataSz());
                 IntByReference imageQuality = new IntByReference();
                 oconf=new FloatByReference();
                 optflag=new IntByReference();
                 try{
                     System.out.println(">>>>IN quality>>>>");
                     nfiqLib.GBNFIQ2_Load();
                     nfiqLib.GBNFIQ2_GetImageQuality(segments[i].getSegData(), segments[i].getW(), segments[i].getH(), imageQuality);
                     nfiqLib.GBNFIQ2_Unload();
                     segments[i].setScore(imageQuality.getValue());
                     System.out.println(">>>Quality>>"+segments[i].getScore());
                 }
                 catch(Exception ex){
                    System.out.println("Failed to determine score!!!");
                    segments[i].setScore(999);
                 }
                
                
            }
            
             
             System.out.println(">>score done");
             return true;
         } else {


            }

 return false;
        
        
        
//        System.out.println(">>IN>>");
//            
//            System.out.println(">>IN>>>");
//            
//            System.out.println(">>IN>>>");
//            
//            System.out.println(">>IN>>>>");
//            
//            System.out.println(">>IN>>>>>");
//            
//            System.out.println(">>IN>>>>>>");
//            data.
//            System.out.println(">>IN>>>>>>");
//            System.out.println("Image Quality = " + imageQuality.getValue());
       
    }

    @Override
    public boolean executeDone() {
        return true;
    }
    
}
