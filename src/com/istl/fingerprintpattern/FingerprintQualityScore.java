package com.istl.fingerprintpattern;

import com.istl.fingerprint.data.FingerprintData;
import com.istl.fingerprint.segmentation.FPSegment;
import com.istl.nfiq.NFIQLibrary;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;


public class FingerprintQualityScore implements FingerprintStrategy{
    NFIQLibrary nFIQLibrary=null;

    @Override
    public boolean execute() {
        
        nFIQLibrary=NFIQLibrary.INSTANCE;
         int respCode = 0, i = 0;
         FPSegment[] segments = FingerprintData.getSegment();
         int nf = FingerprintData.getCaptureData().fingerCount;
         
         IntByReference onfiq=null;
         FloatByReference oconf=null;
         IntByReference optflag=null;
         if (nf > 1) {
             for (i = 0; i < nf; i++) {
                 System.out.println(">>>sout>>"+segments[i].getSegDataSz());
                 onfiq=new IntByReference();
                 oconf=new FloatByReference();
                 optflag=new IntByReference();
                 try{
                     respCode=nFIQLibrary.comp_nfiq(onfiq,oconf,segments[i].getSegData(),segments[i].getW(),segments[i].getH(),8,-1,optflag);
                     segments[i].setScore(scaleScore(onfiq.getValue()));
                     System.out.println("si`ze ="+segments[i].getSegData().length);
                     System.out.println("width ="+segments[i].getW());
                     System.out.println("height ="+segments[i].getH());
                     System.out.println("size cross check ="+segments[i].getH()*segments[i].getW());
                     respCode=nFIQLibrary.comp_nfiq(onfiq,oconf,segments[i].getSegData(),segments[i].getW(),segments[i].getH(),8,-1,optflag);
                     segments[i].setScore(scaleScore(onfiq.getValue()));
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
    }



        
    

    @Override
    public boolean executeDone() 
    {
        return true;
    }
    
    public int scaleScore(int value)
    {
        if(value==4)
            return 1000;
        else if(value==1)
            return 9000;
        else if(value==2)
            return 8000;
        else if(value==3)
            return 6000;
        else
            return 1000;
    }
    
}
