package com.istl.controller;

import com.istl.util.Utils;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class MenuController implements Initializable {

    @FXML
    MenuBar menu;
    @FXML
    private MenuItem logout;
    private BorderPane rootLayout;
    private Utils utils = new Utils();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    protected void homeAction(ActionEvent event) {
//        try {
//            Utils.timerFlag = true;
//            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/istl/view/Home.fxml"));
//            BorderPane border = LoginController.getRoot();
//            border.setCenter(pane);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        utils.anchorType("/com/istl/view/Home.fxml");
    }

    @FXML
    protected void registrationAction(ActionEvent event) {
//        try {
//            Utils.timerFlag = true;
//            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/istl/view/Registration.fxml"));
//            BorderPane border = LoginController.getRoot();
//            border.setCenter(pane);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        utils.anchorType("/com/istl/view/Registration.fxml");
    }

    @FXML
    protected void searchAction(ActionEvent event) throws ParseException {
        try {
            FXMLLoader loader = new FXMLLoader();
//            Utils.timerFlag = true;
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/com/istl/view/Search.fxml"));
//            AnchorPane pane = (AnchorPane) loader.load();
//            BorderPane border = LoginController.getRoot();
//            border.setCenter(pane);
            loader = utils.loaderType("/com/istl/view/Search.fxml");
            SearchController controller = loader.getController();
            controller.setTableValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void userAction(ActionEvent event) throws ParseException {
        try {
//            Utils.timerFlag = true;
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/istl/view/NewUser.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.setTitle("New User Form");
//            stage.setResizable(false);
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.show();
            utils.stageType("/com/istl/view/NewUser.fxml", "New User Form");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    protected void newPasswordAction(ActionEvent event) {
        try {
//            Utils.timerFlag = true;
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/istl/view/PasswordChange.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.setTitle("Passwor Change");
//            stage.getIcons().add(new Image("/logo/logo.png"));
//            stage.setResizable(false);
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.showAndWait();
            utils.stageType("/com/istl/view/PasswordChange.fxml", "Passwor Change");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    protected void logoutAction(ActionEvent event) {
        try {
//            Utils.timerFlag = true;
//            Parent root = FXMLLoader.load(getClass().getResource("/com/istl/view/Login.fxml"));
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setResizable(false);
//            stage.setTitle("Login Form");
//            stage.getIcons().add(new Image("/logo/logo.png"));
//            stage.setScene(scene);
//            stage.show();
            utils.stageType("/com/istl/view/Login.fxml", "Login Form");
            Stage home = (Stage) menu.getScene().getWindow();
            home.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    protected void seeAllEnrollType(ActionEvent event) throws ParseException {
//        try {
//            Utils.timerFlag = true;
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/com/istl/view/SeeEnrollType.fxml"));
//            AnchorPane pane = (AnchorPane) loader.load();
//            BorderPane border = LoginController.getRoot();
//            border.setCenter(pane);
////            SeeEnrollTypeController controller = loader.getController();
////            controller.setTableValue();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        utils.loaderType("/com/istl/view/SeeEnrollType.fxml");
    }

}
