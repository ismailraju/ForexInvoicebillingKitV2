package com.istl.util;

import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.canon.controller.CanonViewController;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
public class CanonUtil {
    private Stage primaryStage;
    
     public CanonViewController showPersonEditDialog(EnrollPersonBiometric biometric) {
         try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CanonUtil.class.getResource("/com/canon/view/CanonView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Image Capture");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setResizable(false);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CanonViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setbiometric(biometric);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            controller.setClosed();
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
           
        }
        return null;
    }
    
    
}
