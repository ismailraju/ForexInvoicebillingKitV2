package com.istl.fingerprint.data;

import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istl.fingerprint.segmentation.FPSegment;
import com.istl.tbs.capture.api.TatvikFpNative.TBSCaptureData;
import java.awt.image.BufferedImage;


public class FingerprintData {
    
    
    public static BufferedImage image;
    public static TBSCaptureData captureData;
    public static boolean isDone;
    public static FPSegment[] segment;
    public static int score;
    public static FPSegment Rt;
    public static FPSegment Ri;
    public static FPSegment Rm;
    public static FPSegment Rr;
    public static FPSegment Rs;
    public static FPSegment Lt;
    public static FPSegment Li;
    public static FPSegment Lm;
    public static FPSegment Lr;
    public static FPSegment Ls;

    public FingerprintData() {
    }

    public static BufferedImage getImage() {
        return image;
    }

    public static void setImage(BufferedImage image) {
        FingerprintData.image = image;
    }
    
    public static TBSCaptureData getCaptureData() {
        return captureData;
    }

    public static void setCaptureData(TBSCaptureData captureData) {
        FingerprintData.captureData = captureData;
    }

    public static boolean isIsDone() {
        return isDone;
    }

    public static void setIsDone(boolean isDone) {
        FingerprintData.isDone = isDone;
    }

    public static FPSegment[] getSegment() {
        return segment;
    }

    public static void setSegment(FPSegment[] segment) {
        FingerprintData.segment = segment;
    }
    
    public static void setSegment(int pos , FPSegment segment) 
    {
        FingerprintData.segment[pos] = segment;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        FingerprintData.score = score;
    }

    public static FPSegment getRt() {
        return Rt;
    }

    public static void setRt(FPSegment Rt) {
        FingerprintData.Rt = Rt;
    }

    public static FPSegment getRi() {
        return Ri;
    }

    public static void setRi(FPSegment Ri) {
        FingerprintData.Ri = Ri;
    }

    public static FPSegment getRm() {
        return Rm;
    }

    public static void setRm(FPSegment Rm) {
        FingerprintData.Rm = Rm;
    }

    public static FPSegment getRr() {
        return Rr;
    }

    public static void setRr(FPSegment Rr) {
        FingerprintData.Rr = Rr;
    }

    public static FPSegment getRs() {
        return Rs;
    }

    public static void setRs(FPSegment Rs) {
        FingerprintData.Rs = Rs;
    }

    public static FPSegment getLt() {
        return Lt;
    }

    public static void setLt(FPSegment Lt) {
        FingerprintData.Lt = Lt;
    }

    public static FPSegment getLi() {
        return Li;
    }

    public static void setLi(FPSegment Li) {
        FingerprintData.Li = Li;
    }

    public static FPSegment getLm() {
        return Lm;
    }

    public static void setLm(FPSegment Lm) {
        FingerprintData.Lm = Lm;
    }

    public static FPSegment getLr() {
        return Lr;
    }

    public static void setLr(FPSegment Lr) {
        FingerprintData.Lr = Lr;
    }

    public static FPSegment getLs() {
        return Ls;
    }

    public static void setLs(FPSegment Ls) {
        FingerprintData.Ls = Ls;
    }

   
   
    
}
