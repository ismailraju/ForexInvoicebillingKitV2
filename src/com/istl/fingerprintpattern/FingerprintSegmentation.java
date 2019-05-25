package com.istl.fingerprintpattern;

import com.istl.fingerprint.data.FingerprintData;
import com.istl.fingerprint.library.LibraryLoader;
import com.istl.fingerprint.segmentation.FPSegment;
import com.istl.imageprocessing.Utils;
import com.istl.nfseg.NFSEGLibrary;
import com.istl.tbs.capture.api.TATVIKImageType;
import com.istl.tbs.capture.api.TatvikFpNative;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arun
 */
public class FingerprintSegmentation implements FingerprintStrategy{
    
    
    
    
    @Override
    public boolean execute( ){
        try{
            TatvikFpNative.TBSCaptureData captureData=FingerprintData.getCaptureData();
            int fgp = 0;
            int fingerCount = FingerprintData.getCaptureData().fingerCount;
            int whichFinger = FingerprintData.getCaptureData().fingerPosition;
            NFSEGLibrary nfseglib = (NFSEGLibrary)LibraryLoader.getLibrary("nfseg", "com.istl.nfseg.NFSEGLibrary");
            PointerByReference pbref = new PointerByReference();
            if(whichFinger == TATVIKImageType.TATVIK_FLAT_LEFT_FINGERS){
                fgp = 14;
            }
            else if(whichFinger == TATVIKImageType.TATVIK_FLAT_RIGHT_FINGERS){
                fgp = 13;
            }
            else if(whichFinger == TATVIKImageType.TATVIK_FLAT_TWO_THUMBS){
                fgp = 15;
            }
            else
                fgp=15;
            
            int rv = 0;
            if (fingerCount >= 1) {
                System.out.println("in");
                rv = nfseglib.segment_finger_prints(captureData.rawImageBytes, captureData.imageWidth, captureData.imageHeight, pbref, fingerCount, fgp, 1, 1);
            }
            Pointer segPtr = null;
            NFSEGLibrary.SEGMENT[] segArray = null;
            FPSegment[] segments = null;
            
            if (rv == 0) {
             
                if (fingerCount >= 1) {
                
                    segPtr = pbref.getValue();
                   
                    NFSEGLibrary.SEGMENT seg = new NFSEGLibrary.SEGMENT(segPtr);
                    
                    seg.read();  ////Read the first element                        
                   
                    segArray = (NFSEGLibrary.SEGMENT[]) seg.toArray(fingerCount);
                   
                    segments = new FPSegment[fingerCount];
                    for (int i = 0; i < fingerCount; i++) {
                      
                        segments[i] = new FPSegment();
                        segments[i].setX(segArray[i].sx);
                        segments[i].setY(segArray[i].sy);
                        segments[i].setW(segArray[i].sw);
                        segments[i].setH(segArray[i].sh);
                        
//                        segments[i].setW(320);
//                        segments[i].setH(480);
                        System.out.println("segment width=" + segArray[i].sw);
                        System.out.println("segment hight=" + segArray[i].sh);
                        segments[i].setSegErr(segArray[i].err);
                    }
                
                }
            } else {
                try {
                    throw new Exception("Segmentation failed with error code : " + rv);
                } catch (Exception ex) {
                    Logger.getLogger(FingerprintSegmentation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(segPtr!=null){
            
                PointerByReference parsedImgdata = new PointerByReference();
                rv =  nfseglib.parse_segfing(parsedImgdata, captureData.rawImageBytes , captureData.imageWidth , captureData.imageHeight , segPtr , fingerCount,1);
                if(rv==0){
                    Pointer p = parsedImgdata.getValue();
                    Pointer[] imgPtr = p.getPointerArray(0, fingerCount);
                    for(int i=0;i<fingerCount;i++){
                        byte[] tmp = new byte[segArray[i].sw*segArray[i].sh];
                        imgPtr[i].read(0, tmp, 0, segArray[i].sw*segArray[i].sh);
                        segments[i].setSegData(tmp);
                        segments[i].setSegDataSz(segArray[i].sw*segArray[i].sh);
                        byte[] image=Utils.rawToBmp(tmp, segArray[i].sw, segArray[i].sh);
                        ByteArrayInputStream lt=new ByteArrayInputStream(image);
                        BufferedImage bimage=ImageIO.read(lt);
                        ImageIO.write(bimage, "bmp", new File(i+".bmp"));
                    
//                    byte[] tmp = new byte[320*480];
//                        imgPtr[i].read(0, tmp, 0, 320*480);
//                        segments[i].setSegData(tmp);
//                        segments[i].setSegDataSz(320*480);
//                        byte[] image=Utils.rawToBmp(tmp, 320, 480);
//                        ByteArrayInputStream lt=new ByteArrayInputStream(image);
//                        BufferedImage bimage=ImageIO.read(lt);
//                        ImageIO.write(bimage, "bmp", new File(i+".bmp"));
                    
                    
                    }
                }
            
            if(fingerCount>=1){
                FingerprintData.setSegment(segments);
            }else{
                switch(whichFinger){
                    case TATVIKImageType.TATVIK_FLAT_LEFT_SMALL:
                        FingerprintData.setSegment(0, segments[0]);
                        break;
                    case TATVIKImageType.TATVIK_FLAT_LEFT_RING:
                        FingerprintData.setSegment(1, segments[0]);
                        break;
                    case TATVIKImageType.TATVIK_FLAT_LEFT_MIDDLE:
                        FingerprintData.setSegment(2, segments[0]);
                        break;    
                    case TATVIKImageType.TATVIK_FLAT_LEFT_INDEX:
                        FingerprintData.setSegment(3, segments[0]);
                        break;    
                    case TATVIKImageType.TATVIK_FLAT_LEFT_THUMB:
                        FingerprintData.setSegment(0, segments[0]);
                        break;    
                    case TATVIKImageType.TATVIK_FLAT_RIGHT_SMALL:
                        FingerprintData.setSegment(3, segments[0]);
                        break;    
                    case TATVIKImageType.TATVIK_FLAT_RIGHT_RING:
                        FingerprintData.setSegment(2, segments[0]);
                        break;    
                    case TATVIKImageType.TATVIK_FLAT_RIGHT_MIDDLE:
                        FingerprintData.setSegment(1, segments[0]);
                        break;    
                    case TATVIKImageType.TATVIK_FLAT_RIGHT_INDEX:
                        FingerprintData.setSegment(0, segments[0]);
                        break;    
                    case TATVIKImageType.TATVIK_FLAT_RIGHT_THUMB:
                        FingerprintData.setSegment(1, segments[0]);
                        break;
                    default:
                        break;
                }
            }
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
       
         return false;
    }
    


    @Override
    public boolean executeDone() {
        return true;
    }
    
    
   
}
