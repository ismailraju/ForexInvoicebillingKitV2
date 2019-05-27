/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.main;

import javafx.scene.control.Alert;

/**
 *
 * @author Arun
 *  
 */
public class Notification {
   public static void  successfullyMessage(String message){
       Alert alert = new Alert(Alert.AlertType.INFORMATION);alert.setTitle("successfully message");
       //alert.setHeaderText("Done");
       alert.setHeaderText(message);
       alert.showAndWait();
   }
   
   public static void  errorMessage(String message){
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Error message");
       //alert.setHeaderText("Error");
       alert.setHeaderText(message);
       alert.showAndWait();
   }
    
}
