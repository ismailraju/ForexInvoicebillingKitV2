package com.istl.iris.controller;

import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istlbd.iris.cmi.CMILibrary;
import com.istlbd.iris.cmi.CMILibraryFactory;
import com.istlbd.iris.cmi.CMI_DM_EVENT;
import com.istlbd.iris.cmi.CMI_EVENT;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainController {

    public  EnrollPersonBiometric biometric;
    //CMILibrary

   
    public  PointerByReference g_handle = null;
    public ByteBuffer g_Buffer = null;
    private  String g_Version = "";
    private static List<CMI_DM_EVENT.ByReference> g_DeviceList = new ArrayList<CMI_DM_EVENT.ByReference>();
//	private static CMI_BMT_SPECIFIC_DEFINE g_BmtSpecificMode = CMI_BMT_SPECIFIC_DEFINE.CMI_BMT_AUTO_MODE;
    private  int g_BmtSpecificMode = CMILibrary.CMI_BMT_AUTO_MODE;
    public  BufferedImage g_LeftIrisBI = null;
    public  BufferedImage g_RightIrisBI = null;

    public  boolean g_CaptureAutoSave = false;
    public  boolean g_CaptureGazeDetection = false;
    public  double g_CaptureLRIrisMargin = 0.0;
    public  double g_CaptureTBIrisMargin = 0.0;
    //public static int g_CaptureEyes = CMI_WHICHEYE_DEFINE.CMI_BOTH_EYES.getValue();
    public  int g_CaptureEyes = CMILibrary.CMI_BOTH_EYES;
    public  int g_ContinuosCaptureTimeInterval = 5;
    public  boolean g_CaptureLowBandwidth = false;
    public  double g_ImageXYMovement = 1.0;
//	public static int g_ImageLeftIrisIntensity = 0;
//	public static int g_ImageRightIrisIntensity = 0;
//	public static String g_ImageLeftExposure = "";
//	public static String g_ImageRightExposure = "";
    public  boolean g_DisplayFrame = true;
    public  int g_DisplayFrameRate = 1;
    public  boolean g_DisplayIrisBoundaryInPreview = true;

    @FXML
    MainTabController maintabController;
    @FXML
    OptionTabController optiontabController;
    
    public Stage primaryStage;

    @FXML
    public void initialize() {
        System.out.println(">>>>IN>>>>>");
        System.out.println("Application started");
        g_handle = new PointerByReference();
        // int ret = cmiris.createCMIris(CMI_DEVICE_TYPE.CMI_BMT_MODEL, g_handle);
        int ret = CMILibraryFactory.getCMILibrary().cmi_createCMIris(CMILibrary.CMI_BMT_MODEL, g_handle);
        System.out.println("createCMIris:" + ret);
        //  g_Version = cmiris.getCMIrisVersion().toString();
        maintabController.init(this);
        optiontabController.init(this);

        maintabController.open();

    }
    public void setDialogStage(Stage primaryStage){
            this.primaryStage=primaryStage;
        }
        
          public void setbiometric(EnrollPersonBiometric biometric){
              this.biometric=biometric;
          }

    public void setLImgFromDevice(Image img, CMI_EVENT event) {
        if (img != null) {
//			if (event.event == cmiris.CMI_EVENT_TYPE.CMI_EVENT_IRIS_IMAGES_SELECTED.getValue())
            if (event.event == CMILibrary.CMI_EVENT_IRIS_IMAGES_SELECTED) {
                double irisDiameter = event.imageInfo.leftIrisRadius * 2;
                double xpos = event.imageInfo.leftIrisCenterX - (irisDiameter / 2);
                double ypos = event.imageInfo.leftIrisCenterY - (irisDiameter / 2);

               // System.out.println("SELECTED L -> xpos:" + xpos + " ypos:" + ypos + " radius:" + irisDiameter + " width:" + img.getWidth() + " height:" + img.getHeight());

                Canvas cv = maintabController.leftEyeCanvas;
                GraphicsContext gc = cv.getGraphicsContext2D();
                gc.clearRect(0, 0, cv.getWidth(), cv.getHeight());
                gc.drawImage(img, 0, 0);
                gc.setLineWidth(2.0);
                gc.setStroke(Color.rgb(0, 255, 0));
                gc.strokeRect(0, 0, cv.getWidth(), cv.getHeight());

                if (irisDiameter > 0) {
                    gc.strokeRoundRect(xpos, ypos, irisDiameter, irisDiameter, irisDiameter, irisDiameter);
                }
            } //else if (event.event == cmiris.CMI_EVENT_TYPE.CMI_EVENT_PREVIEW_FRAME_INFO.getValue())
            else if (event.event == CMILibrary.CMI_EVENT_PREVIEW_FRAME_INFO) {
                double irisCenterX = event.imageInfo.leftIrisCenterX;
                double irisCenterY = event.imageInfo.leftIrisCenterY;
                double imgX = (maintabController.leftEyeCanvas.getWidth() - img.getWidth()) / 2;
                double imgY = (maintabController.leftEyeCanvas.getHeight() - img.getHeight()) / 2;
                double irisDiameter = event.imageInfo.leftIrisRadius * 2;
                double xpos = imgX + (irisCenterX - (irisDiameter / 2));
                double ypos = imgY + (irisCenterY - (irisDiameter / 2));
                boolean lookFront = event.imageInfo.doesLeftLookFront == 0 ? false : true;

               // System.out.println("PREVIEW R -> center_x: " + irisCenterX + " center_y:" + irisCenterY + " xpos:" + xpos + " ypos:" + ypos + " radius:" + irisDiameter + " width:" + img.getWidth() + " height:" + img.getHeight());

                Canvas cv = maintabController.leftEyeCanvas;
                GraphicsContext gc = cv.getGraphicsContext2D();
                gc.clearRect(0, 0, cv.getWidth(), cv.getHeight());
                gc.drawImage(img, imgX, imgY);
                gc.setLineWidth(2.0);
                if (lookFront) {
                    gc.setStroke(Color.rgb(0, 0, 255));
                } else {
                    gc.setStroke(Color.rgb(255, 0, 0));
                }

                if (irisDiameter > 0) {
                    gc.strokeRoundRect(xpos, ypos, irisDiameter, irisDiameter, irisDiameter, irisDiameter);
                }
            }
        }
    }

    public void setRImgFromDevice(Image img, CMI_EVENT event) {
        if (img != null) {
            //if (event.event == cmiris.CMI_EVENT_TYPE.CMI_EVENT_IRIS_IMAGES_SELECTED.getValue())
            if (event.event == CMILibrary.CMI_EVENT_IRIS_IMAGES_SELECTED) {
                double irisDiameter = event.imageInfo.rightIrisRadius * 2;
                double xpos = event.imageInfo.rightIrisCenterX - (irisDiameter / 2);
                double ypos = event.imageInfo.rightIrisCenterY - (irisDiameter / 2);

              //  System.out.println("SELECTED R -> xpos:" + xpos + " ypos:" + ypos + " radius:" + irisDiameter + " width:" + img.getWidth() + " height:" + img.getHeight());

                Canvas cv = maintabController.rightEyeCanvas;
                GraphicsContext gc = cv.getGraphicsContext2D();
                gc.clearRect(0, 0, cv.getWidth(), cv.getHeight());
                gc.drawImage(img, 0, 0);

                gc.setLineWidth(2.0);
                gc.setStroke(Color.rgb(0, 255, 0));
                gc.strokeRect(0, 0, cv.getWidth(), cv.getHeight());
                if (irisDiameter > 0) {
                    gc.strokeRoundRect(xpos, ypos, irisDiameter, irisDiameter, irisDiameter, irisDiameter);
                }
            } //else if (event.event == cmiris.CMI_EVENT_TYPE.CMI_EVENT_PREVIEW_FRAME_INFO.getValue())
            else if (event.event == CMILibrary.CMI_EVENT_PREVIEW_FRAME_INFO) {
                double irisCenterX = event.imageInfo.leftIrisCenterX;
                double irisCenterY = event.imageInfo.leftIrisCenterY;
                double imgX = (maintabController.rightEyeCanvas.getWidth() - img.getWidth()) / 2;
                double imgY = (maintabController.rightEyeCanvas.getHeight() - img.getHeight()) / 2;
                double irisDiameter = event.imageInfo.rightIrisRadius * 2;
                double xpos = imgX + (event.imageInfo.rightIrisCenterX - (irisDiameter / 2));
                double ypos = imgY + (event.imageInfo.rightIrisCenterY - (irisDiameter / 2));
                boolean lookFront = event.imageInfo.doesRightLookFront == 0 ? false : true;
                //boolean marginOk = event.imageInfo.rightIrisMargin == cmiris.CMI_SETTING_DEFINE.CMI_ALL_MARGINS_OK.getValue() ? true : false;
                //boolean marginOk = event.imageInfo.rightIrisMargin == cmiris.CMI_SETTING_DEFINE.CMI_ALL_MARGINS_OK.getValue() ? true : false;
             //   System.out.println("PREVIEW L -> center_x: " + irisCenterX + " center_y:" + irisCenterY + " xpos:" + xpos + " ypos:" + ypos + " radius:" + irisDiameter + " width:" + img.getWidth() + " height:" + img.getHeight());

                Canvas cv = maintabController.rightEyeCanvas;
                GraphicsContext gc = cv.getGraphicsContext2D();
                gc.clearRect(0, 0, cv.getWidth(), cv.getHeight());
                gc.drawImage(img, imgX, imgY);

                gc.setLineWidth(2.0);
                if (lookFront) {
                    gc.setStroke(Color.rgb(0, 0, 255));
                } else {
                    gc.setStroke(Color.rgb(255, 0, 0));
                }

                if (irisDiameter > 0) {
                    gc.strokeRoundRect(xpos, ypos, irisDiameter, irisDiameter, irisDiameter, irisDiameter);
                }
            }
        }
    }

    public Pointer getDeviceHandle() {
        return g_handle.getValue();
    }

    public void addDeviceList(CMI_DM_EVENT.ByReference dev) {
        if (dev != null) {
           // System.out.println(">>>device>>");
            g_DeviceList.add(dev);
        }
    }

    public void removeDeviceList(CMI_DM_EVENT.ByReference dev) {
        if (dev != null) {
            for (int i = 0; i < g_DeviceList.size(); i++) {
                CMI_DM_EVENT obj = g_DeviceList.get(i);
                if (obj != null) {
                    String obj_serialNumber = "";
                    String dev_serialNumber = "";
                    try {
                        obj_serialNumber = new String(obj.deviceInfo.serialNumber, "UTF-8");
                        obj_serialNumber = obj_serialNumber.trim();
                        dev_serialNumber = new String(dev.deviceInfo.serialNumber, "UTF-8");
                        dev_serialNumber = dev_serialNumber.trim();
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    if (obj_serialNumber.equals(dev_serialNumber)) {
                        System.out.println("remove g_DeviceList : " + dev_serialNumber);
                        g_DeviceList.remove(i);
                        break;
                    }
                }
            }
        }
    }

    public List<CMI_DM_EVENT.ByReference> getDeviceList() {
   //     System.out.println(">>Dvc lsi>>>"+g_DeviceList);
       
        return g_DeviceList;
    }

    public int getBmtSpecificMode() {
        return g_BmtSpecificMode;
    }

    public void setBmtSpecificMode(int value) {
        g_BmtSpecificMode = value;
    }

}
