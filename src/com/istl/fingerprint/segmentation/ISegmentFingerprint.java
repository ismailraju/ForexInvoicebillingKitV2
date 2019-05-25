/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.fingerprint.segmentation;

import com.istl.tbs.capture.api.TatvikFpNative;

/**
 *
 * @author Arun
 */
public interface ISegmentFingerprint {
    TatvikFpNative.TBSCaptureData getcaptureData();
    int getFingerCnt();    
    void setFPData(TatvikFpNative.TBSCaptureData captureData);
    void segmentationDone();
    void segmentationFailed(String msg);
    int getImageType();
    
}
