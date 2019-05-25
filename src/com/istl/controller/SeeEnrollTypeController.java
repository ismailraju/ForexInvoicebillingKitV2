/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.controller;

import com.istl.accesscontrol.model.EnrollUtilsRespons;
import com.istl.enroll_kit.model.EnrollUtils;
import com.istl.service.EnrollUtilService;
import com.istl.util.Notification;
import com.istl.util.PersonTableUtil;
import com.istl.util.Utils;
import com.istl.util.Zone;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * FXML Controller class
 *
 * @author User
 */
public class SeeEnrollTypeController implements Initializable {

    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TableView table;
    @FXML
    private ComboBox<Zone> ip;
    @FXML
    private Circle serverStatus;

    EnrollUtilsRespons response = new EnrollUtilsRespons();
    final Timer timer = new Timer();
    String type = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ip.setItems(Utils.getIp());
        if (Utils.zone.getId() == null) {

            ip.getSelectionModel().select(0);
            Utils.zone = Utils.getIp().get(0);

        } else {
            ip.getSelectionModel().select(Utils.zone.getId() - 1);

        }
        setColorCircle();

        typeComboBox.setItems(getList());
        if (type.length() == 0) {
            type = getList().get(0);
            typeComboBox.setValue(getList().get(0));
        }
        ip.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Zone>() {
            @Override
            public void changed(ObservableValue<? extends Zone> observable, Zone oldValue, Zone newValue) {
                if (newValue != null) {
                    //System.out.println("set" + newValue);
                    Utils.zone = newValue;
                    try {
                        setColorCircle();

                    } catch (Exception ex) {
                        Logger.getLogger(ExportDataController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

        try {
            Utils.timerFlag = false;
            backgroundRunningThread();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTableValue();
    }

    private void backgroundRunningThread() {

        int delay = 0;
        int interval = 1000;
        try {
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    setColorCircle();
                    if (Utils.timerFlag) {
                        timer.cancel();
                        timer.purge();
                    }

                }

            }, delay, interval);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setColorCircle() {
        if (Utils.checkServerConnection()) {
            serverStatus.setFill(Color.GREEN);
        } else {
            serverStatus.setFill(Color.RED);
        }
        System.err.println("Color Set");

    }

    private ObservableList<String> getList() {
        List<String> list = new ArrayList<String>();
        list.add("Categories");
        list.add("Organization");
        return FXCollections.observableArrayList(list);
    }
    private EnrollUtilService utilService = new EnrollUtilService();

    public void setTableValue() {
        tableOperation();
        table.setItems(utilService.getUtilsList(type));
        // System.out.println("Categories : " + utilService.getUtilsList("Categories"));
    }

    public static void main(String args[]) {
        SeeEnrollTypeController a = new SeeEnrollTypeController();
        a.setTableValue();
    }

    public void tableOperation() {
        EnrollTypeController.setController(this);
        PersonTableUtil.setController(this);
        ObservableList<TableColumn> tcList = table.getColumns();
        for (TableColumn tc : tcList) {
            switch (tc.getId()) {
                case "name":
                    PersonTableUtil.decorateTypeNameColumn(tc);
                    break;
                case "color":
                    PersonTableUtil.decorateTypeColorColumn(tc);
                    break;
//                case "action":
//                    PersonTableUtil.decorateTypeActionColumn(tc);
//                    break;
                case "remarks":
                    PersonTableUtil.decorateTypeRemarksColumn(tc);
                    break;
                case "priority":
                    PersonTableUtil.decorateTypePriorityColumn(tc);
                    break;
                default:
                    break;
            }
        }
    }

    @FXML
    protected void setType(ActionEvent event) {
        if (typeComboBox.getValue() != null) {
            type = typeComboBox.getValue().toString();
        }
        setTableValue();
    }

//    @FXML
//    protected void addNew(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/istl/view/EnrollType.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.setTitle("Add EnrollType");
//            stage.getIcons().add(new Image("/logo/logo.png"));
//            stage.setResizable(false);
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.showAndWait();
//        } catch (Exception exc) {
//            exc.printStackTrace();
//        }
//    }

    private EnrollUtils getChanged(com.istl.accesscontrol.model.EnrollUtils enrollUtils) {
        EnrollUtils util = new EnrollUtils();
        util.setId(enrollUtils.getId());
        util.setName(enrollUtils.getName());
        if (enrollUtils.getColorCode() != null && enrollUtils.getColorCode().length() != 0) {

           // System.err.println("Before concat : " + enrollUtils.getColorCode());
            util.setColorCode("0x" + enrollUtils.getColorCode().substring(1, 7) + "ff");
          //  System.err.println("After concat : " + util.getColorCode());
        }

        util.setPriority(enrollUtils.getPriority());
        util.setType(enrollUtils.getType());
        util.setRemarks(enrollUtils.getRemarks());
        return util;
    }

    @FXML
    protected void syncAction(ActionEvent event) {
       // System.err.println("Called");
        String msg = "";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        int Editcount = 0;
        int Addcount = 0;
        if (Utils.checkServerConnection()) {
            response = utilService.getList();
            if (response.getList() != null && response.getList().size() != 0) {
               // System.err.println("Size of List : " + response.getList().size());
               
                for (com.istl.accesscontrol.model.EnrollUtils enrollUtils : response.getList()) {
                    System.err.println("========"+enrollUtils.toString());
                     msg += enrollUtils.getName();
                    if (utilService.isValidEnrollUtils(enrollUtils.getId())) {
                        utilService.save(getChanged(enrollUtils), 2);
                        Editcount++;
                        msg += " Updated\n";
                    } else {
                        utilService.save(getChanged(enrollUtils), 1);
                        Addcount++;
                        msg += " Added\n";
                    }
                }
                alert.setHeaderText(msg);
                alert.setContentText("Total Data : "+response.getList().size()+"\nTotal Updated : "+Editcount+"\nTotal Added : "+Addcount+"\nTotal Failed : "+(response.getList().size()-(Editcount+Addcount)));
                setTableValue();
                alert.show();
            } else {
                System.err.println("data : " + getList());
                Notification.errorMessage("No data found in your server");
            }
            
        } else {
            Notification.errorMessage("Chec your server connection");
        }

    }

}
