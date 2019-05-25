package com.istl.fingerprint.fingerprintqualityscore;

import com.istl.nfiq.NFIQLibrary;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;

public class ComputeQualityScore {
    
    NFIQLibrary nFIQLibrary=null;
    public FPData computeQS(FPData data) throws Exception {
        System.out.println("call compute");
        nFIQLibrary=NFIQLibrary.INSTANCE;
        int respCode = 0, i = 0;
        int nf = 1;
        
        IntByReference onfiq=null;
        FloatByReference oconf=null;
        IntByReference optflag=null;
        if (nf == 1) {
            onfiq=new IntByReference();
            oconf=new FloatByReference();
            optflag=new IntByReference();
            try{
                respCode=nFIQLibrary.comp_nfiq(onfiq,oconf,data.getSegData(),data.getW(),data.getH(),8,-1,optflag);
                data.setScore(scaleScore(onfiq.getValue()));
//                System.out.println("si`ze ="+data.getSegData().length);
//                System.out.println("width ="+data.getW());
//                System.out.println("height ="+data.getH());
//                System.out.println("size cross check ="+data.getH()*data.getW());
//                respCode=nFIQLibrary.comp_nfiq(onfiq,oconf,data.getSegData(),data.getW(),data.getH(),8,-1,optflag);
//                data.setScore(scaleScore(onfiq.getValue()));
//                System.out.println(">>>value>>>"+onfiq.getValue());
//                System.out.println(">>>>>Score>>>>>"+data.getScore());
                
            }
            catch(Exception ex){
                System.out.println("Failed to determine score!!!");
                data.setScore(999);
            }
        }
        return data;
    }
    
//    public int scaleScore(int value){
//        if(value==5)
//            return 10000;
//        else if(value==4)
//            return 9000;
//        else if(value==3)
//            return 8000;
//        else if(value==2)
//            return 6000;
//        else
//            return 2000;
//    }
    
    private int scaleScore(int value)
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
