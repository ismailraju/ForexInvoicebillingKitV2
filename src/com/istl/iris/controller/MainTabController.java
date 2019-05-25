package com.istl.iris.controller;

import com.istl.imageprocessing.Utils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import com.istl.iris.controller.MainController;
import com.istlbd.iris.cmi.CMILibrary;
import com.istlbd.iris.cmi.CMILibraryFactory;
import com.istlbd.iris.cmi.CMI_DEVICE_INFO;
import com.istlbd.iris.cmi.CMI_DM_EVENT;
import com.istlbd.iris.cmi.CMI_EVENT;
import java.awt.BorderLayout;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

import java.nio.IntBuffer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainTabController {

    private MainController _main;

    @FXML
    public AnchorPane MainTabPane;
    @FXML
    private Button MainTabBtnCancel;
    @FXML
//    private Button MainTabBtnClose;
//    @FXML
//    private Button MainTabBtnStart;
//    @FXML
//    private Button MainTabBtnCapture;
//    @FXML
//    private Button MainTabBtnStop;
//    @FXML
    private Button MainTabBtnSave;
//    @FXML
//    private ChoiceBox MainTabChboxDeviceList;
    @FXML
    public Label MainTabLblStatus;
    @FXML
    public Canvas leftEyeCanvas;
    @FXML
    public Canvas rightEyeCanvas;
//    @FXML
//    public RadioButton MainTabAutoRbtn;
//    @FXML
//    public RadioButton MainTabManualRbtn;
//    @FXML
//    public RadioButton MainTabFastAutoRbtn;
//    @FXML
//    public RadioButton MainTabUserRbtn;
//    @FXML
//    private ToggleGroup MainTabBmtModeGroup;
//    @FXML
//    private HBox MainTabBmtModeHBox;
//    @FXML
//    private Label MainTabModellbl;
    @FXML
    private Label MainTabRightIrisSizelbl;
    @FXML
    private Label MainTabLeftIrisSizelbl;
//    @FXML
//    private Label MainTabCpuUsagelbl;
    @FXML
    public Label MainTabDeviceUpSideDownlbl;
//    @FXML
//    private CheckBox MainTabContinuosCaptureCbx;

    //private String deviceSerial;
    ////try to kill
    public void open() {

        if (_main.getDeviceList().size() == 0) {
           MainTabDeviceUpSideDownlbl.setText("Device Not Found !");
           //device not found 
        }
        else
        {
            
        
        CMI_DM_EVENT.ByReference obj1 = _main.getDeviceList().get(0);

        String serialNumber = null;
        try {
            //  System.out.println("in open "+MainTabChboxDeviceList.getValue());
            String str = new String(obj1.deviceInfo.serialNumber, "UTF-8");
            serialNumber = str.trim();

        } catch (UnsupportedEncodingException unsupportedEncodingException) {
        }

        //String serialNumber = "BA1807A043415";//
        if (serialNumber == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("CMITECH");
            alert.setHeaderText("Error!");
            alert.setContentText("Fail to open device. Please check the connections");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }
            });

            return;
        }
        serialNumber = serialNumber.trim();

        if (true) {
            for (int i = 0; i < _main.getDeviceList().size(); i++) {

                CMI_DM_EVENT.ByReference obj = _main.getDeviceList().get(i);
                CMI_DEVICE_INFO dvInfo = null;
                CMI_DEVICE_INFO.ByReference dvInfoRef = new CMI_DEVICE_INFO.ByReference();

                if (obj != null) {
                    String devLst_serialNumber = "";
                    String devLst_ModelName = "";
                    try {
                        devLst_serialNumber = new String(obj.deviceInfo.serialNumber, "UTF-8");
                        devLst_ModelName = new String(obj.deviceInfo.modelName, "UTF-8");

                        if (devLst_serialNumber != null) {
                            devLst_serialNumber = devLst_serialNumber.trim();
                        }
                        if (devLst_ModelName != null) {
                            devLst_ModelName = devLst_ModelName.trim();
                        }
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (devLst_serialNumber.equals(serialNumber)) {
                        dvInfo = (CMI_DEVICE_INFO) obj.deviceInfo;
                        dvInfoRef.cbSize = dvInfo.cbSize;
                        System.arraycopy(dvInfo.firmwareRev, i, dvInfoRef.firmwareRev, i, dvInfo.firmwareRev.length);
                        System.arraycopy(dvInfo.hardwareRev, i, dvInfoRef.hardwareRev, i, dvInfo.hardwareRev.length);
                        System.arraycopy(dvInfo.modelName, i, dvInfoRef.modelName, i, dvInfo.modelName.length);
                        System.arraycopy(dvInfo.serialNumber, i, dvInfoRef.serialNumber, i, dvInfo.serialNumber.length);
                        System.out.println(">>check<<" + dvInfoRef.cbSize);
                        System.out.println(">>check device handle<<" + _main.getDeviceHandle());
                        // System.out.println("refrence values : "+);
                        int ret = CMILibraryFactory.getCMILibrary().cmi_openDevice(_main.getDeviceHandle(), dvInfoRef);

                        System.out.println("openDevice:" + ret);

                        if (ret == CMILibrary.CMI_SUCCESS) {

                            MainTabLblStatus.setText("Device opened successfully!");

                            //ret = cmiris.setBMTMode(_main.getDeviceHandle(), _main.getBmtSpecificMode());
                            ret = CMILibraryFactory.getCMILibrary().cmi_setBMTMode(_main.getDeviceHandle(), _main.getBmtSpecificMode());
                            System.out.println("setBMTMode:" + ret);

                            if (ret == CMILibrary.CMI_SUCCESS) {
                                setOpenCloseProc(true);
                                
                                // MainTabModellbl.setText(devLst_ModelName);
                            } else {
                                
                                setStatusBar(ret);
                            }
                        } else {
                            setStatusBar(ret);
                        }

                        break;
                    }
                }

            }

        } else {
            System.out.println("openDevice failed");
        }
        /// starting
        try {
            System.out.println("MainTabBtnStartClicked");

            clearLeftIrisDisplay();
            clearRightIrisDisplay();
            // preview frame buffer size
            IntBuffer len = IntBuffer.allocate(1);
//		int bufferSize = cmiris.getBufferSize(_main.getDeviceHandle(), 6, 0);
            CMILibraryFactory.getCMILibrary().cmi_getBufferSize(_main.getDeviceHandle(), 6, 0, len);
            // System.out.println(">>>bufferSize>>>"+len.get());
            int bufferSize = len.get(0);
            System.out.println(bufferSize);

            if (bufferSize > 0) {
                MainTabDeviceUpSideDownlbl.setText("");
                _main.g_LeftIrisBI = null;
                _main.g_RightIrisBI = null;
                if (_main.g_Buffer != null) {
                    _main.g_Buffer.clear();
                }

                _main.g_Buffer = ByteBuffer.allocateDirect(bufferSize);

                Pointer pbuf = Native.getDirectBufferPointer(_main.g_Buffer);

                //cmiris.setMaxXYMovement(_main.getDeviceHandle(), _main.g_ImageXYMovement);
                CMILibraryFactory.getCMILibrary().cmi_setMaxXYMovement(_main.getDeviceHandle(), (int) _main.g_ImageXYMovement);
                int leftRightMargin = (int) ((_main.g_CaptureLRIrisMargin * 100) + 0.5);
                int topBottomMargin = (int) ((_main.g_CaptureTBIrisMargin * 100) + 0.5);
                //cmiris.setIrisMargins(_main.getDeviceHandle(), leftRightMargin, leftRightMargin, topBottomMargin, topBottomMargin); //60,60,20,20
                CMILibraryFactory.getCMILibrary().cmi_setIrisMargins(_main.getDeviceHandle(), leftRightMargin, leftRightMargin, topBottomMargin, topBottomMargin);
                //cmiris.setWhichEye(_main.getDeviceHandle(), _main.g_CaptureEyes);
                CMILibraryFactory.getCMILibrary().cmi_setWhichEye(_main.getDeviceHandle(), _main.g_CaptureEyes);
                if (_main.g_CaptureGazeDetection) {
                    //  cmiris.setGazeDetectionEnabled(_main.getDeviceHandle(), 1);
                    CMILibraryFactory.getCMILibrary().cmi_setGazeDetectionEnabled(_main.getDeviceHandle(), 1);
                } else {
                    //cmiris.setGazeDetectionEnabled(_main.getDeviceHandle(), 0);
                    CMILibraryFactory.getCMILibrary().cmi_setGazeDetectionEnabled(_main.getDeviceHandle(), 0);
                }

                // int ret = cmiris.startCapture(_main.getDeviceHandle(), bufferSize, pbuf);
                int ret = CMILibraryFactory.getCMILibrary().cmi_startCapture(_main.getDeviceHandle(), bufferSize, pbuf);
                System.out.println("startCapture:" + ret);

                if (ret == CMILibrary.CMI_SUCCESS) {
                    MainTabLblStatus.setText("Capture started!");
                    setStartStopProc(true);

                } else {
                    setStartStopProc(false);
                    setStatusBar(ret);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        ///

    }

    private void setOpenCloseProc(boolean flag) {
        // System.out.println(flag);
        if (flag == true) // open
        {
            int currentSpecMode = getBmtSpecificMode();
            if (currentSpecMode == CMILibrary.CMI_BMT_MANUAL_MODE
                    || currentSpecMode == CMILibrary.CMI_BMT_MANUAL_LB_MODE
                    || currentSpecMode == CMILibrary.CMI_BMT_USER_MODE) {
                //MainTabBtnCapture.setDisable(true);
            } else {
                //MainTabBtnCapture.setDisable(true);
            }

            MainTabBtnCancel.setDisable(false);
            // MainTabBtnClose.setDisable(true);
            //MainTabBtnStart.setDisable(true);
            //MainTabBtnStop.setDisable(true);
            MainTabBtnSave.setDisable(true);
            //  MainTabChboxDeviceList.setDisable(false);

            //   MainTabBmtModeHBox.setDisable(true);
            //  MainTabChboxDeviceList.setDisable(false);
        } else // close
        {
            MainTabBtnCancel.setDisable(false);
            // MainTabBtnClose.setDisable(true);
            //MainTabBtnStart.setDisable(true);
            //MainTabBtnCapture.setDisable(true);
            //MainTabBtnStop.setDisable(true);
            MainTabBtnSave.setDisable(true);
            // MainTabChboxDeviceList.setDisable(true);

            //MainTabBmtModeHBox.setDisable(false);
            // MainTabChboxDeviceList.setDisable(false);
            // MainTabModellbl.setText("");
            MainTabRightIrisSizelbl.setText("");
            MainTabLeftIrisSizelbl.setText("");
            //MainTabCpuUsagelbl.setText("");
            MainTabDeviceUpSideDownlbl.setText("");

            GraphicsContext gc = leftEyeCanvas.getGraphicsContext2D();
            gc.clearRect(0, 0, leftEyeCanvas.getWidth(), leftEyeCanvas.getHeight());
            GraphicsContext gc2 = rightEyeCanvas.getGraphicsContext2D();
            gc2.clearRect(0, 0, rightEyeCanvas.getWidth(), rightEyeCanvas.getHeight());
        }
    }

    private void setStartStopProc(boolean flag) {
        int currentSpecMode = getBmtSpecificMode();
        if (currentSpecMode == CMILibrary.CMI_BMT_MANUAL_MODE
                || currentSpecMode == CMILibrary.CMI_BMT_MANUAL_LB_MODE
                || currentSpecMode == CMILibrary.CMI_BMT_USER_MODE) {
            //MainTabBtnCapture.setDisable(false);
        } else {
            //MainTabBtnCapture.setDisable(true);
        }

        if (flag) // start capture
        {

            //MainTabBtnStart.setDisable(true);
            //MainTabBtnStop.setDisable(true);
        } else // stop capture
        {
            //MainTabBtnStart.setDisable(true);
            //MainTabBtnStop.setDisable(true);

            MainTabDeviceUpSideDownlbl.setText("");

            GraphicsContext gc = leftEyeCanvas.getGraphicsContext2D();
            gc.clearRect(0, 0, leftEyeCanvas.getWidth(), leftEyeCanvas.getHeight());
            GraphicsContext gc2 = rightEyeCanvas.getGraphicsContext2D();
            gc2.clearRect(0, 0, rightEyeCanvas.getWidth(), rightEyeCanvas.getHeight());

        }
    }

    private void setSaveProc(boolean flag) {
        if (flag) // save enable
        {
            MainTabBtnSave.setDisable(false);
        } else {
            MainTabBtnSave.setDisable(true);
        }

    }

    @FXML
    private void MainTabBtnCancelClicked(ActionEvent event) throws IOException, Exception {

        System.out.println("MainTabBtnCloseClicked");

        clearLeftIrisDisplay();
        clearRightIrisDisplay();
        //MainTabModellbl.setText("");

         int r1 = CMILibraryFactory.getCMILibrary().cmi_clearDMEventQueue(_main.getDeviceHandle());
            int r2 = CMILibraryFactory.getCMILibrary().cmi_clearEventQueue(_main.getDeviceHandle());
            
            int r3 = CMILibraryFactory.getCMILibrary().cmi_clearEventQueue(_main.getDeviceHandle());
            int r4 = CMILibraryFactory.getCMILibrary().cmi_cancelPendingReadDMEvent(_main.getDeviceHandle());
            int r5 = CMILibraryFactory.getCMILibrary().cmi_cancelPendingReadEvent(_main.getDeviceHandle());
            int r = CMILibraryFactory.getCMILibrary().cmi_destroyCMIris(_main.getDeviceHandle());
            int ret = CMILibraryFactory.getCMILibrary().cmi_closeDevice(_main.getDeviceHandle());

            Stage stage = (Stage) MainTabBtnCancel.getScene().getWindow();
            stage.close();

    }


    @FXML
    private void MainTabBtnSaveClicked(ActionEvent event) throws Exception {
        System.out.println("MainTabBtnSaveClicked");
//        System.out.println(deviceSerial);
        if (_main.g_LeftIrisBI == null && _main.g_RightIrisBI == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("PDMS");
            alert.setHeaderText("Warning");
            alert.setContentText("Please capture first before save!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Please capture first before save!");
                }
            });
            return;
        }

       
        MainTabLblStatus.setText("Save Done!");

        clearLeftIrisDisplay();
        clearRightIrisDisplay();
        //MainTabModellbl.setText("");

        int r1 = CMILibraryFactory.getCMILibrary().cmi_clearDMEventQueue(_main.getDeviceHandle());
            int r2 = CMILibraryFactory.getCMILibrary().cmi_clearEventQueue(_main.getDeviceHandle());
            
            int r3 = CMILibraryFactory.getCMILibrary().cmi_clearEventQueue(_main.getDeviceHandle());
            int r4 = CMILibraryFactory.getCMILibrary().cmi_cancelPendingReadDMEvent(_main.getDeviceHandle());
            int r5 = CMILibraryFactory.getCMILibrary().cmi_cancelPendingReadEvent(_main.getDeviceHandle());
            int r = CMILibraryFactory.getCMILibrary().cmi_destroyCMIris(_main.getDeviceHandle());
            int ret = CMILibraryFactory.getCMILibrary().cmi_closeDevice(_main.getDeviceHandle());

            Stage stage = (Stage) MainTabBtnCancel.getScene().getWindow();
            stage.close();
    }

    public int getBmtSpecificMode() {
        int ret = CMILibrary.CMI_BMT_AUTO_MODE;

        return ret;
    }

    public void clearLeftIrisDisplay() {
        //System.out.println(">>>in>>");
        Canvas cv = leftEyeCanvas;
        GraphicsContext gc = cv.getGraphicsContext2D();
        gc.clearRect(0, 0, cv.getWidth(), cv.getHeight());
    }

    public void clearRightIrisDisplay() {
        // System.out.println(">>in>>");
        Canvas cv = rightEyeCanvas;
        GraphicsContext gc = cv.getGraphicsContext2D();
        gc.clearRect(0, 0, cv.getWidth(), cv.getHeight());
    }

    public void addMainTabChboxDeviceList(String val) {
        //MainTabChboxDeviceList.getItems().add(val);

        System.out.println("addMainTabChboxDeviceList serial : " + val);
    }

    public void clearMainTabChboxDeviceList() {
        // MainTabChboxDeviceList.getSelectionModel().clearSelection();
        // MainTabChboxDeviceList.getItems().clear();
    }

    public void selectFirstMainTabChboxDeviceList() {
        //MainTabChboxDeviceList.getSelectionModel().selectFirst();
        //System.out.println("selected" + MainTabChboxDeviceList.getValue());
    }

    public void setStatusBar(int ret) {
        if (ret == CMILibrary.CMI_SUCCESS) {
            MainTabLblStatus.setText("Device opened successfully!");
        } else if (ret == CMILibrary.CMI_ERROR_WAIT_TIMEOUT) {
            MainTabLblStatus.setText("Force capture timeout!");
        } else if (ret == CMILibrary.CMI_ERROR_INVALID_HANDLE) {
            MainTabLblStatus.setText("Error! Invalid Handle");
        } else if (ret == CMILibrary.CMI_ERROR_FAIL_TO_SEND_COMMAND) {
            MainTabLblStatus.setText("Error! Fail to send command to device.");
        } else if (ret == CMILibrary.CMI_ERROR_CALL_GET_BUFFER_SIZE_FIRST) {
            MainTabLblStatus.setText("Error! call cmi_getBufferSize() first!");
        } else if (ret == CMILibrary.CMI_ERROR_BUFFER_SIZE_TOO_SMALL) {
            MainTabLblStatus.setText("Error! buffer size is too small!");
        } else if (ret == CMILibrary.CMI_ERROR_CANNOT_FIND_DEVICE) {
            MainTabLblStatus.setText("Error! Cannot find the device!");
        } else if (ret == CMILibrary.CMI_ERROR_DEVICE_OPENED) {
            MainTabLblStatus.setText("Device has already opened!");
        } else if (ret == CMILibrary.CMI_ERROR_DEVICE_CLOSED) {
            MainTabLblStatus.setText("Device has not opened yet!");
        } else if (ret == CMILibrary.CMI_ERROR_DEVICE_STARTED) {
            MainTabLblStatus.setText("Device has already started!");
        } else if (ret == CMILibrary.CMI_ERROR_DEVICE_STOPPED) {
            MainTabLblStatus.setText("Capture has not started yet!");
        } else if (ret == CMILibrary.CMI_ERROR_DEVICE_UPSIDE_DOWN) {
            MainTabLblStatus.setText("Error! Device upside down!");
        } else if (ret == CMILibrary.CMI_ERROR_IN_ARGUMENTS) {
            MainTabLblStatus.setText("Error! open argument is NULL");
        } else if (ret == CMILibrary.CMI_ERROR_FAIL_TO_OPEN_IMAGER_DEVICE) {
            MainTabLblStatus.setText("Error! Fail to open device. Please check the connections");
        } else if (ret == CMILibrary.CMI_ERROR_EEPROM_READ_TIMEOUT) {
            MainTabLblStatus.setText("Error! Cannot read data from the device.");
        } else if (ret == CMILibrary.CMI_ERROR_EEPROM_VERSION_INVALID) {
            MainTabLblStatus.setText("Error! Device and S/W versions are not matched.");
        } else if (ret == CMILibrary.CMI_ERROR_CANNOT_ALLOC_MEMORY) {
            MainTabLblStatus.setText("Error! Cannot allocate Memory.");
        }
    }

    private void setMainTabDeviceUpSideDownlbl(int val) {
        if (val == 0) {
            MainTabDeviceUpSideDownlbl.setText("Device OK");
        } else {
            MainTabDeviceUpSideDownlbl.setText("Device upside down");
        }
    }

    private void setMainTabIrisDiameterlbl(double lval, double rval) {
        if (lval > 0) {
            MainTabLeftIrisSizelbl.setText("" + lval);
        } else {
            MainTabLeftIrisSizelbl.setText("");
        }

        if (rval > 0) {
            MainTabRightIrisSizelbl.setText("" + rval);
        } else {
            MainTabRightIrisSizelbl.setText("");
        }
    }

    private void continuosCapture() {

    }

    private void setCpuUsage(int val) {
        if (val == 1) {
            // MainTabCpuUsagelbl.setText("1%");
        } else {
            //MainTabCpuUsagelbl.setText((100 * val / 30) + "%");
        }
    }

    private static Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
        WritableImage image = new WritableImage(width, height);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read, null);
        } catch (IOException ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    public void init(MainController mainController) {

        System.out.println("Application(MainTabController) started");

        _main = mainController;

        readDMEventT eventDMT = new readDMEventT();
        eventDMT.start();

        readEventT eventT = new readEventT();
        eventT.start();
//        String n = (String) MainTabChboxDeviceList.getValue();//
//        System.out.println(" After readevent serial checking " + n);
        //MainTabModellbl.setText("");
        MainTabRightIrisSizelbl.setText("");
        MainTabLeftIrisSizelbl.setText("");

        //MainTabCpuUsagelbl.setText("");
        MainTabDeviceUpSideDownlbl.setText("");

//        MainTabAutoRbtn.setUserData("Auto");
//        MainTabAutoRbtn.setText("Auto");
//        MainTabManualRbtn.setUserData("Manual");
//        MainTabManualRbtn.setText("Manual");
//        MainTabFastAutoRbtn.setUserData("Fast Auto");
//        MainTabFastAutoRbtn.setText("Fast Auto");
//        MainTabUserRbtn.setUserData("User");
//        MainTabUserRbtn.setText("User");
//        MainTabBmtModeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//            public void changed(ObservableValue<? extends Toggle> ov,
//                    Toggle old_toggle, Toggle new_toggle) {
//                if (MainTabBmtModeGroup.getSelectedToggle() != null) {
//                    _main.setBmtSpecificMode(getBmtSpecificMode());
//                }
//            }
//        });
    }
//    public void setDevice(String str)
//    {
//        deviceSerial = str;
//        System.out.println("Serial"+deviceSerial+"<<<");
//    }
//     public String getDevice()
//    {
//        System.out.println("Serial "+deviceSerial+"get<<<");
//        return deviceSerial;
//        
//    }

    public class readDMEventT extends Thread {

        public void run() {
            try {
                while (true) {
                    // System.out.println(">>>read>>");
                    CMI_DM_EVENT.ByReference dmEvent = new CMI_DM_EVENT.ByReference();
                    //int ret = cmiris.readDMEvent(_main.getDeviceHandle(), dmevent);
                    int ret = CMILibraryFactory.getCMILibrary().cmi_readDMEvent(_main.getDeviceHandle(), dmEvent, 100);
                    // System.out.println(">>>ret>>"+ret);
                    if (ret == CMILibrary.CMI_SUCCESS) {
                        // System.out.println(">>>");
                        //System.out.println(">>>Device Information debug>>"+dmEvent);
                        _main.addDeviceList(dmEvent);

                        String serialNumber = new String(dmEvent.deviceInfo.serialNumber, "UTF-8").trim();
                        String firmwareRev = new String(dmEvent.deviceInfo.firmwareRev, "UTF-8");
                        String modelName = new String(dmEvent.deviceInfo.modelName, "UTF-8");
                        String hardwareRev = new String(dmEvent.deviceInfo.hardwareRev, "UTF-8");

                        System.out.println(" >> serialNumber:" + serialNumber);
                        System.out.println(" >> firmwareRev:" + firmwareRev);
                        System.out.println(" >> modelName:" + modelName);
                        System.out.println(" >> hardwareRev:" + hardwareRev);

                        if (serialNumber != null) {

                            Platform.runLater(() -> {
//                                System.out.println("com.istl.iris.controller.MainTabController.readDMEventT.run()");
//                                System.out.println(" >> serialNumber:" + serialNumber);
                                clearMainTabChboxDeviceList();
//                                System.out.println("com.istl.iris.controller.MainTabController.readDMEventT.run()");
//                                System.out.println(" >> serialNumber:" + serialNumber);
                                List<CMI_DM_EVENT.ByReference> devLst = _main.getDeviceList();
                                for (int i = 0; i < devLst.size(); i++) {
                                    CMI_DM_EVENT.ByReference obj = devLst.get(i);
                                    if (obj != null) {
                                        String serial = "";
                                        try {
                                            serial = new String(obj.deviceInfo.serialNumber, "UTF-8");
                                        } catch (UnsupportedEncodingException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                        serial = serial.trim();
//                                        deviceSerial = serial;
//                                        System.out.println("device serial number : " + deviceSerial);
                                        addMainTabChboxDeviceList(serial);

                                    }
                                }

                                selectFirstMainTabChboxDeviceList();

                                //  MainTabLblStatus.setText("SN:" + serialNumber + " is arrived");
                            });
                        }
                    } else if (ret == 6) {
                        _main.removeDeviceList(dmEvent);

                        String serialNumber = new String(dmEvent.deviceInfo.serialNumber, "UTF-8").trim();
                        String firmwareRev = new String(dmEvent.deviceInfo.firmwareRev, "UTF-8");
                        String modelName = new String(dmEvent.deviceInfo.modelName, "UTF-8");
                        String hardwareRev = new String(dmEvent.deviceInfo.hardwareRev, "UTF-8");

                        System.out.println(" >> serialNumber:" + serialNumber);
                        System.out.println(" >> firmwareRev:" + firmwareRev);
                        System.out.println(" >> modelName:" + modelName);
                        System.out.println(" >> hardwareRev:" + hardwareRev);
//                        System.out.println("com.istl.iris.controller.MainTabController.readDMEventT.run()");
//                        System.out.println(" >> serialNumber:" + serialNumber);
                        if (serialNumber != null) {
                            Platform.runLater(() -> {
                                clearMainTabChboxDeviceList();

                                List<CMI_DM_EVENT.ByReference> devLst = _main.getDeviceList();
                                for (int i = 0; i < devLst.size(); i++) {
                                    CMI_DM_EVENT.ByReference obj = devLst.get(i);
                                    if (obj != null) {
                                        String serial = "";
                                        try {
                                            serial = new String(obj.deviceInfo.serialNumber, "UTF-8");
                                        } catch (UnsupportedEncodingException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                        serial = serial.trim();
//                                        deviceSerial = serial;
//                                        System.out.println("device serial number : " + deviceSerial);
                                        addMainTabChboxDeviceList(serial);
                                        // setDevice(serial);
                                    }
                                }

                                selectFirstMainTabChboxDeviceList();

                                //MainTabLblStatus.setText("SN:" + serialNumber + " is removed");
                            });
                        }
                    }

                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    public class readEventT extends Thread {

        public void run() {
            try {
                boolean flag = false;
                double frame = 0;
                while (true) {
                    CMI_EVENT.ByReference ret_event = new CMI_EVENT.ByReference();
                    //int ret = cmiris.readEvent(_main.getDeviceHandle(), ret_event);
                    int ret = CMILibraryFactory.getCMILibrary().cmi_readEvent(_main.getDeviceHandle(), ret_event, 100);
                    if (ret == 0) {
                        if (ret_event.event == CMILibrary.CMI_EVENT_PREVIEW_FRAME_INFO) {
                            frame++;

                            int width = ret_event.imageInfo.leftWidth;
                            int height = ret_event.imageInfo.leftHeight;
                           // System.out.println("com.istl.iris.controller.MainTabController.readEventT.run()"+width+" "+height);
                            Pointer imgPtr = ret_event.imageInfo.leftImage;
                            if (width > 0 && height > 0) {
                                if (imgPtr != null) {
                                    if (_main.g_DisplayFrame) {
                                        if (frame % _main.g_DisplayFrameRate == 0) {
                                            byte[] imgBytes = imgPtr.getByteArray(0, width * height);
                                            
                                            BufferedImage convertedGrayscale = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
                                            convertedGrayscale.getRaster().setDataElements(0, 0, width, height, imgBytes);
                                            WritableImage img = new WritableImage(width, height);
                                            img = SwingFXUtils.toFXImage(convertedGrayscale, null);

                                            _main.setLImgFromDevice(img, ret_event);
                                           
                                        }
                                    }

                                    // cmiris.releasePreviewBuffer(_main.getDeviceHandle(), imgPtr);
                                    CMILibraryFactory.getCMILibrary().cmi_releasePreviewBuffer(_main.getDeviceHandle(), imgPtr);
                                }
                            }

                            width = ret_event.imageInfo.rightWidth;
                            height = ret_event.imageInfo.rightHeight;
                            imgPtr = ret_event.imageInfo.rightImage;
                            if (width > 0 && height > 0) {
                                if (imgPtr != null) {
                                    if (_main.g_DisplayFrame) {
                                        if (frame % _main.g_DisplayFrameRate == 0) {
                                            byte[] imgBytes = imgPtr.getByteArray(0, width * height);
                                           
                                            BufferedImage convertedGrayscale = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
                                            convertedGrayscale.getRaster().setDataElements(0, 0, width, height, imgBytes);
                                            WritableImage img = new WritableImage(width, height);
                                            img = SwingFXUtils.toFXImage(convertedGrayscale, null);

                                            _main.setRImgFromDevice(img, ret_event);
                                          
                                        }
                                    }

                                    // cmiris.releasePreviewBuffer(_main.getDeviceHandle(), imgPtr);
                                    CMILibraryFactory.getCMILibrary().cmi_releasePreviewBuffer(_main.getDeviceHandle(), imgPtr);
                                }
                            }

                            Platform.runLater(() -> {
                                setMainTabDeviceUpSideDownlbl(ret_event.imageInfo.isDeviceUpSideDown);
                                setCpuUsage(ret_event.imageInfo.avgProcessingTime);
                            });
                        } else if (ret_event.event == CMILibrary.CMI_EVENT_IRIS_IMAGES_SELECTED) {
                            IntByReference whichEyeRef = new IntByReference();
                            //ret = cmiris.getWhichEye(_main.getDeviceHandle(), whichEyeRef);
                            ret = CMILibraryFactory.getCMILibrary().cmi_getWhichEye(_main.getDeviceHandle(), whichEyeRef);
                            if (ret == CMILibrary.CMI_SUCCESS) {
                                int whichEye = whichEyeRef.getValue();
                                int lret = whichEye & CMILibrary.CMI_LEFT_EYE;
                                int rret = whichEye & CMILibrary.CMI_RIGHT_EYE;
                                if ((lret > 0 && ret_event.imageInfo.leftImage == null) || (rret > 0 && ret_event.imageInfo.rightImage == null)) {
                                    Platform.runLater(() -> {
                                        MainTabLblStatus.setText("Error! Fail to capture images. Please try again.");
                                    });
                                    continue;
                                }
                            }

                            int width = ret_event.imageInfo.leftWidth;
                            int height = ret_event.imageInfo.leftHeight;
                            Pointer imgPtr = ret_event.imageInfo.leftImage;
                            if (width > 0 && height > 0) {
                                if (imgPtr != null) {
                                    byte[] imgBytes = imgPtr.getByteArray(0, width * height);
                                     _main.biometric.setIrisLeft(Utils.rawToBmpIris(imgBytes, width, height));
                                    _main.g_LeftIrisBI = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
                                    _main.g_LeftIrisBI.getRaster().setDataElements(0, 0, width, height, imgBytes);
                                    WritableImage img = new WritableImage(width, height);
                                    img = SwingFXUtils.toFXImage(_main.g_LeftIrisBI, null);

                                    if (_main.g_CaptureAutoSave) {
                                        ImageIO.write(_main.g_LeftIrisBI, "bmp", new File("./leftimage.bmp"));
                                    }

                                    _main.setLImgFromDevice(img, ret_event);
                                }
                            }

                            width = ret_event.imageInfo.rightWidth;
                            height = ret_event.imageInfo.rightHeight;
                            imgPtr = ret_event.imageInfo.rightImage;
                            if (width > 0 && height > 0) {
                                if (imgPtr != null) {
                                    byte[] imgBytes = imgPtr.getByteArray(0, width * height);
                                   _main.biometric.setIrisRight(Utils.rawToBmpIris(imgBytes, width, height));
                                    _main.g_RightIrisBI = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
                                    _main.g_RightIrisBI.getRaster().setDataElements(0, 0, width, height, imgBytes);
                                    WritableImage img = new WritableImage(width, height);
                                    img = SwingFXUtils.toFXImage(_main.g_RightIrisBI, null);

                                    if (_main.g_CaptureAutoSave) {
                                        ImageIO.write(_main.g_RightIrisBI, "bmp", new File("./rightimage.bmp"));
                                    }

                                    _main.setRImgFromDevice(img, ret_event);
                                }
                            }

                            Platform.runLater(() -> {
                                double leftirisDiameter = ret_event.imageInfo.leftIrisRadius * 2;
                                double rightirisDiameter = ret_event.imageInfo.rightIrisRadius * 2;

                                setMainTabIrisDiameterlbl(leftirisDiameter, rightirisDiameter);
                            });
                        } else if (ret_event.event == CMILibrary.CMI_EVENT_IRIS_IMAGE_UNSELECTED) {
                            Platform.runLater(() -> {
                                setStartStopProc(false);
                                setSaveProc(false);
                                System.out.println("CMI_EVENT_IRIS_IMAGE_UNSELECTED");
                            });
                        } else if (ret_event.event == CMILibrary.CMI_EVENT_CAPTURE_DONE) {
                            Platform.runLater(() -> {
                                setStartStopProc(false);
                                setSaveProc(true);
                                continuosCapture();
                                System.out.println("CMI_EVENT_CAPTURE_DONE");
                            });
                        }
                    }

                    Thread.sleep(10);
                }
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }
}
