/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.util;


import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author User
 */
 public abstract class LoadingClass {

    
    public Stage primaryStage;


    public void loadingFront(String Fxmlpath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(Fxmlpath));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage stage1 = new Stage();
            this.primaryStage = stage1;
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.initStyle(StageStyle.TRANSPARENT);
            stage1.setScene(scene);
            stage1.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public abstract void backGroundTask();

}
//in implementation


// @Override
//    public void backGroundTask() {
//
//    final Timer timer = new Timer();
//        int delay = 0;
//        int interval = 1000;
//        try {
//            timer.scheduleAtFixedRate(new TimerTask() {
//                public void run() {
//                    
//                   ///write code here for background task
//
//                       //  running background task
//
//                   /// end code here
//
//                    timer.cancel();
//                    timer.purge();
//
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            primaryStage.hide();
//
//                            //write code here after copleting background task
//                            
//                            alert.show();
//                            try {
//                                FXMLLoader loader = new FXMLLoader();
//                                loader.setLocation(getClass().getResource("/com/istl/view/ExportDataController.fxml"));
//                                AnchorPane pane = (AnchorPane) loader.load();
//                                BorderPane border = LoginController.getRoot();
//                                border.setCenter(pane);
//                            } catch (Exception Exception) {
//                            }
//                             //end code here
//                        }
//                    });
//
//                    //
//                }
//
//            }, delay, interval
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }