package com.canon.controller;

import com.istl.enroll_kit.model.EnrollPersonBiometric;
import edsdk.api.CanonCamera;
import edsdk.api.commands.GetPropertyCommand.Size;
import it.polito.elite.teaching.cv.utils.Utils;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public class CanonViewController implements Initializable {

    @FXML
    private ImageView liveImage;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private Button save;
    @FXML
    private RadioButton selectOne;
    @FXML
    private RadioButton selectTwo;
    @FXML
    private RadioButton selectTHree;
    @FXML
    private Label message;
    
     // face cascade classifier C:/opencv/build/etc/lbpcascades/lbpcascade_frontalface.xml
    String file = "C:/opencv/build/etc/haarcascades/haarcascade_frontalface_alt.xml";
    private CascadeClassifier faceCascade = new CascadeClassifier(file);
    private int absoluteFaceSize;
    static CanonCamera camera;
    private ScheduledExecutorService timer;
    private BufferedImage bufferedImageOne;
    private BufferedImage bufferedImageTwo;
    private BufferedImage bufferedImageThree;
    private Stage primaryStage;
    private boolean flag=false;
    private EnrollPersonBiometric biometric;
    public void setDialogStage(Stage primaryStage){
        this.primaryStage=primaryStage;
    }
    
    static{
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setbiometric(EnrollPersonBiometric biometric){
        this.biometric=biometric;
    }
    
    
    
    
    public  void openCamera() {
        camera = new CanonCamera();
        camera.run();
        if (!camera.openSession()) {
            System.out.println("Camera is not Connected");
            message.setText("Canon is not Connected");
        }else{
            message.setText("Canon is Ready");
        }
    }

    public  void liveView(){
        if (camera.beginLiveView()){
            Runnable frameGrabber;
            frameGrabber = new Runnable() {
                @Override
                public void run() {
                    try {
                        //byte[] bimage = camera.downloadLiveViewBuffer();
                        BufferedImage bimage=camera.downloadLiveView();
                        if(bimage!=null){
                        Mat m= grabFrame(bimage);
                        Image imageToShow =Utils.mat2Image(m); //SwingFXUtils.toFXImage(bimage, null);//
                        updateImageView(liveImage, imageToShow);}
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            };
            this.timer = Executors.newSingleThreadScheduledExecutor();
            this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
            } else {
            System.err.println("Failed to open camra connection.....");
        }
    }
    
    public static void closeCamera() {
        
        camera.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openCamera();
        liveView();
    }

    @FXML
    protected void  captureAction(ActionEvent event) throws IOException, Exception{
        BufferedImage image=cropingIMage(bufferedImageToMat(camera.downloadLiveView()));
        if(image!=null){
             if(bufferedImageOne==null && bufferedImageThree==null && bufferedImageTwo==null){
            bufferedImageOne=cropingIMage(bufferedImageToMat(camera.downloadLiveView()));//camera.downloadLiveView();
            //cropingIMage(bufferedImageToMat(camera.downloadLiveView()));
            image1.setImage(SwingFXUtils.toFXImage(bufferedImageOne, null));
        }else if(bufferedImageOne!=null && bufferedImageThree==null && bufferedImageTwo==null){
            bufferedImageTwo=cropingIMage(bufferedImageToMat(camera.downloadLiveView()));;
            image2.setImage(SwingFXUtils.toFXImage(bufferedImageTwo, null));
        }else if(bufferedImageOne!=null && bufferedImageThree!=null && bufferedImageTwo==null){
            bufferedImageTwo=cropingIMage(bufferedImageToMat(camera.downloadLiveView()));;
            image2.setImage(SwingFXUtils.toFXImage(bufferedImageTwo, null));
        }else if(bufferedImageOne!=null && bufferedImageThree==null && bufferedImageTwo!=null){
            bufferedImageThree=cropingIMage(bufferedImageToMat(camera.downloadLiveView()));;
            image3.setImage(SwingFXUtils.toFXImage(bufferedImageThree, null));
        }else{
             bufferedImageOne=cropingIMage(bufferedImageToMat(camera.downloadLiveView()));
            image1.setImage(SwingFXUtils.toFXImage(bufferedImageOne, null));
        }
        }else{
            System.out.println("face not found");
        }
        
       
    }
    
    @FXML
    protected void saveAction(ActionEvent event) {
         boolean flag=false;
        try {
            if(selectOne.isSelected()){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImageOne, "jpg", baos );
                baos.flush();
                byte[] image= baos.toByteArray();
                baos.close();
                biometric.setPhoto(image);
                flag=true;
                System.out.println(">>>W: "+bufferedImageOne.getWidth());
                System.out.println(">>>H: "+bufferedImageOne.getHeight());
                ImageIO.write(bufferedImageOne, "JPG",new File("output.JPG"));
            }
            
            if(selectTwo.isSelected()){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImageTwo, "jpg", baos );
                baos.flush();
                byte[] image= baos.toByteArray();
                baos.close();
                biometric.setPhoto(image);
                flag=true;
            }
            
            if(selectTHree.isSelected()){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write( bufferedImageThree, "jpg", baos );
                baos.flush();
                byte[] image= baos.toByteArray();
                baos.close();
                biometric.setPhoto(image);
                flag=true;
            }
            
            if(flag){
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
            
            if (this.camera.isLiveViewActive()) {
                this.camera.endLiveView();
                this.camera.closeSession();
                this.closeCamera();
                Stage stage = (Stage) save.getScene().getWindow();
                stage.close();
            }
            
            }
            
            

           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
   @FXML
   protected void deleteOneAction(ActionEvent event){
       image1.setImage(null);
       bufferedImageOne=null;
   }
   
   @FXML
   protected void deleteTwoAction(ActionEvent event){
       image2.setImage(null);
       bufferedImageTwo=null;
   }
   
   @FXML
   protected void deleteThreeAction(ActionEvent event){
       image3.setImage(null);
       bufferedImageThree=null;
   }
   
   
   @FXML
   protected void closeAction(ActionEvent event){
       
       try {
         Stage stage = (Stage) save.getScene().getWindow();
                stage.close();
          
       } catch (Exception e) {
           e.printStackTrace();
       }
   
   }
   
   
   public void setClosed(){
       try {
           if (this.timer != null && !this.timer.isShutdown()) {
               try {// stop the timer
                   this.timer.shutdown();
                   this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    // log any exception
                    System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
                }
            }
           
           if (this.camera.isLiveViewActive()) {
                this.camera.endLiveView();
                this.camera.closeSession();
                this.closeCamera();

           }else{
               this.closeCamera();
                Stage stage = (Stage) save.getScene().getWindow();
                stage.close();
           
           }
           
          
       } catch (Exception e) {
           e.printStackTrace();
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
//1056, 704, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED
    private Mat grabFrame(byte[] image) {
        Mat frame = new Mat();
        frame = Imgcodecs.imdecode(new MatOfByte(image), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
       // frame.put(0, 0, image);
        if (!frame.empty()) {
            this.detectAndDisplay(frame);// face detection
        }
        return frame;
    }
    
    public static Mat bufferedImageToMat(BufferedImage bi) {
        if(bi!=null){
            Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
            byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
            mat.put(0, 0, data);
            return mat;
        }else{
            return null;
        }
}
    
     private Mat grabFrame(BufferedImage image) {
        Mat frame = new Mat(image.getWidth(),image.getHeight(),CvType.CV_8UC3);
        frame=bufferedImageToMat(image);
        //frame = Imgcodecs.imdecode(new MatOfByte(image), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
       // frame.put(0, 0, image);
        if (!frame.empty()) {
            this.detectAndDisplay(frame);// face detection
        }
        return frame;
    }

    private void detectAndDisplay(Mat frame) {
        MatOfRect faces = new MatOfRect();
        Mat grayFrame = new Mat();

        // convert the frame in gray scale
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
        // equalize the frame histogram to improve the result
        Imgproc.equalizeHist(grayFrame, grayFrame);

       

        
         // compute minimum face size (20% of the frame height, in our case)
        if (this.absoluteFaceSize == 0) {
            int height = grayFrame.rows();
            if (Math.round(height * 0.2f) > 0) {
                this.absoluteFaceSize = Math.round(height * 0.2f);
            }
        }
        
        
        

        // detect faces
        this.faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
                new org.opencv.core.Size(this.absoluteFaceSize, this.absoluteFaceSize), new org.opencv.core.Size());
      //  System.out.println(">>>>>>>>"+faces.dataAddr());
        // each rectangle in faces is a face: draw them!
       
        Rect[] facesArray = faces.toArray();
        
        for (int i = 0; i < facesArray.length; i++) {
            Imgproc.rectangle(frame, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0), 3);
        }

    }
    
    
    public BufferedImage cropingIMage(Mat image) throws IOException {
        BufferedImage out=null;
        
        if(image!=null){
            MatOfRect faces = new MatOfRect();
            Mat grayFrame = new Mat();
            // convert the frame in gray scale
            Imgproc.cvtColor(image, grayFrame, Imgproc.COLOR_BGR2GRAY);
            // equalize the frame histogram to improve the result
            Imgproc.equalizeHist(grayFrame, grayFrame);
            
                //    filtering 
         Imgproc.GaussianBlur(image, grayFrame, new org.opencv.core.Size(0, 0), 10); 
         Core.addWeighted(image, 1.5, grayFrame, -0.5, 0, grayFrame); 

            // compute minimum face size (20% of the frame height, in our case)
            if (this.absoluteFaceSize == 0) {
                int height = grayFrame.rows();
                if (Math.round(height * 0.2f) > 0) {
                    this.absoluteFaceSize = Math.round(height * 0.2f);
                }
            }

            // detect faces
            this.faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
                    new org.opencv.core.Size(this.absoluteFaceSize, this.absoluteFaceSize), new org.opencv.core.Size());
            // each rectangle in faces is a face: draw them!
            Rect rect_Crop=null;
            Rect[] facesArray = faces.toArray();
            for (int i = 0; i < facesArray.length; i++) {
                rect_Crop = new Rect(facesArray[i].x-50, facesArray[i].y-100, facesArray[i].width+100, facesArray[i].height+175);
            }
            if(faces.dataAddr()!=0){
                Mat image_roi = new Mat(image,rect_Crop);
                out=getBufferedImageFromMat(image_roi);
            }else{
                System.out.println("face not found");
            }
            System.out.println(">>>return");
            return  out;
        }
        return out;
    }
    
    
    public static BufferedImage getBufferedImageFromMat(Mat mat) throws IOException{
        MatOfByte mbyt= new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, mbyt);
        byte ba[]=mbyt.toArray();
        BufferedImage bi=ImageIO.read(new ByteArrayInputStream(ba));
        return bi;
    }
}