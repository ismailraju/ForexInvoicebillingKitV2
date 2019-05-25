/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.tbs.capture.api.callback;


import com.istl.fingerprint.data.FingerprintData;

import com.istl.fingerprintpattern.FingerprintContext;
import com.istl.fingerprintpattern.FingerprintQualityScore;
import com.istl.fingerprintpattern.FingerprintSegmentation;
import com.istl.tbs.capture.api.TatvikFpNative;
import com.istl.tbs.capture.api.TatvikFpNative.ResultImageCallbackInterface;


/**
 *
 * @author Arun
 */
public class ResultImageCallbackImplementation implements ResultImageCallbackInterface {
    private boolean completed = false;
    @Override
    public void invoke(int pContext, TatvikFpNative.TBSCaptureData tbsCaptureData) {
        FingerprintData.setCaptureData(tbsCaptureData);
        System.out.println("CAPTURED OBJECT: " + tbsCaptureData);
        FingerprintData.setIsDone(true);
        completed = true;
        System.out.println("CAPTURED COMPLETED BOOL: " + completed);
      
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
