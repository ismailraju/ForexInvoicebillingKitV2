package com.istl.controller;

import com.istl.accesscontrol.model.Person;
import com.istl.dao.imp.PersonBiometricDAOImp;
import com.istl.enroll_kit.model.EnrollPerson;
import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istl.enroll_kit.service.AccessControlServiceManager;
import com.istl.enroll_kit.utility.AccessControlResponse;
import com.istl.util.Zone;
import com.istl.service.PersonService;
import com.istl.util.EnrollPersonBiometricConvert;
import com.istl.util.EnrollPersonConvert;

import com.istl.util.Notification;
import com.istl.util.PersonTableUtil;
import com.istl.util.Utils;
import com.istl.util.LoadingClass;

import java.io.IOException;

import java.net.URL;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ExportDataController extends LoadingClass implements Initializable {

    @FXML
    private TableView table;
    @FXML
    private ComboBox<Zone> ip;
    @FXML
    private Circle serverStatus;
    PersonService service = new PersonService();
    public static List<Zone> zoneList = new ArrayList<>();
    Stage stage = new Stage();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        table.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        ip.setItems(Utils.getIp());
        if (Utils.zone.getId() == null) {

            ip.getSelectionModel().select(0);
            Utils.zone = Utils.getIp().get(0);

        } else {
            ip.getSelectionModel().select(Utils.zone.getId() - 1);

        }
        setColorCircle();

        try {
            tableOparetion();
            setTableValue(Utils.zone.getId());

        } catch (ParseException ex) {
            Logger.getLogger(ExportDataController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ip.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Zone>() {
            @Override
            public void changed(ObservableValue<? extends Zone> observable, Zone oldValue, Zone newValue) {
                if (newValue != null) {
                    //System.out.println("set" + newValue);
                    Utils.zone = newValue;
                    try {
                        setColorCircle();
                        tableOparetion();
                        setTableValue(Utils.zone.getId());

                    } catch (ParseException ex) {
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
    }

    public void setTableValue(Integer id) throws ParseException {
        table.setItems(service.getPersonByStatus(id));
    }

    public void tableOparetion() {

        ObservableList<TableColumn> tcList = table.getColumns();
        for (TableColumn tc : tcList) {
            switch (tc.getId()) {
                case "NameEn":
                    PersonTableUtil.decorateFirstNameColumn(tc);
                    break;
                case "tnationality":
                    PersonTableUtil.decorateNationalityColumn(tc);
                    break;
                case "tnationbalID":
                    PersonTableUtil.decorateNationalIDColumn(tc);
                    break;
                case "tDateofBirth":
                    PersonTableUtil.decorateDateOfBirthColumn(tc);
                    break;
                case "tMobileNumber":
                    PersonTableUtil.decorateMobileNumberColumn(tc);
                    break;
                case "tFatherName":
                    PersonTableUtil.decorateFatherColumn(tc);
                    break;
                case "tMotherName":
                    PersonTableUtil.decorateMotherColumn(tc);
                    break;
                case "tblAction":
                    PersonTableUtil.decorateActionExportColumn(tc);
                    break;
                default:
                    break;
            }
        }
    }

    private void setColorCircle() {
        if (Utils.checkServerConnection()) {
            serverStatus.setFill(Color.GREEN);
        } else {
            serverStatus.setFill(Color.RED);
        }
//        System.err.println("Color Set");

    }

    public void refresh(Integer id) throws ParseException {
        tableOparetion();
        setTableValue(Utils.zone.getId());
    }

    @FXML
    protected void uploadAll(ActionEvent actionEvent) throws IOException, InterruptedException {
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Confirmation Dialog");
        al.setHeaderText("Are you sure to upload ?");
        Optional<ButtonType> result = al.showAndWait();

        if (result.get() == ButtonType.OK) {
            loadingFront("/com/istl/view/FXML.fxml");
            backGroundTask();
        }

    }

    private void backgroundRunningThread() {

        final Timer timer = new Timer();
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

    @Override
    public void backGroundTask() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");

        final Timer timer = new Timer();
        int delay = 0;
        int interval = 1000;
        try {
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    ///write code from here

                    String msg = "";
                    int count = 0;

                    if (Utils.checkServerConnection()) {
                        ObservableList<EnrollPerson> selectedItems = table.getSelectionModel().getSelectedItems();
                        System.out.println(selectedItems);

                        // TEST
                        ArrayList<EnrollPerson> selectedIDs = new ArrayList<>();
                        selectedItems.forEach((row) -> {
                            selectedIDs.add(row);
                        });

                        ListIterator<EnrollPerson> iterator = selectedIDs.listIterator();

                        while (iterator.hasNext()) {
                            EnrollPerson ePerson = (EnrollPerson) iterator.next();
                            PersonService p = new PersonService();
                            ePerson = p.getPersonById(ePerson.getId());
                            PersonBiometricDAOImp bio = new PersonBiometricDAOImp();
                            EnrollPersonBiometric eBiometric = bio.getBiometricById(ePerson.getId());
                            Person person = EnrollPersonConvert.EnrollPersonToPerson(ePerson, EnrollPersonBiometricConvert.PersonBiometricConvertTOEnrollPersonBiometric(eBiometric));
                            msg += ePerson.getFirstNameEn();
                            Short s = 1;
                            person.setStatus(s);

                            try {
                                AccessControlServiceManager acs = new AccessControlServiceManager();
                                AccessControlResponse res = acs.addEnrollPersonAndBiometric(person);

                                msg += " " + res.getResponseMessage() + "\n";

                                if (res.getResponseMessage() == null) {
                                    msg = "Please Check Your Server Connection";
                                }

                                if (res.isOperationResult()) {
                                    count++;
                                    PersonService personService = new PersonService();
                                    ePerson.setStatus(ePerson.getStatus() + Utils.zone.getId() + ",");
                                    boolean onlyPersonUpdate = personService.onlyPersonUpdate(ePerson);
                                }

                            } catch (Exception e) {
                                System.out.println("========decorateActionExportColumn==========" + e);
                                e.printStackTrace();
                            }
                        }
                        alert.setHeaderText(msg);
                        alert.setContentText("Successfully uploaded : " + count + "\nUploaded failed : " + (selectedItems.size() - count));
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        //alert.show();

                        System.err.println("Message : " + msg);
                    } else {
                        Notification.errorMessage("Check Your Server Connection");
                    }

                    //end code here
                    timer.cancel();
                    timer.purge();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            primaryStage.close();

                            //write code here
                            alert.show();
                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/com/istl/view/ExportData.fxml"));
                                AnchorPane pane = (AnchorPane) loader.load();
                                BorderPane border = LoginController.getRoot();
                                border.setCenter(pane);
                            } catch (IOException iOException) {
                            }
                            //end code here
                        }
                    });

                    //
                }

            }, delay, interval
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
