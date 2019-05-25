/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.tbs.capture.api.callback;

import com.istl.fingerprint.data.FingerprintData;
import com.istl.imageprocessing.Utils;
import com.istl.tbs.capture.api.TatvikFpNative;
import com.istl.tbs.capture.api.TatvikFpNative.PreviewImageCallbackInterface;
import java.awt.image.BufferedImage;

/**
 *
 * @author Arun
 */
public class PreviewImageCallbackImplementation implements PreviewImageCallbackInterface {
    
    @Override
    public void invoke(int pContext, TatvikFpNative.TBSCaptureData tbsCaptureData, TatvikFpNative.TBSCaptureQuality tbsCaptureQuality) {
        BufferedImage bImageFromConvert = Utils.ByteArraytoBufferedImage(tbsCaptureData.rawImageBytes,tbsCaptureData.imageWidth,tbsCaptureData.imageHeight);
        FingerprintData.setImage(bImageFromConvert);
        
    }
}
