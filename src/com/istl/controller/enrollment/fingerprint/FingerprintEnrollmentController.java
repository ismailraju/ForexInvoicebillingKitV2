 package com.istl.controller.enrollment.fingerprint;

import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istl.fingerprint.fingerprintqualityscore.ComputeQualityScore;
import com.istl.fingerprint.fingerprintqualityscore.FPData;
import com.istl.fingerprint.fingerprintqualityscore.GBNFIQ2_ImageQuality;
import com.istl.image.ImageUtil;
import com.istl.imageprocessing.Utils;
import com.istl.util.Notification;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
 
 public class FingerprintEnrollmentController {

    @FXML
    private Label fingerPrintHeading;

    @FXML
    private Label fingerstatus;
    
    @FXML
    private ImageView mainImage;

    @FXML
    private ImageView leftThumbImage;

    @FXML
    private ImageView leftIndexImage;

    @FXML
    private ImageView rightIndexImage;

    @FXML
    private ImageView rightThumbImage;

    @FXML
    private ImageView rt;

    @FXML
    private ImageView ri;

    @FXML
    private ImageView lt;

    @FXML
    private ImageView li;

    @FXML
    private ImageView rightT;

    @FXML
    private ImageView rightI;

    @FXML
    private ImageView leftT;

    @FXML
    private ImageView leftI;
    @FXML
    private Circle led;
    
    
    @FXML
    private ProgressIndicator scoreIndicator;
    @FXML
    private ProgressBar leftThumbBar;
    @FXML
    private ProgressBar leftIndexBar;
    @FXML
    private ProgressBar rightThumbBar;
    @FXML
    private ProgressBar rightIndexBar;
    
    FPData fpData = new FPData();
    
    int flag=0;
    int count = 0;

    ImageView arro = new ImageView("/com/istl/resource/arrow.gif");
    int i = 0;
    BufferedImage greyScaleImg;
    WritableImage mnImage, leftThumbimage, leftIndeximage, rightIndeximage, rightThumbimage;

    private Stage dialogStage;
    EnrollPersonBiometric biometrics = new EnrollPersonBiometric();
    ImageUtil imageUtils = new ImageUtil();
    FingerprintEnrollment fingerprintEnrollment = new FingerprintEnrollment();
    Thread th = new Thread();
    GBNFIQ2_ImageQuality gBNFIQ2_ImageQuality= new GBNFIQ2_ImageQuality();
    public void setbiometric(EnrollPersonBiometric biometrics) {
        this.biometrics = biometrics;
    }

    public void initialize() {
        scoreIndicator.setProgress(0.0000);
        leftThumbBar.setProgress(0.0000);
        leftIndexBar.setProgress(0.0000);
        rightThumbBar.setProgress(0.0000);
        rightIndexBar.setProgress(0.0000);
        
        if (fingerprintEnrollment.deviceConnection()) {
            led.setFill(javafx.scene.paint.Color.GREEN);
        }else{
            led.setFill(javafx.scene.paint.Color.RED);
        }
    
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    protected void enrollmentStartAction(ActionEvent event) throws IOException, InterruptedException {
        flag++;
        th.stop();
        scoreIndicator.setProgress(0.0000);
        leftThumbBar.setProgress(0.0000);
        leftIndexBar.setProgress(0.0000);
        rightThumbBar.setProgress(0.0000);
        rightIndexBar.setProgress(0.0000);
        mainImage.setImage(null);
        rightThumbImage.setImage(null);
        rightIndexImage.setImage(null);
        leftThumbImage.setImage(null);
        leftIndexImage.setImage(null);
        rt.setImage(null);
        ri.setImage(null);
        lt.setImage(null);
        li.setImage(null);
        rightT.setImage(new Image("/com/istl/resource/r_thumb_blue.gif"));
        rightI.setImage(new Image("/com/istl/resource/r_index_blue.gif"));
        leftT.setImage(new Image("/com/istl/resource/l_thumb_blue.gif"));
        leftI.setImage(new Image("/com/istl/resource/l_index_blue.gif"));

        if (fingerprintEnrollment.deviceConnection()) {
            led.setFill(javafx.scene.paint.Color.GREEN);

            i = 0;
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        rt.setImage(new Image("/com/istl/resource/arrow.gif"));
                        i=0;
                        while (i <= 4) {
                            scoreIndicator.setProgress(0.0);
                            double flag=0;
                            while (flag<=60) {
                                fpData = fingerprintEnrollment.getFPData();
                                fpData=gBNFIQ2_ImageQuality.computeQS(fpData);
                                flag=fpData.getScore();
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        scoreIndicator.setProgress(new Double(fpData.getScore())/100);
                                        StringProperty colorProperty = new SimpleStringProperty();
                                        if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                        
                                        scoreIndicator.styleProperty().bind(colorProperty);
                                        
                                    }
                                });
                            }
                            if(fpData.getScore()>=60){
                                BufferedImage final_image = Utils.ByteArraytoBufferedImage(fpData.getSegData(), fpData.getW(), fpData.getH());
                                Image image = SwingFXUtils.toFXImage(final_image, null);
                                mainImage.setImage(image);
                                rt.setImage(null);
                                if(i==0){
                                    rt.setImage(new Image("/com/istl/resource/arrow.gif"));
                                    i++;
                                }
                                Thread.sleep(3000);
                                if (i == 1) {
                                rt.setImage(null);
                                ri.setImage(new Image("/com/istl/resource/arrow.gif"));
                                lt.setImage(null);
                                li.setImage(null);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                      //  fingerstatus.setText("Right Index");
                                    }
                                });
                                rightThumbImage.setImage(image);
                                mainImage.setImage(null);
                                biometrics.setWsqRt(Utils.rawToBmp(fpData.getSegData(), fpData.getW(), fpData.getH()));
                                rightT.setImage(new Image("/com/istl/resource/r_thumb_green.gif"));
                                flag=0;
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        StringProperty colorProperty = new SimpleStringProperty();
                                        scoreIndicator.setProgress(0/100);
                                        rightThumbBar.setProgress(new Double(fpData.getScore())/100);
                                        if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                        rightThumbBar.styleProperty().bind(colorProperty);
                                    }
                                });
                                Thread.sleep(1000);
                                }
                                if (i == 2) {
                                    rt.setImage(null);
                                    ri.setImage(null);
                                    lt.setImage(new Image("/com/istl/resource/arrow.gif"));
                                    li.setImage(null);
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                          //  fingerstatus.setText("Left Thumb");
                                        }
                                    });
                                    rightIndexImage.setImage(image);
                                    mainImage.setImage(null);
                                    biometrics.setWsqRi(Utils.rawToBmp(fpData.getSegData(), fpData.getW(), fpData.getH()));
                                    rightI.setImage(new Image("/com/istl/resource/r_index_green.gif"));
                                    flag=0;
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            StringProperty colorProperty = new SimpleStringProperty();
                                            scoreIndicator.setProgress(0/100);
                                            rightIndexBar.setProgress(new Double(fpData.getScore())/100);
                                            if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                            rightIndexBar.styleProperty().bind(colorProperty);
                                        }
                                    });
                                    Thread.sleep(1000);
                                }
                                if (i == 3) {
                                    rt.setImage(null);
                                    ri.setImage(null);
                                    lt.setImage(null);
                                    li.setImage(new Image("/com/istl/resource/arrow.gif"));
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                          //  fingerstatus.setText("Left Index");
                                        }
                                    });
                                    leftThumbImage.setImage(image);
                                    mainImage.setImage(null);
                                    biometrics.setWsqLt(Utils.rawToBmp(fpData.getSegData(), fpData.getW(), fpData.getH()));
                                    leftT.setImage(new Image("/com/istl/resource/l_thumb_green.gif"));
                                    flag=0;
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            StringProperty colorProperty = new SimpleStringProperty();
                                            scoreIndicator.setProgress(new Double(0/100));
                                            leftThumbBar.setProgress(new Double(fpData.getScore())/100);
                                            if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                            leftThumbBar.styleProperty().bind(colorProperty);
                                        }
                                    });
                                    Thread.sleep(1000);
                                }
                                if (i == 4) {
                                    leftIndexImage.setImage(image);
                                    mainImage.setImage(null);
                                    biometrics.setWsqLi(Utils.rawToBmp(fpData.getSegData(), fpData.getW(), fpData.getH()));
                                    leftI.setImage(new Image("/com/istl/resource/l_index_green.gif"));
                                    rt.setImage(null);
                                    ri.setImage(null);
                                    lt.setImage(null);
                                    li.setImage(null);
                                    
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            StringProperty colorProperty = new SimpleStringProperty();
                                            scoreIndicator.setProgress(0/100);
                                            leftIndexBar.setProgress(new Double(fpData.getScore())/100);
                                           if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                            leftIndexBar.styleProperty().bind(colorProperty);
                                        }
                                    });
                                    Thread.sleep(1000);
                                }
                                i++;
                            }else{
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Notification.errorMessage("Bad Image");
                                    }
                                });
                                i=i;
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            th = new Thread(sleeper);
            th.start();
        } else {
            led.setFill(javafx.scene.paint.Color.RED);
            Notification.errorMessage("Device is not Connected");
        }
    }
    
    @FXML
    protected void enrollmentDoneAction(ActionEvent event) throws IOException {
        dialogStage.close();
    }
    
    @FXML
    protected void rightThumbAction(MouseEvent event) throws IOException {
        if(flag!=0){
            th.stop();
        rt.setImage(null);
        ri.setImage(null);
        lt.setImage(null);
        li.setImage(null);
        rightT.setImage(new Image("/com/istl/resource/r_thumb_blue.gif"));
        if (fingerprintEnrollment.deviceConnection()){
            fingerPrintHeading.setText("Device is Ready");
            fingerstatus.setText("Right Thumb");
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        int i=0;
                        while (i==0) {
                            rt.setImage(new Image("/com/istl/resource/arrow.gif"));
                            rightThumbBar.setProgress(0.0);
                            int flag=0;
                            while (flag<=60) {
                                fpData = fingerprintEnrollment.getFPData();
                                fpData=gBNFIQ2_ImageQuality.computeQS(fpData);
                                flag=fpData.getScore();
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        scoreIndicator.setProgress(new Double(fpData.getScore())/100);
                                        StringProperty colorProperty = new SimpleStringProperty();
                                        if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                        
                                        scoreIndicator.styleProperty().bind(colorProperty);
                                        
                                    }
                                });
                            }
                            BufferedImage final_image = Utils.ByteArraytoBufferedImage(fpData.getSegData(), fpData.getW(), fpData.getH());
                            Image image = SwingFXUtils.toFXImage(final_image, null);
                            mainImage.setImage(image);
                            rt.setImage(null);
                            Thread.sleep(2000);
                            rt.setImage(null);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                // fingerstatus.setText("Right Index");
                                }
                            });
                            rightThumbImage.setImage(image);
                            mainImage.setImage(null);
                            biometrics.setWsqRt(Utils.rawToBmp(fpData.getSegData(), fpData.getW(), fpData.getH()));
                            rightT.setImage(new Image("/com/istl/resource/r_thumb_green.gif"));
                            Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        StringProperty colorProperty = new SimpleStringProperty();
                                        scoreIndicator.setProgress(0/100);
                                        rightThumbBar.setProgress(new Double(fpData.getScore())/100);
                                        if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                        rightThumbBar.styleProperty().bind(colorProperty);
                                    }
                                });
                            i++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                };
            };
            th = new Thread(sleeper);
            th.start();
        }else{
            Notification.errorMessage("Device is not Connected");
        }
        }else{
            Notification.errorMessage("Press Start First");
        }
        
    
    }
    
    @FXML
    protected void rightIndexAction(MouseEvent event) throws IOException {
        if(flag!=0){
            System.out.println(">>>1>>");
        th.stop();
        rt.setImage(null);
        ri.setImage(null);
        lt.setImage(null);
        li.setImage(null);
        rightI.setImage(new Image("/com/istl/resource/r_index_blue.gif"));
        System.out.println(">>>2>>");
        System.out.println(">>>>>>>>"+fingerprintEnrollment.deviceConnection());
        if (fingerprintEnrollment.deviceConnection()){
            fingerPrintHeading.setText("Device is Ready");
            System.out.println(">>>3>>");
            fingerstatus.setText("Right Thumb");
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        int i=0;
                        while (i==0) {
                            ri.setImage(new Image("/com/istl/resource/arrow.gif"));
                            rightIndexBar.setProgress(0.0);
                            System.out.println(">>>>>>I>>>" + i);
                            int flag=0;
                            while (flag<=60) {
                                fpData = fingerprintEnrollment.getFPData();
                                ComputeQualityScore cs = new ComputeQualityScore();
                                // fpData = cs.computeQS(fpData);
                                fpData=gBNFIQ2_ImageQuality.computeQS(fpData);
                                flag=fpData.getScore();
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        StringProperty colorProperty = new SimpleStringProperty();
                                        scoreIndicator.setProgress(new Double(fpData.getScore())/100);
                                        
                                       if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                        
                                        scoreIndicator.styleProperty().bind(colorProperty);
                                        
                                    }
                                });
                            }
                            BufferedImage final_image = Utils.ByteArraytoBufferedImage(fpData.getSegData(), fpData.getW(), fpData.getH());
                            Image image = SwingFXUtils.toFXImage(final_image, null);
                            mainImage.setImage(image);
                            ri.setImage(null);
                            Thread.sleep(2000);
                            rt.setImage(null);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                // fingerstatus.setText("Right Index");
                                }
                            });
                            rightIndexImage.setImage(image);
                            mainImage.setImage(null);
                            biometrics.setWsqRi(Utils.rawToBmp(fpData.getSegData(), fpData.getW(), fpData.getH()));
                            rightI.setImage(new Image("/com/istl/resource/r_index_green.gif"));
                            Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        StringProperty colorProperty = new SimpleStringProperty();
                                        scoreIndicator.setProgress(0/100);
                                        rightIndexBar.setProgress(new Double(fpData.getScore())/100);
                                       if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                        rightIndexBar.styleProperty().bind(colorProperty);
                                    }
                                });
                            i++;
                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                 return null;   
                };
               
            };
            th = new Thread(sleeper);
            th.start();
        
        }else{
            Notification.errorMessage("Device is not Connected");
        }
        }else{
            Notification.errorMessage("Press Start First");
        }
        
    }

    @FXML
    protected void leftThumbAction(MouseEvent event) throws IOException {
        if(flag!=0){
            System.out.println(">>>1>>");
        th.stop();
        rt.setImage(null);
        ri.setImage(null);
        lt.setImage(null);
        li.setImage(null);
        leftT.setImage(new Image("/com/istl/resource/l_thumb_blue.gif"));
        System.out.println(">>>2>>");
        System.out.println(">>>>>>>>"+fingerprintEnrollment.deviceConnection());
        if (fingerprintEnrollment.deviceConnection()){
            fingerPrintHeading.setText("Device is Ready");
            System.out.println(">>>3>>");
            fingerstatus.setText("Left Thumb");
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        int i=0;
                        while (i==0) {
                            lt.setImage(new Image("/com/istl/resource/arrow.gif"));
                            leftThumbBar.setProgress(0.0);
                            System.out.println(">>>>>>I>>>" + i);
                            int flag=0;
                            while (flag<=60) {
                                fpData = fingerprintEnrollment.getFPData();
                                ComputeQualityScore cs = new ComputeQualityScore();
                                // fpData = cs.computeQS(fpData);
                                fpData=gBNFIQ2_ImageQuality.computeQS(fpData);
                                flag=fpData.getScore();
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        scoreIndicator.setProgress(new Double(fpData.getScore())/100);
                                        StringProperty colorProperty = new SimpleStringProperty();
                                        if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                        
                                        scoreIndicator.styleProperty().bind(colorProperty);
                                        
                                    }
                                });
                            }
                            BufferedImage final_image = Utils.ByteArraytoBufferedImage(fpData.getSegData(), fpData.getW(), fpData.getH());
                            Image image = SwingFXUtils.toFXImage(final_image, null);
                            mainImage.setImage(image);
                            lt.setImage(null);
                            Thread.sleep(2000);
                            lt.setImage(null);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                 //fingerstatus.setText("Right Index");
                                }
                            });
                            leftThumbImage.setImage(image);
                            mainImage.setImage(null);
                            biometrics.setWsqLt(Utils.rawToBmp(fpData.getSegData(), fpData.getW(), fpData.getH()));
                            leftT.setImage(new Image("/com/istl/resource/l_thumb_green.gif"));
                            Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        StringProperty colorProperty = new SimpleStringProperty();
                                        scoreIndicator.setProgress(0/100);
                                        leftThumbBar.setProgress(new Double(fpData.getScore())/100);
                                       if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                        leftThumbBar.styleProperty().bind(colorProperty);
                                    }
                                });
                            i++;
                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                 return null;   
                };
               
            };
            th = new Thread(sleeper);
            th.start();
        
        }else{
            Notification.errorMessage("Device is not Connected");
        }
        }else{
            Notification.errorMessage("Press Start First");
        }
        
    }

    @FXML
    protected void leftIndexAction(MouseEvent event) throws IOException, Exception {
        if(flag!=0){
            System.out.println(">>>1>>");
        th.stop();
        rt.setImage(null);
        ri.setImage(null);
        lt.setImage(null);
        li.setImage(null);
        leftI.setImage(new Image("/com/istl/resource/l_index_blue.gif"));
        System.out.println(">>>2>>");
        System.out.println(">>>>>>>>"+fingerprintEnrollment.deviceConnection());
        if (fingerprintEnrollment.deviceConnection()){
            fingerPrintHeading.setText("Device is Ready");
            System.out.println(">>>3>>");
            fingerstatus.setText("Right Thumb");
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        int i=0;
                        while (i==0) {
                            li.setImage(new Image("/com/istl/resource/arrow.gif"));
                            leftIndexBar.setProgress(0.0);
                            int flag=0;
                            while (flag<=60) {
                                fpData = fingerprintEnrollment.getFPData();
                                ComputeQualityScore cs = new ComputeQualityScore();
                                // fpData = cs.computeQS(fpData);
                                fpData=gBNFIQ2_ImageQuality.computeQS(fpData);
                                flag=fpData.getScore();
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        scoreIndicator.setProgress(new Double(fpData.getScore())/100);
                                        StringProperty colorProperty = new SimpleStringProperty();
                                        if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                        
                                        scoreIndicator.styleProperty().bind(colorProperty);
                                        
                                    }
                                });
                            }
                            BufferedImage final_image = Utils.ByteArraytoBufferedImage(fpData.getSegData(), fpData.getW(), fpData.getH());
                            Image image = SwingFXUtils.toFXImage(final_image, null);
                            mainImage.setImage(image);
                            li.setImage(null);
                            Thread.sleep(2000);
                            li.setImage(null);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                 //fingerstatus.setText("Right Index");
                                }
                            });
                            leftIndexImage.setImage(image);
                            mainImage.setImage(null);
                            biometrics.setWsqLi(Utils.rawToBmp(fpData.getSegData(), fpData.getW(), fpData.getH()));
                            leftI.setImage(new Image("/com/istl/resource/l_index_green.gif"));
                            Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        StringProperty colorProperty = new SimpleStringProperty();
                                        scoreIndicator.setProgress(0/100);
                                        leftIndexBar.setProgress(new Double(fpData.getScore())/100);
                                       if(fpData.getScore()>=60 &fpData.getScore()<70){
                                            colorProperty.setValue("-fx-accent: #ff9900");
                                        }else if(fpData.getScore()>=70 &fpData.getScore()<80){
                                            colorProperty.setValue("-fx-accent: #00cc44");
                                        }else if(fpData.getScore()>=80 &fpData.getScore()<90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else if(fpData.getScore()>=90){
                                            colorProperty.setValue("-fx-accent: #009933");
                                        }else{
                                            colorProperty.setValue("-fx-accent: #ff0000");
                                        }
                                        leftIndexBar.styleProperty().bind(colorProperty);
                                    }
                                });
                            i++;
                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                 return null;   
                };
               
            };
            th = new Thread(sleeper);
            th.start();
        
        }else{
            Notification.errorMessage("Device is not Connected");
        }
        
        }else{
            Notification.errorMessage("Press Start First");
        
        }
    }
}
