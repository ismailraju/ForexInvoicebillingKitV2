/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.util;

import com.istl.controller.enrollment.fingerprint.FingerprintEnrollmentController;
import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istl.fingerprint.controller.FingerprintScanController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Arun
 */
public class FingerPrintEnrollmentUtils {
    private Stage primaryStage;
    
     public FingerprintEnrollmentController showPersonEditDialog(EnrollPersonBiometric biometric) {
         try {
            // Load the fxml file and create a new stage for the popup dialog.///com/istl/fingerprint/view/FingerprintScan.fxml
            FXMLLoader loader = new FXMLLoader();//com/istl/view/enrollment/fingerprint
            loader.setLocation(FingerPrintEnrollmentUtils.class.getResource("/com/istl/view/enrollment/fingerprint/FingerprintEnrollment.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setResizable(false);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            FingerprintEnrollmentController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setbiometric(biometric);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller;
        } catch (IOException e) {
            e.printStackTrace();
           
        }
        return null;
    }
    
}
