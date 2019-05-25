package com.istl.controller.enrollment.fingerprint;

import com.istl.fingerprint.fingerprintqualityscore.FPData;
import com.istl.ftrscanapi.FTRSCANAPILibrary;
import com.istl.ftrscanapi.FTRSCAN_IMAGE_SIZE;
import com.istl.util.Utils;
import com.sun.jna.Memory;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

public class FingerprintEnrollment{
    
    WritableImage image=null;
    int ret = -1;
    NativeLong err;
    FTRSCANAPILibrary ftrScanLib = null;
    FTRSCAN_IMAGE_SIZE ImageSize;
    Pointer handle;
    FPData data= new FPData();
    
    public boolean deviceConnection(){
        try {
            ftrScanLib = FTRSCANAPILibrary.INSTANCE;
            handle = ftrScanLib.ftrScanOpenDevice();
            if(handle==null){
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
       return true;
   }
    
    
    public WritableImage getImage() throws IOException, Exception{
        deviceConnection();
        System.out.println("Device opened >>>> ");
        System.out.println("keep fngr>>>> ");
        FTRSCAN_IMAGE_SIZE.ByReference imageSize = new FTRSCAN_IMAGE_SIZE.ByReference();
        ret = ftrScanLib.ftrScanGetImageSize(handle, imageSize);
        if (ret == FTRSCANAPILibrary.TRUE){
            Pointer imgPtr = new Memory(imageSize.nImageSize);
            //ftrScanLib.ftrScanSetDiodesStatus(handle, (byte) 254, (byte) 254);
            while (true){
                ftrScanLib.ftrScanSetOptions(handle, new NativeLong(64), new NativeLong(64));
                ret = ftrScanLib.ftrScanGetFrame(handle, imgPtr, null);
                if (ret == FTRSCANAPILibrary.FALSE){
                    err = ftrScanLib.ftrScanGetLastError();
                    if (err == null){
                        System.err.println("No last error found>>>");
                        break;
                    }
                } else{
                    byte[] imgData = new byte[imageSize.nImageSize];
                    imgPtr.read(0, imgData, 0, imageSize.nImageSize);
                    BufferedImage buffImg = Utils.ByteArraytoImage(imgData, imageSize.nWidth, imageSize.nHeight);
                    BufferedImage greyScaleImg = Utils.convertToGray(buffImg);
                    if (greyScaleImg != null){
                        BufferedImage final_image=getProcessedImage(greyScaleImg);
                        image = SwingFXUtils.toFXImage( final_image, null);
                        ftrScanLib.ftrScanCloseDevice(handle);
                        return image;
                    } else{
                        System.out.println("no image");
                    }
                    break;
                }
            }
        }
        
        return image;
    }
    
    public WritableImage getFingerPrint() throws IOException{
        try{
            ftrScanLib = FTRSCANAPILibrary.INSTANCE;
            handle = ftrScanLib.ftrScanOpenDevice();
            if(handle==null){
                System.out.println("No device connected >>>> ");
            }else{
                System.out.println("Device opened >>>> ");
                System.out.println("keep fngr>>>> ");
                FTRSCAN_IMAGE_SIZE.ByReference imageSize = new FTRSCAN_IMAGE_SIZE.ByReference();
                ret = ftrScanLib.ftrScanGetImageSize(handle, imageSize);
                if (ret == FTRSCANAPILibrary.TRUE){
                    Pointer imgPtr = new Memory(imageSize.nImageSize);
                    ftrScanLib.ftrScanSetDiodesStatus(handle, (byte) 254, (byte) 254);
                    while (true){
                        ret = ftrScanLib.ftrScanGetFrame(handle, imgPtr, null);
                        if (ret == FTRSCANAPILibrary.FALSE){
                            err = ftrScanLib.ftrScanGetLastError();
                            if (err == null){
                                System.err.println("No last error found>>>");
                                break;
                            }
                        } else{
                            byte[] imgData = new byte[imageSize.nImageSize];
                            imgPtr.read(0, imgData, 0, imageSize.nImageSize);
                            BufferedImage buffImg = Utils.ByteArraytoImage(imgData, imageSize.nWidth, imageSize.nHeight);
                           // BufferedImage greyScaleImg = Utils.convertToGray(buffImg);
                            if (buffImg != null){
                                //System.out.println("image");
                              BufferedImage final_image=buffImg;
                             // BufferedImage final_image= getProcessedImage(greyScaleImg);
                                image = SwingFXUtils.toFXImage( final_image, null);
                                ftrScanLib.ftrScanCloseDevice(handle);
                                return image;
                            } else{
                                System.out.println("no image");
                            }
                            break;
                        }
                    }
                }
                ftrScanLib.ftrScanSetDiodesStatus(handle, (byte) 0, (byte) 0);
                System.out.println("ret = " + ret);
                System.out.println("Image Size = " + imageSize.nImageSize);
                System.out.println("Image Width = " + imageSize.nWidth);
                System.out.println("Image Height = " + imageSize.nHeight);
                ftrScanLib.ftrScanCloseDevice(handle);

            }
        }catch(Exception exc){
            System.err.println(exc);
        }
        
        return image;
    }
    
    
    public FPData getFPData() throws IOException, Exception{
        deviceConnection();
        System.out.println("Device opened >>>> ");
        System.out.println("keep fngr>>>> ");
        FTRSCAN_IMAGE_SIZE.ByReference imageSize = new FTRSCAN_IMAGE_SIZE.ByReference();
        ret = ftrScanLib.ftrScanGetImageSize(handle, imageSize);
        if (ret == FTRSCANAPILibrary.TRUE){
            Pointer imgPtr = new Memory(imageSize.nImageSize);
//            ftrScanLib.ftrScanSetDiodesStatus(handle, (byte) 254, (byte) 254);
//            ftrScanLib.ftrScanSetOptions(handle, new Long(64), new Long(64));
            while (true){
                ftrScanLib.ftrScanSetOptions(handle, new NativeLong(64), new NativeLong(64));
                ret = ftrScanLib.ftrScanGetFrame(handle, imgPtr, null);
                if (ret == FTRSCANAPILibrary.FALSE){
                    err = ftrScanLib.ftrScanGetLastError();
                    if (err == null){
                        System.err.println("No last error found>>>");
                        break;
                    }
                } else{
                    byte[] imgData = new byte[imageSize.nImageSize];
                   // Thread.sleep(1000);
                    imgPtr.read(0, imgData, 0, imageSize.nImageSize);
                    data.setH(imageSize.nHeight);
                    data.setW(imageSize.nWidth);
                    data.setSegData(imgData);
                    return data;
                }
            }
        }
        
        return null;
    }
    
    
   
    
    private final int clamp(int c){
        return (c>255 ? 255:(c < 0 ? 0 : c));
    }
    
    public BufferedImage getProcessedImage(BufferedImage img){
        int width = img.getWidth();
        int height= img.getHeight();
        float mean = 0;
        float sum = 0;
        float variance = 0;
        int[] black = new int[]{0, 0, 0};
        int[] white = new int[]{255, 255, 255};
        int array[][] = new int[height + 100][width + 100];
        int arr[][] = new int[height + 100][width + 100];
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                int clr = img.getRGB(i, j);
                int red = (clr & 0x00ff0000) >> 16;
                array[j + 50][i + 50] = red;
            }
        }
        
        for (int i = 50; i < width + 50; i++){
            for (int j = 50; j < height + 50; j++){
                mean = mean + array[j][i];
            }
        }
        mean = mean / (height * width);

        for (int i = 50; i < width + 50; i++){
            for (int j = 50; j < height + 50; j++){
                sum = sum + ((array[j][i] - mean) * (array[j][i] - mean));
            }
        }
        variance = sum / (width * height);
        
        if (variance < 500){
            for (int i = 50; i < height + 50; i++){
                for (int j = 50; j < width + 50; j++){
                    arr[i][j] = clamp(9 * array[i][j] - (array[i - 1][j - 1] + array[i - 1][j] + array[i - 1][j + 1] + array[i][j - 1] + array[i][j + 1] + array[i + 1][j - 1] + array[i + 1][j] + array[i + 1][j + 1]));
                }
            }
        } else{
            for (int i = 50; i < height + 50; i++){
                for (int j = 50; j < width + 50; j++){
                    arr[i][j] = clamp(5 * array[i][j] - (array[i][j - 1] + array[i][j + 1]));
                  }
              }
          }
        
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                int clr = img.getRGB(i, j);
                int red = (clr & 0x00ff0000) >> 16;
                
                if (red > mean){
                    raster.setPixel(i, j, black);
                  } else{
                    raster.setPixel(i, j, white);
                  }
              }
          }
        
        return image;
    }
  
  }         
     
