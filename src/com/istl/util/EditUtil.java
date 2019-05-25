/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.util;

import com.istl.controller.EditController;
import com.istl.enroll_kit.model.EnrollPerson;
import com.istl.enroll_kit.model.EnrollPersonBiometric;
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
public class EditUtil {
    private Stage primaryStage;
    
     public EditController showPersonEditDialog(EnrollPerson person, EnrollPersonBiometric biometric) {
         try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EditController.class.getResource("/com/istl/view/Edit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.initData(person, biometric);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller;
        } catch (IOException e) {
            e.printStackTrace();
           
        }
        return null;
    }
    
}
