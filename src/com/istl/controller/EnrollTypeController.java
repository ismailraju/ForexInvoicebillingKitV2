/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.controller;

import com.istl.enroll_kit.model.EnrollUtils;
import com.istl.service.EnrollUtilService;
import com.istl.util.Notification;

import java.net.URL;

import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author User
 */
public class EnrollTypeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<String> list = FXCollections.observableArrayList();
    EnrollUtilService service = new EnrollUtilService();

    @FXML
    private TextField typeName;
    @FXML
    private ComboBox<String> type;
    @FXML
    private ColorPicker colorpicker;
    @FXML
    private Button button;
    @FXML
    private TextField remarks;
    @FXML
    private TextField priority;

    public EnrollUtils util = new EnrollUtils();
    public static SeeEnrollTypeController controller;

    public static SeeEnrollTypeController getController() {
        return controller;
    }

    public static void setController(SeeEnrollTypeController controller) {
        EnrollTypeController.controller = controller;
    }

    /**
     *
     * @param event
     */
    @FXML
    protected void saveAction(ActionEvent event) {

        if (isValid()) {

            if (type.getValue().toString().equals("Categories") && !(remarks.getText().length() ==0) && !(remarks.getText() == null)) {

                util.setType(type.getValue().toString());

                util.setPriority(Integer.parseInt(priority.getText()));

                util.setName(typeName.getText());
                if (type.getValue().toString().equals("Categories")) {
                    util.setColorCode(colorpicker.getValue().toString());
                    util.setRemarks(remarks.getText());
                }
                boolean b = false;
                int a = 0;
                if (util.getId() != null) {
                    b = service.save(util, 2);
                    if (b == true) {
                        a = 1;
                    }
                } else {
                    b = service.save(util, 1);
                    if (b == true) {
                        a = 2;
                    }
                }

                if (a == 1) {
                    Notification.successfullyMessage("Successfully Updated");
                    SeeEnrollTypeController cnt = getController();
                    cnt.setTableValue();
                    Stage s = (Stage) button.getScene().getWindow();
                    s.close();
                } else if (a == 2) {
                    Notification.successfullyMessage("Successfully Added");
                    SeeEnrollTypeController cnt = getController();
                    cnt.setTableValue();
                    Stage s = (Stage) button.getScene().getWindow();
                    s.close();
                } else {
                    Notification.errorMessage("Something Wrong");
                }
            } else {
                Notification.errorMessage("Remarks should not be empty");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.add("Categories");
        list.add("Organization");
        type.setItems(list);
        type.setValue(list.get(0));
        type.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Categories")) {
                    System.out.println(newValue);
                    colorpicker.setDisable(false);
                    remarks.setDisable(false);
                } else {
                    colorpicker.setDisable(true);
                    remarks.setDisable(true);
                }
            }
        });

    }

    public void initData(EnrollUtils utils) {
        this.util = utils;
        type.setValue(utils.getType());
        type.setDisable(true);
        
        typeName.setText(utils.getName());
       
        if (utils.getColorCode() != null) {
            Color c = Color.web(utils.getColorCode());
            colorpicker.setValue(c);
        }
        
        if (utils.getPriority() != null) {
            priority.setText(utils.getPriority().toString());
        }

        if (utils.getRemarks().length() != 0) {
            remarks.setText(utils.getRemarks());
        }
        button.setText("Update");
    }

    public boolean isValid() {
        String msg = "";

        
        if (typeName.getText().length() == 0 || priority.getText().length() == 0) {
            msg += "Type Name and priority can't be empty\n";
            
        }
        if (priority.getText().length() != 0 && !priority.getText().matches("[0-9]+")) {
            msg += "Priority should be number only";
        }
        if (msg.length() == 0) {

            return true;
        } else {
            Notification.errorMessage(msg);
            return false;
        }

    }
}
