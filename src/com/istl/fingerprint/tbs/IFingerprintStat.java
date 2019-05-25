/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.fingerprint.tbs;

import com.istl.tbs.capture.api.TatvikFpNative;
import java.awt.image.BufferedImage;

/**
 *
 * @author Arun
 */
public interface IFingerprintStat {
    TatvikFpNative.TBSCaptureData getCaptureData();
    void setCaptureData(TatvikFpNative.TBSCaptureData captureData);
    BufferedImage getCaptureImage();
    void setFPImagePreview(BufferedImage image);
    void setFPImageFinal(BufferedImage image);
    void setCaptureDone();
    int getFingerCnt();
    int getImageType();
}
