/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.util;

import javafx.stage.Stage;
import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istl.iris.controller.MainController;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

/**
 *
 * @author Arun
 */
public class IrisUtil {
    
     private Stage primaryStage;
    
     public MainController showPersonEditDialog(EnrollPersonBiometric biometric) {
         try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CanonUtil.class.getResource("/com/istl/iris/view/Main.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Iris Capture");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setResizable(false);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            MainController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setbiometric(biometric);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
           // controller.setClosed();
            
//            ByteArrayInputStream is=new ByteArrayInputStream(biometric.getIrisLeft());
//            BufferedImage image=ImageIO.read(is);
//            ImageIO.write(image, "bmp",new File("leftIr.bmp"));
           return controller;
        } catch (IOException e) {
            e.printStackTrace();
           
        }
        return null;
    }
    
    
}
