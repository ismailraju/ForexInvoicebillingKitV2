package com.istl.controller;


import com.istl.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class HomeController implements Initializable{
    
    @FXML 
    private  AnchorPane home;
    Utils util = new Utils();
    @Override
    public void initialize(URL url, ResourceBundle rb){
       // System.out.println(User.getUser());
        
    }
    
    @FXML
    protected void registrationAction(ActionEvent event) throws IOException{
        
//        AnchorPane pane=(AnchorPane) FXMLLoader.load(getClass().getResource("/com/istl/view/Registration.fxml"));
//        home.getChildren().setAll(pane);
          util.anchorType("/com/istl/view/Registration.fxml");
    }
    
    @FXML
    protected void searchAction(ActionEvent event) throws IOException, ParseException{
        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/com/istl/view/Search.fxml"));
//        AnchorPane pane = (AnchorPane) loader.load();
//        BorderPane border = LoginController.getRoot();
//        border.setCenter(pane);
         loader = util.loaderType("/com/istl/view/Search.fxml");
        SearchController controller = loader.getController();
        controller.setTableValue();
    }
    
    @FXML
    protected void RegisteredAction(ActionEvent event) throws IOException, ParseException{
        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/com/istl/view/Registered.fxml"));
//        AnchorPane pane = (AnchorPane) loader.load();
//        BorderPane border = LoginController.getRoot();
//        border.setCenter(pane);
        loader = util.loaderType("/com/istl/view/Registered.fxml");
        RegisteredController controller = loader.getController();
        controller.setTableValue();
    }
    
    @FXML
    protected void exportAction(ActionEvent event) throws IOException, ParseException{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/com/istl/view/ExportData.fxml"));
//        AnchorPane pane = (AnchorPane) loader.load();
//        BorderPane border = LoginController.getRoot();
//        border.setCenter(pane);
////        ExportDataController controller = loader.getController();
////        controller.setTableValue();
//        
         util.loaderType("/com/istl/view/ExportData.fxml");
    }
    
}
