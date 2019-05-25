package com.istl.fingerprint.controller;

import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istl.fingerprint.data.FingerprintData;
import com.istl.fingerprint.segmentation.FPSegment;
import com.istl.fingerprintpattern.FingerprintContext;
import com.istl.fingerprintpattern.FingerprintSegmentation;
import com.istl.fingerprintpattern.GBNFIQ2_ImageQuality;
import com.istl.imageprocessing.Utils;
import com.istl.tbs.capture.api.TATVIKImageType;
import com.istl.tbs.capture.api.TatvikFpNative;
import com.istl.tbs.capture.api.TatvikFpNative.TBSCaptureData;
import com.istl.tbs.capture.api.callback.PreviewImageCallbackImplementation;
import com.istl.tbs.capture.api.callback.ResultImageCallbackImplementation;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FingerprintScanController implements Initializable {

    @FXML
    private ImageView viewFingerprint;
    @FXML
    private ImageView leftlt;
    @FXML
    private ImageView leftring;
    @FXML
    private ImageView leftmdl;
    @FXML
    private ImageView leftindex;
    @FXML
    private ImageView leftthumb;
    @FXML
    private ImageView rightthumb;
    @FXML
    private ImageView rightindex;
    @FXML
    private ImageView rightmdl;
    @FXML
    private ImageView rightring;
    @FXML
    private ImageView rightltl;
    @FXML
    private ImageView icone1;
    @FXML
    private ImageView icone2;
    @FXML
    private ImageView icone3;
    @FXML
    private ImageView icone4;
    @FXML
    private ImageView icone5;
    @FXML
    private ImageView icone6;
    @FXML
    private ImageView icone7;
    @FXML
    private ImageView icone8;
    @FXML
    private ImageView icone9;
    @FXML
    private ImageView icone10;
    @FXML
    private ImageView icone11;
    @FXML
    private ImageView icone12;
    @FXML
    private ImageView icone13;
    @FXML
    private ImageView icone14;
    @FXML
    private ImageView icone15;
    @FXML
    private ImageView icone16;
    @FXML
    private ImageView icone17;
    @FXML
    private ImageView icone18;
    @FXML
    private ImageView icone19;
    @FXML
    private ImageView icone20;

    private Stage dialogStage;
    EnrollPersonBiometric biometrics = new EnrollPersonBiometric();

    private ScheduledExecutorService timer;
    byte[] serialNumber = new byte[15];
    int nFingerCount;
    int fingerTyp;
    int nRetValue = -1;
    int g_context = 1;
    TBSCaptureData g_resultData = null;
    boolean g_bCaptureCompleted = false;
//    public PreviewImageCallbackImplementation g_previewImgCallbk = new PreviewImageCallbackImplementation();
//    public ResultImageCallbackImplementation g_resultImgCallbk = new ResultImageCallbackImplementation();
    public PreviewImageCallbackImplementation g_previewImgCallbk;
    public ResultImageCallbackImplementation g_resultImgCallbk;
    private FPSegment[] segData;
    Thread t = new Thread();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        g_previewImgCallbk = new PreviewImageCallbackImplementation();
        g_resultImgCallbk = new ResultImageCallbackImplementation();
        System.out.println("INITIALIZING IN FINGER");
        nRetValue = TatvikFpNative.myinstance.tbsIsScannerConnected();
        if (TatvikFpNative.TATVIK_STATUS_OK != nRetValue) {
            System.out.println("not connected");
        } else {
            System.out.println("Dervice is connected");
        }
        nRetValue = TatvikFpNative.myinstance.tbsFingerPrintInit();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setbiometric(EnrollPersonBiometric biometrics) {
        this.biometrics = biometrics;
    }

    public void SetSegDataLeft() throws IOException, InterruptedException {
        System.out.println(">>dataleft in");
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_LEFT_FINGERS == FingerprintData.getCaptureData().fingerPosition) {
         //   if (segData[0].getScore() >= 50) {
                System.out.println(">>>>>>>>>Left Smale>>>>" + segData[0].getScore());
                biometrics.setWsqLs(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                leftlt.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
          //  } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
          //  }

          //  if (segData[1].getScore() >= 50) {
                System.out.println(">>>>>>>>>Left ring>>>>" + segData[1].getScore());
                biometrics.setWsqLr(Utils.rawToBmp(segData[1].getSegData(), segData[1].getW(), segData[1].getH()));
                leftring.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[1].getSegData(), segData[1].getW(), segData[1].getH()), null));
          //  } else {
                System.out.println(">>LOW Score>>>" + segData[1].getScore());
          //  }

          //  if (segData[2].getScore() >= 50) {
          System.out.println(">>>>>>>>>Left Midle>>>>" + segData[2].getScore());
                biometrics.setWsqLm(Utils.rawToBmp(segData[2].getSegData(), segData[2].getW(), segData[2].getH()));
                leftmdl.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[2].getSegData(), segData[2].getW(), segData[2].getH()), null));
          //  } else {
                System.out.println(">>LOW Score>>>" + segData[2].getScore());
         //   }

            if (segData[3].getScore() >= 55) {
                System.out.println(">>>>>>>>>Left Index>>>>" + segData[3].getScore());
                biometrics.setWsqLi(Utils.rawToBmp(segData[3].getSegData(), segData[3].getW(), segData[3].getH()));
                leftindex.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[3].getSegData(), segData[3].getW(), segData[3].getH()), null));
            } else {
                System.out.println(">>LOW Score>>>" + segData[3].getScore());
            }

            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            FingerprintData.captureData = null;
            leftFingerDone();
            System.out.println(">>dataleft out");
        }
    }

    public void SetSegDataRight() throws IOException, InterruptedException {
        System.out.println("right data in>>>>");
        this.segData = FingerprintData.getSegment();

        if (TATVIKImageType.TATVIK_FLAT_RIGHT_FINGERS == FingerprintData.getCaptureData().fingerPosition) {
            if (segData[0].getScore() >= 55) {
                System.out.println(">>>>>>>>>rIGHT iNDEX>>>>" + segData[0].getScore());
                biometrics.setWsqRi(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                rightindex.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
            } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
            }

           // if (segData[1].getScore() >= 50) {
           System.out.println(">>>>>>>>>rIGHT MDL>>>>" + segData[1].getScore());
                biometrics.setWsqRm(Utils.rawToBmp(segData[1].getSegData(), segData[1].getW(), segData[1].getH()));
                rightmdl.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[1].getSegData(), segData[1].getW(), segData[1].getH()), null));
          //  } else {
                System.out.println(">>LOW Score>>>" + segData[1].getScore());
          //  }

           // if (segData[2].getScore() >= 50) {
           System.out.println(">>>>>>>>>Right Ring>>>>" + segData[2].getScore());
                biometrics.setWsqRr(Utils.rawToBmp(segData[2].getSegData(), segData[2].getW(), segData[2].getH()));
                rightring.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[2].getSegData(), segData[2].getW(), segData[2].getH()), null));
          //  } else {
                System.out.println(">>LOW Score>>>" + segData[2].getScore());
          //  }

           // if (segData[3].getScore() >= 50) {
           System.out.println(">>>>>>>>>Right Smale Smale>>>>" + segData[3].getScore());
                biometrics.setWsqRs(Utils.rawToBmp(segData[3].getSegData(), segData[3].getW(), segData[3].getH()));
                rightltl.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[3].getSegData(), segData[3].getW(), segData[3].getH()), null));
          //  } else {
                System.out.println(">>LOW Score>>>" + segData[3].getScore());
          //  }

            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            FingerprintData.captureData = null;
            rightFingerDone();
            System.out.println("right data out>>>>");
        }
    }

    public void SetSegDataThumb() throws IOException, InterruptedException {
        System.out.println("thumb data in>>>>");
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_TWO_THUMBS == FingerprintData.getCaptureData().fingerPosition) {
            if (segData[0].getScore() >= 55) {
                System.out.println(">>>>>>>>>Left Thumb>>>>" + segData[0].getScore());
                biometrics.setWsqLt(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                leftthumb.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
            } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
            }

            if (segData[1].getScore() >= 55) {
                System.out.println(">>>>>>>>>Right Thumb>>>>" + segData[1].getScore());
                biometrics.setWsqRt(Utils.rawToBmp(segData[1].getSegData(), segData[1].getW(), segData[1].getH()));
                rightthumb.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[1].getSegData(), segData[1].getW(), segData[1].getH()), null));
            } else {
                System.out.println(">>LOW Score>>>" + segData[1].getScore());
            }

            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            FingerprintData.captureData = null;
            twoThumbDone();
            System.out.println("thumb data out>>>>");
        }
    }

    public void SetLeftSmale() throws IOException, InterruptedException {
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_LEFT_SMALL == FingerprintData.getCaptureData().fingerPosition) {
            System.out.println(">>l1>>");
          //  if (segData[0].getScore() >= 50) {
                System.out.println(">>>>>>>>>find>>>>" + segData[0].getScore());
                biometrics.setWsqLs(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                leftlt.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
           // } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
          //  }

            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            seticone1Done();
            FingerprintData.setSegment(null);
        }
    }

    public void SetLeftRing() throws IOException, InterruptedException {
        System.out.println(">>start in seg");
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_LEFT_RING == FingerprintData.getCaptureData().fingerPosition) {
            System.out.println(">>l2>>");
           // if (segData[0].getScore() >= 55) {
                System.out.println(">>>>>>>>>find>>>>" + segData[0].getScore());
                biometrics.setWsqLr(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                leftring.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
          //  } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
         //   }
            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            seticone2Done();
            FingerprintData.setSegment(null);
        }
    }

    public void SetLeftMiddle() throws IOException, InterruptedException {
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_LEFT_MIDDLE == FingerprintData.getCaptureData().fingerPosition) {
            System.out.println(">>l3>>");
          //  if (segData[0].getScore() >= 50) {
                System.out.println(">>>>>>>>>find>>>>" + segData[0].getScore());
                biometrics.setWsqLm(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                leftmdl.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
           // } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
          //  }

            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            seticone3Done();
            FingerprintData.setSegment(null);
        }
    }

    public void setLeftIndex() throws IOException, InterruptedException {
        System.out.println(">>in seg left");
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_LEFT_INDEX == FingerprintData.getCaptureData().fingerPosition) {
            System.out.println(">>l4>>");
            if (segData[0].getScore() >= 55) {
                System.out.println(">>>>>>>>>find>>>>" + segData[0].getScore());
                biometrics.setWsqLi(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                leftindex.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
            } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
            }

            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            seticone4Done();
            FingerprintData.setSegment(null);
            System.out.println(">>out seg");
        }
    }

    public void SetLeftThumb() throws IOException, InterruptedException {
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_LEFT_THUMB == FingerprintData.getCaptureData().fingerPosition) {
            System.out.println(">>l5>>");
            if (segData[0].getScore() >= 55) {
                System.out.println(">>>>>>>>>find>>>>" + segData[0].getScore());
                biometrics.setWsqLt(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                leftthumb.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
            } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
            }

            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            seticone5Done();
            FingerprintData.setSegment(null);
        }
    }

    public void SetRightThumb() throws IOException, InterruptedException {
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_RIGHT_THUMB == FingerprintData.getCaptureData().fingerPosition) {
            System.out.println(">>l6>>");
            if (segData[0].getScore() >= 55) {
                System.out.println(">>>>>>>>>find>>>>" + segData[0].getScore());
                biometrics.setWsqRt(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                rightthumb.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
            } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
            }

            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            seticone6Done();
            FingerprintData.setSegment(null);
        }
    }

    public void SetRightIndex() throws IOException, InterruptedException {
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_RIGHT_INDEX == FingerprintData.getCaptureData().fingerPosition) {
            System.out.println(">>l7>>");
            if (segData[0].getScore() >= 55) {
                System.out.println(">>>>>>>>>find>>>>" + segData[0].getScore());
                biometrics.setWsqRi(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                rightindex.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
            } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
            }
            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            seticone7Done();
            FingerprintData.setSegment(null);
        }
    }

    public void SetRightMiddle() throws IOException, InterruptedException {
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_RIGHT_MIDDLE == FingerprintData.getCaptureData().fingerPosition) {
            System.out.println(">>l8>>");
          //  if (segData[0].getScore() >= 50) {
                System.out.println(">>>>>>>>>find>>>>" + segData[0].getScore());
                biometrics.setWsqRm(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                rightmdl.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
          //  } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
         //   }

            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            seticone8Done();
            FingerprintData.setSegment(null);
        }
    }

    public void SetRightRing() throws IOException, InterruptedException {
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_RIGHT_RING == FingerprintData.getCaptureData().fingerPosition) {
            System.out.println(">>l9>>");
           // if (segData[0].getScore() >= 50) {
                System.out.println(">>>>>>>>>find>>>>" + segData[0].getScore());
                biometrics.setWsqRr(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                rightring.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
          //  } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
          //  }
            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            seticone9Done();
            FingerprintData.setSegment(null);
        }
    }

    public void SetRightSmale() throws IOException, InterruptedException {
        this.segData = FingerprintData.getSegment();
        if (TATVIKImageType.TATVIK_FLAT_RIGHT_SMALL == FingerprintData.getCaptureData().fingerPosition) {
            System.out.println(">>l10>>");
         //   if (segData[0].getScore() >= 50) {
                System.out.println(">>>>>>>>>find>>>>" + segData[0].getScore());
                biometrics.setWsqRs(Utils.rawToBmp(segData[0].getSegData(), segData[0].getW(), segData[0].getH()));
                rightltl.setImage(SwingFXUtils.toFXImage(Utils.ByteArraytoBufferedImage(segData[0].getSegData(), segData[0].getW(), segData[0].getH()), null));
           // } else {
                System.out.println(">>LOW Score>>>" + segData[0].getScore());
          //  }
            TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
            removeArro();
            FingerprintData.setImage(null);
            seticone10Done();
            FingerprintData.setSegment(null);
        }
    }

    private void updateImageView(ImageView view, Image image) {
        onFXThread(view.imageProperty(), image);
    }

    public static <T> void onFXThread(final ObjectProperty<T> property, final T value) {
        Platform.runLater(() -> {
            property.set(value);
        });
    }

    @FXML
    protected void leftAction(ActionEvent event) {
        try {
            setLeftFinger();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 4;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_LEFT_FINGERS;
                System.out.println(">>>capture");
                captureFingerprint();
                System.out.println("live");
                liveView();
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {

                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println("CALLBACK IN LOOP>>>>>>>> " + g_resultImgCallbk.isCompleted());
                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println(">>>>>>>" + b);

                        boolean c = false;
                        if (b) {//GBNFIQ2_ImageQuality
                            // FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println(">>>>>>>>" + c);

                        }
                        System.out.println(">>seg in>>");
                        SetSegDataLeft();
                        System.out.println("segout>>");
                        g_resultImgCallbk.setCompleted(false);
                        System.out.println("setup false");
                        LiveEnd();
                        System.out.println("end");
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void rightAction(ActionEvent event) {
        try {
            setRightFinger();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 4;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_RIGHT_FINGERS;
                System.out.println("1");
                captureFingerprint();
                liveView();
                System.out.println("2");
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {
                        System.out.println("running>>>>>>>>>>left>>>>>>>>>>>>");
                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>>>>>>");

                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println(">>" + b);
                        boolean c = false;
                        if (b) {
                            //FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println(">>" + c);
                        }

                        System.out.println("seg");
                        SetSegDataRight();
                        System.out.println("segend");
                        g_resultImgCallbk.setCompleted(false);
                        System.out.println("4");
                        LiveEnd();
                        System.out.println("5");
                        System.out.println("end");
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void thumbAction(ActionEvent event) throws IOException {
        try {
            settwoThumbs();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                nFingerCount = 2;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_TWO_THUMBS;
                System.out.println("1");
                captureFingerprint();
                System.out.println("2");
                liveView();
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {

                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>");
                        }

                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println("" + b);
                        boolean c = false;
                        if (b) {
                            // FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println("" + c);
                        }
                        System.out.println("Done>>>>>>>>>>>>>>>>>>");
                        SetSegDataThumb();
                        System.out.println("seg done");
                        g_resultImgCallbk.setCompleted(false);
                        System.out.println("4");
                        LiveEnd();
                        System.out.println("end");
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }

        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void icon1Action(MouseEvent event) {
        try {
            System.out.println(">>>>>");
            seticone1();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 1;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_LEFT_SMALL;
                System.out.println("1");
                captureFingerprint();
                System.out.println("2");
                liveView();
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {
                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>>>>>>");
                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println(">>b" + b);
                        boolean c = false;
                        if (b) {
                            // FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println(">>>" + c);
                        }
                        System.out.println(">>start");
                        SetLeftSmale();
                        System.out.println("end");
                        g_resultImgCallbk.setCompleted(false);
                        LiveEnd();
                        System.out.println(">>end");
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void icon2Action(MouseEvent event) {
        try {
            seticone2();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 1;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_LEFT_RING;
                System.out.println("1");
                captureFingerprint();
                System.out.println("2");
                liveView();
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {
                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>>>>>>");
                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println("b" + b);
                        boolean c = false;
                        if (b) {
                            // FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println("c" + c);
                        }
                        System.out.println(">>start");
                        SetLeftRing();
                        System.out.println(">>end");
                        g_resultImgCallbk.setCompleted(false);
                        LiveEnd();
                        System.out.println("end");
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }

    }

    @FXML
    protected void icon3Action(MouseEvent event) {
        try {
            seticone3();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 1;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_LEFT_MIDDLE;
                System.out.println(">>>1");
                captureFingerprint();
                System.out.println(">>>2");
                liveView();
                System.out.println(">>>3");
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {
                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>>>>>>");
                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println(">>" + b);
                        boolean c = false;
                        if (b) {
                            // FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println(">>" + c);
                        }
                        System.out.println(">>in");
                        SetLeftMiddle();
                        System.out.println(">>out");
                        g_resultImgCallbk.setCompleted(false);
                        LiveEnd();
                        System.out.println(">>end");
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void icon4Action(MouseEvent event) {
        try {
            seticone4();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 1;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_LEFT_INDEX;
                System.out.println(">>1");
                captureFingerprint();
                System.out.println(">>2");
                liveView();
                System.out.println(">>3");
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {
                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>>>>>>");
                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println(">>>" + b);
                        boolean c = false;
                        if (b) {
                            //  FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println(">>c" + c);
                        }
                        System.out.println("start");
                        setLeftIndex();
                        System.out.println("end");
                        g_resultImgCallbk.setCompleted(false);
                        LiveEnd();
                        System.out.println("end");
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void icon5Action(MouseEvent event) {
        try {
            seticone5();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 1;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_LEFT_THUMB;
                System.out.println(">>1");
                captureFingerprint();
                System.out.println(">>>2");
                liveView();
                System.out.println(">>>3");
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {
                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>>>>>>");
                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println(">>" + b);
                        boolean c = false;
                        if (b) {
                            //  FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println(">>" + c);
                        }
                        System.out.println(">>start");
                        SetLeftThumb();
                        System.out.println("end>>");
                        g_resultImgCallbk.setCompleted(false);
                        LiveEnd();
                        System.out.println("end>>>>");
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void icon6Action(MouseEvent event) {
        try {
            seticone6();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 1;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_RIGHT_THUMB;
                System.out.println(">>1");
                captureFingerprint();
                System.out.println(">>2");
                liveView();
                System.out.println(">>3");
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {
                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>>>>>>");
                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println(">>b" + b);
                        boolean c = false;
                        if (b) {
                            // FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println(">>c" + c);
                        }
                        System.out.println("start");
                        SetRightThumb();
                        System.out.println("end");
                        g_resultImgCallbk.setCompleted(false);
                        LiveEnd();
                        System.out.println("end");
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void icon7Action(MouseEvent event) {
        try {
            seticone7();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 1;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_RIGHT_INDEX;
                System.out.println("1");
                captureFingerprint();
                System.out.println("2");
                liveView();
                System.out.println("3");
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {
                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>>>>>>");
                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println(">>" + b);
                        boolean c = false;
                        if (b) {
                            // FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println("c>>" + c);
                        }
                        SetRightIndex();
                        g_resultImgCallbk.setCompleted(false);
                        LiveEnd();
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void icon8Action(MouseEvent event) {
        try {
            seticone8();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 1;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_RIGHT_MIDDLE;
                captureFingerprint();
                System.out.println(">>1");
                liveView();
                System.out.println(">>2");
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {
                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>>>>>>");
                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println(">>b" + b);
                        boolean c = false;
                        if (b) {
                            //FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println(">>c" + c);
                        }
                        System.out.println(">>>start");
                        SetRightMiddle();
                        System.out.println(">>end");
                        g_resultImgCallbk.setCompleted(false);
                        LiveEnd();
                        System.out.println(">>end");
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void icon9Action(MouseEvent event) {
        try {
            seticone9();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 1;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_RIGHT_RING;
                System.out.println("1");
                captureFingerprint();
                System.out.println("2");
                liveView();
                System.out.println("3");
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {
                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>>>>>>");

                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());

                        boolean b = context.executeStrategy();
                        System.out.println(">>" + b);
                        boolean c = false;
                        if (b) {
                            //  FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println(">>c" + c);
                        }
                        System.out.println(">>>start");
                        SetRightRing();
                        System.out.println(">>end");
                        g_resultImgCallbk.setCompleted(false);
                        LiveEnd();
                        System.out.println(">>end");
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void icon10Action(MouseEvent event) {
        try {
            seticone10();
            if (TatvikFpNative.TATVIK_STATUS_OK == nRetValue) {
                System.out.println("Initialized");
                nFingerCount = 1;
                fingerTyp = TATVIKImageType.TATVIK_FLAT_RIGHT_SMALL;
                System.out.println(">>1");
                captureFingerprint();
                System.out.println(">>2");
                liveView();
                System.out.println(">>3");
                Task task = new Task<Void>() {
                    @Override
                    public Void call() throws IOException, InterruptedException {
                        while (!g_resultImgCallbk.isCompleted()) {
                            System.out.println(">>>>>>>>>");
                        }
                        FingerprintContext context = new FingerprintContext(new FingerprintSegmentation());
                        boolean b = context.executeStrategy();
                        System.out.println(">>" + b);
                        boolean c = false;
                        if (b) {
                            //  FingerprintContext contexts = new FingerprintContext(new FingerprintQualityScore());
                            FingerprintContext contexts = new FingerprintContext(new GBNFIQ2_ImageQuality());
                            c = contexts.executeStrategy();
                            System.out.println(">>>" + c);
                        }
                        SetRightSmale();
                        g_resultImgCallbk.setCompleted(false);
                        LiveEnd();
                        return null;
                    }
                };
                t = new Thread(task);
                t.start();
            }
        } catch (Exception exc) {
            System.out.println("Device initialization error. " + exc.getMessage());
        }
    }

    @FXML
    protected void saveAction(ActionEvent event) {
        FingerprintData.captureData = null;
        FingerprintData.image = null;
        FingerprintData.isDone = false;
        TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
        TatvikFpNative.myinstance.tbsFingerPrintRelease();
        dialogStage.close();

    }

    @FXML
    protected void closeAction(ActionEvent event) {
        FingerprintData.captureData = null;
        FingerprintData.image = null;
        FingerprintData.isDone = false;
        TatvikFpNative.myinstance.tbsFingerPrintCaptureAbort();
        TatvikFpNative.myinstance.tbsFingerPrintRelease();
        dialogStage.close();
    }

    public void captureFingerprint() {
        try {
            nRetValue = TatvikFpNative.myinstance.tbsSetCaptureCallbackFunctions(g_previewImgCallbk, g_resultImgCallbk, g_context);
            nRetValue = TatvikFpNative.myinstance.tbsFingerPrintCaptureStart(nFingerCount, fingerTyp, true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void liveView() {
        Runnable frameGrabber;
        frameGrabber = new Runnable() {
            @Override
            public void run() {
                try {

                    BufferedImage bimage = FingerprintData.getImage();
                    if (bimage != null) {
                        Image imageToShow = SwingFXUtils.toFXImage(bimage, null);
                        updateImageView(viewFingerprint, imageToShow);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        this.timer = Executors.newSingleThreadScheduledExecutor();
        this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
    }

    public void LiveEnd() {
        if (this.timer != null && !this.timer.isShutdown()) {
            try {
                // stop the timer
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                // log any exception
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }
        }

    }

    public void setLeftFinger() {
        System.out.println(">>>>>left");
        icone1.setImage(new Image("/resource/arrow.gif"));
        icone2.setImage(new Image("/resource/arrow.gif"));
        icone3.setImage(new Image("/resource/arrow.gif"));
        icone4.setImage(new Image("/resource/arrow.gif"));
        icone5.setImage(null);
        icone6.setImage(null);
        icone7.setImage(null);
        icone8.setImage(null);
        icone9.setImage(null);
        icone10.setImage(null);
    }

    public void leftFingerDone() {

        icone11.setImage(new Image("/resource/l_small_green.gif"));
        icone12.setImage(new Image("/resource/l_ring_green.gif"));
        icone13.setImage(new Image("/resource/l_middle_green.gif"));
        icone14.setImage(new Image("/resource/l_index_green.gif"));

    }

    public void rightFingerDone() {

        icone17.setImage(new Image("/resource/r_index_green.gif"));
        icone18.setImage(new Image("/resource/r_middle_green.gif"));
        icone19.setImage(new Image("/resource/r_ring_green.gif"));
        icone20.setImage(new Image("/resource/r_small_green.gif"));
    }

    public void twoThumbDone() {

        icone15.setImage(new Image("/resource/l_thumb_green.gif"));
        icone16.setImage(new Image("/resource/r_thumb_green.gif"));

    }

    public void seticone1Done() {
        icone11.setImage(new Image("/resource/l_small_green.gif"));

    }

    public void seticone2Done() {
        icone12.setImage(new Image("/resource/l_ring_green.gif"));

    }

    public void seticone3Done() {
        icone13.setImage(new Image("/resource/l_middle_green.gif"));

    }

    public void seticone4Done() {
        icone14.setImage(new Image("/resource/l_index_green.gif"));

    }

    public void seticone5Done() {
        icone15.setImage(new Image("/resource/l_thumb_green.gif"));

    }

    public void seticone6Done() {
        icone16.setImage(new Image("/resource/r_thumb_green.gif"));

    }

    public void seticone7Done() {
        icone17.setImage(new Image("/resource/r_index_green.gif"));

    }

    public void seticone8Done() {
        icone18.setImage(new Image("/resource/r_middle_green.gif"));

    }

    public void seticone9Done() {
        icone19.setImage(new Image("/resource/r_ring_green.gif"));

    }

    public void seticone10Done() {
        icone20.setImage(new Image("/resource/r_small_green.gif"));

    }

    public void seticone1() {
        icone1.setImage(new Image("/resource/arrow.gif"));
        icone2.setImage(null);
        icone3.setImage(null);
        icone4.setImage(null);
        icone5.setImage(null);
        icone6.setImage(null);
        icone7.setImage(null);
        icone8.setImage(null);
        icone9.setImage(null);
        icone10.setImage(null);
    }

    public void seticone2() {
        icone2.setImage(new Image("/resource/arrow.gif"));
        icone1.setImage(null);
        icone3.setImage(null);
        icone4.setImage(null);
        icone5.setImage(null);
        icone6.setImage(null);
        icone7.setImage(null);
        icone8.setImage(null);
        icone9.setImage(null);
        icone10.setImage(null);
    }

    public void seticone3() {
        icone3.setImage(new Image("/resource/arrow.gif"));
        icone2.setImage(null);
        icone1.setImage(null);
        icone4.setImage(null);
        icone5.setImage(null);
        icone6.setImage(null);
        icone7.setImage(null);
        icone8.setImage(null);
        icone9.setImage(null);
        icone10.setImage(null);
    }

    public void seticone4() {
        icone4.setImage(new Image("/resource/arrow.gif"));
        icone2.setImage(null);
        icone3.setImage(null);
        icone1.setImage(null);
        icone5.setImage(null);
        icone6.setImage(null);
        icone7.setImage(null);
        icone8.setImage(null);
        icone9.setImage(null);
        icone10.setImage(null);
    }

    public void seticone5() {
        icone5.setImage(new Image("/resource/arrow.gif"));
        icone2.setImage(null);
        icone3.setImage(null);
        icone4.setImage(null);
        icone1.setImage(null);
        icone6.setImage(null);
        icone7.setImage(null);
        icone8.setImage(null);
        icone9.setImage(null);
        icone10.setImage(null);
    }

    public void seticone6() {
        icone6.setImage(new Image("/resource/arrow.gif"));
        icone2.setImage(null);
        icone3.setImage(null);
        icone4.setImage(null);
        icone5.setImage(null);
        icone1.setImage(null);
        icone7.setImage(null);
        icone8.setImage(null);
        icone9.setImage(null);
        icone10.setImage(null);
    }

    public void seticone7() {
        icone7.setImage(new Image("/resource/arrow.gif"));
        icone2.setImage(null);
        icone3.setImage(null);
        icone4.setImage(null);
        icone5.setImage(null);
        icone6.setImage(null);
        icone1.setImage(null);
        icone8.setImage(null);
        icone9.setImage(null);
        icone10.setImage(null);
    }

    public void seticone8() {
        System.out.println(">>>>>8");
        icone8.setImage(new Image("/resource/arrow.gif"));
        icone2.setImage(null);
        icone3.setImage(null);
        icone4.setImage(null);
        icone5.setImage(null);
        icone6.setImage(null);
        icone7.setImage(null);
        icone1.setImage(null);
        icone9.setImage(null);
        icone10.setImage(null);
    }

    public void seticone9() {
        icone9.setImage(new Image("/resource/arrow.gif"));
        icone2.setImage(null);
        icone3.setImage(null);
        icone4.setImage(null);
        icone5.setImage(null);
        icone6.setImage(null);
        icone7.setImage(null);
        icone8.setImage(null);
        icone1.setImage(null);
        icone10.setImage(null);
    }

    public void seticone10() {
        icone10.setImage(new Image("/resource/arrow.gif"));
        icone2.setImage(null);
        icone3.setImage(null);
        icone4.setImage(null);
        icone5.setImage(null);
        icone6.setImage(null);
        icone7.setImage(null);
        icone8.setImage(null);
        icone9.setImage(null);
        icone1.setImage(null);
    }

    public void setRightFinger() {
        icone1.setImage(null);
        icone2.setImage(null);
        icone3.setImage(null);
        icone4.setImage(null);
        icone5.setImage(null);
        icone6.setImage(null);
        icone7.setImage(new Image("/resource/arrow.gif"));
        icone8.setImage(new Image("/resource/arrow.gif"));
        icone9.setImage(new Image("/resource/arrow.gif"));
        icone10.setImage(new Image("/resource/arrow.gif"));

    }

    public void settwoThumbs() {
        icone1.setImage(null);
        icone2.setImage(null);
        icone3.setImage(null);
        icone4.setImage(null);
        icone5.setImage(new Image("/resource/arrow.gif"));
        icone6.setImage(new Image("/resource/arrow.gif"));
        icone7.setImage(null);
        icone8.setImage(null);
        icone9.setImage(null);
        icone10.setImage(null);
    }

    public void removeArro() {
        icone1.setImage(null);
        icone2.setImage(null);
        icone3.setImage(null);
        icone4.setImage(null);
        icone5.setImage(null);
        icone6.setImage(null);
        icone7.setImage(null);
        icone8.setImage(null);
        icone9.setImage(null);
        icone10.setImage(null);
    }
}
