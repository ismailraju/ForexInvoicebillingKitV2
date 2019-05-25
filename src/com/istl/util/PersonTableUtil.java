package com.istl.util;

import com.istl.accesscontrol.model.Person;
import com.istl.accesscontrol.model.PersonBiometric;
import com.istl.controller.EditController;
import com.istl.controller.EnrollTypeController;
import com.istl.controller.ExportDataController;
import com.istl.controller.LoginController;
import com.istl.controller.SearchController;
import com.istl.controller.SeeEnrollTypeController;
import com.istl.controller.ShowController;
import com.istl.dao.imp.ParsonDAOImp;
import com.istl.dao.imp.PersonBiometricDAOImp;
import com.istl.enroll_kit.model.EnrollNationalityLookup;
import com.istl.enroll_kit.model.EnrollPerson;
import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istl.enroll_kit.model.EnrollUtils;
import com.istl.enroll_kit.service.AccessControlServiceManager;
import com.istl.enroll_kit.utility.AccessControlResponse;
import com.istl.service.EnrollUtilService;
import com.istl.service.PersonService;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class PersonTableUtil {

    static TableView<EnrollPerson> personTable;
    private static SeeEnrollTypeController controller;
    public static Stage primaryStage;

    public static TableView<EnrollPerson> getPersonTable() {
        return personTable;
    }

    public static void setPersonTable(TableView<EnrollPerson> personTable) {
        PersonTableUtil.personTable = personTable;
    }

    public static EnrollUtilService getEnrollUtilService() {
        return enrollUtilService;
    }

    public static void setEnrollUtilService(EnrollUtilService enrollUtilService) {
        PersonTableUtil.enrollUtilService = enrollUtilService;
    }

    public static SeeEnrollTypeController getController() {
        return controller;
    }

    public static void setController(SeeEnrollTypeController controller) {
        PersonTableUtil.controller = controller;
    }

    public static EnrollUtilService enrollUtilService = new EnrollUtilService();

    public static void decorateFirstNameColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollPerson, String>("firstNameEn"));
    }

//    public static void decorateLastNameColumn(TableColumn tCol) {
//        tCol.setCellValueFactory(new PropertyValueFactory<EnrollPerson, String>("firstNameLocal"));
//    }
    public static void decorateNationalityColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollPerson, EnrollNationalityLookup>("nationalityId"));
    }

    public static void decorateNationalIDColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollPerson, String>("nationalId"));
    }

    public static void decorateDateOfBirthColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollPerson, Date>("dateOfBirth"));
        tCol.setCellFactory(new SearchController.ColumnFormatter<>(DateTimeFormatter.ofPattern("MM/dd/yyyy")));

    }

    public static void decorateMobileNumberColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollPerson, String>("mobileNumber"));
    }

    public static void decorateFatherColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollPerson, String>("fatherName"));
    }

    public static void decorateMotherColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollPerson, String>("motherName"));
    }

    public static void decorateStatusColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollPerson, Integer>("isCompleted"));
        tCol.setCellFactory(param -> new StatusTableCell<>());
    }
    ///

    public static void decorateTypeNameColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollUtils, String>("name"));
    }

    public static void decorateTypeRemarksColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollUtils, String>("remarks"));
    }

    public static void decorateTypePriorityColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollUtils, Integer>("priority"));
    }

    public static void decorateTypeColorColumn(TableColumn tCol) {
        tCol.setCellValueFactory(new PropertyValueFactory<EnrollUtils, String>("colorCode"));
        tCol.setCellFactory(param -> new ColorTableCell<>());
    }

    public static class ColorTableCell<EnrollUtils> extends TableCell<EnrollUtils, String> {

        public ColorTableCell() {
            super();
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            }
            try {
                if (item != null) {
                    Color c = Color.web(item, 1.0);
                    Label l = new Label();
                    l.setMaxSize(100, 20);
                    l.setStyle("-fx-border-color: black;");
                    l.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
                    setGraphic(l);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class StatusTableCell<EnrollPerson> extends TableCell<EnrollPerson, Integer> {

        public StatusTableCell() {
            super();
        }

        @Override
        public void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            }
            try {
                if (item != null) {
                    if (item == 0) {
                        setGraphic(new Label("Incomplete"));
                    } else {
                        setGraphic(new Label("Complete"));
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
///

    public static void decorateTypeActionColumn(TableColumn tCol) {
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                TableCell cell = new TableCell() {
                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        final TableCell thisCell = this;
                        if (!empty) {
                            ImageView delView = new ImageView("/com/istl/resource/001-bin.png");
                            ImageView editView = new ImageView("/com/istl/resource/002-edit.png");

                            HBox hb = new HBox(3);
                            Button delBtn = new Button(null, delView);
                            delBtn.setPrefSize(20, 20);
                            delBtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    Alert alert = new Alert(AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText("Are you sure to delete ?");
                                    Optional<ButtonType> result = alert.showAndWait();
                                    if (result.get() == ButtonType.OK) {
                                        TableRow tr = thisCell.getTableRow();
                                        EnrollUtils utils = (EnrollUtils) tr.getItem();
                                        boolean b = enrollUtilService.removeItem(utils);
                                        if (b) {
                                            Notification.successfullyMessage("Successfully Removed");
                                            SeeEnrollTypeController cn = getController();
                                            cn.setTableValue();
                                        }
                                    }
                                }
                            });

                            Button editBtn = new Button(null, editView);
                            editBtn.setPrefSize(20, 20);
                            editBtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    TableRow tr = thisCell.getTableRow();
                                    EnrollUtils utils = (EnrollUtils) tr.getItem();
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/istl/view/EnrollType.fxml"));
                                        Parent root = loader.load();
                                        Scene scene = new Scene(root);
                                        Stage stage = new Stage();
                                        stage.setScene(scene);
                                        stage.setTitle("Add EnrollType");
                                        stage.setResizable(false);
                                        stage.initModality(Modality.APPLICATION_MODAL);
                                        stage.show();
                                        EnrollTypeController cont = loader.getController();
                                        cont.initData(utils);
                                        SeeEnrollTypeController cn = getController();
                                        cn.setTableValue();
                                    } catch (Exception exc) {
                                        exc.printStackTrace();
                                    }
                                }
                            });

                            hb.getChildren().addAll(editBtn, delBtn);
                            this.setText(null);
                            this.setGraphic(hb);
                        }
                    }
                };
                return cell;
            }
        };
        tCol.setCellFactory(cellFactory);
    }
//

    public static void decorateActionColumn(TableColumn tCol) {
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                TableCell cell = new TableCell() {
                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        final TableCell thisCell = this;
                        if (!empty) {
                            ImageView delView = new ImageView("/com/istl/resource/001-bin.png");
                            ImageView editView = new ImageView("/com/istl/resource/002-edit.png");
                            ImageView openView = new ImageView("/com/istl/resource/ico_view.png");

                            HBox hb = new HBox(3);
                            Button delBtn = new Button(null, delView);
                            delBtn.setPrefSize(20, 20);
                            delBtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    Alert alert = new Alert(AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText("Are you sure to delete ?");
                                    Optional<ButtonType> result = alert.showAndWait();
                                    if (result.get() == ButtonType.OK) {
                                        TableRow tr = thisCell.getTableRow();

                                        EnrollPerson thisPerson = (EnrollPerson) tr.getItem();

                                        ParsonDAOImp personImp = new ParsonDAOImp();
                                        int value = personImp.personDelete(thisPerson);
                                        if (value != 0) {
                                            try {
                                                Notification.successfullyMessage("Your Data is deleted");
                                                FXMLLoader loader = new FXMLLoader();
                                                loader.setLocation(getClass().getResource("/com/istl/view/Search.fxml"));
                                                AnchorPane pane = (AnchorPane) loader.load();
                                                BorderPane border = LoginController.getRoot();
                                                border.setCenter(pane);
                                                SearchController controller = loader.getController();
                                                controller.setTableValue();

                                            } catch (IOException ex) {
                                                Logger.getLogger(PersonTableUtil.class
                                                        .getName()).log(Level.SEVERE, null, ex);

                                            } catch (ParseException ex) {
                                                Logger.getLogger(PersonTableUtil.class
                                                        .getName()).log(Level.SEVERE, null, ex);
                                            }
                                        } else {
                                            Notification.errorMessage("Your Data is not deleted");
                                        }
                                    } else {
                                        // ... user chose CANCEL or closed the dialog
                                    }

                                }
                            });

                            Button editBtn = new Button(null, editView);
                            editBtn.setPrefSize(20, 20);
                            editBtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        Stage stage = new Stage();
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/istl/view/Edit.fxml"));
                                        Parent root = loader.load();
                                        stage.setScene(new Scene(root));
                                        stage.setTitle("Information Details");
                                        stage.initModality(Modality.WINDOW_MODAL);
                                        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                        EditController controller = loader.<EditController>getController();
                                        TableRow tr = thisCell.getTableRow();
                                        EnrollPerson thisPerson = (EnrollPerson) tr.getItem();
//                                        ParsonDAOImp personImp = new ParsonDAOImp();
                                        PersonBiometricDAOImp bio = new PersonBiometricDAOImp();
                                        EnrollPersonBiometric biometric = bio.getBiometricById(thisPerson.getId());
                                        controller.initData(thisPerson, biometric);
                                        stage.setOnCloseRequest((ev) -> {
                                            FieldValidation.exit();
                                        });
                                        stage.show();
                                    } catch (Exception exc) {
                                        exc.printStackTrace();
                                    }
                                }
                            });

                            Button openBtn = new Button(null, openView);
                            openBtn.setPrefSize(20, 20);
                            openBtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        Stage stage = new Stage();
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/istl/view/Show.fxml"));
                                        Parent root = loader.load();
                                        stage.setScene(new Scene(root));
                                        stage.setTitle("Information Details");
                                        stage.initModality(Modality.WINDOW_MODAL);
                                        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                                        ShowController controller = loader.<ShowController>getController();
                                        TableRow tr = thisCell.getTableRow();
                                        EnrollPerson thisPerson = (EnrollPerson) tr.getItem();
//                                        ParsonDAOImp personImp = new ParsonDAOImp();
                                        PersonBiometricDAOImp bio = new PersonBiometricDAOImp();
                                        EnrollPersonBiometric biometric = bio.getBiometricById(thisPerson.getId());
                                        controller.initData(thisPerson, biometric);
                                        stage.show();
                                    } catch (Exception exc) {
                                        exc.printStackTrace();
                                    }
                                }
                            });
                            hb.getChildren().addAll(openBtn, editBtn, delBtn);
                            this.setText(null);
                            this.setGraphic(hb);
                        }
                    }
                };
                return cell;
            }
        };
        tCol.setCellFactory(cellFactory);
    }

    public static void decorateActionExportColumn(TableColumn tCol) {
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                TableCell cell = new TableCell() {
                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        final TableCell thisCell = this;
                        if (!empty) {
                            ImageView delView = new ImageView("/com/istl/resource/003-export.png");

                            HBox hb = new HBox(3);
                            Button exportBtn = new Button(null, delView);
                            exportBtn.setPrefSize(160, 10);
                            exportBtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    Alert al = new Alert(AlertType.CONFIRMATION);
                                    al.setTitle("Confirmation Dialog");
                                    al.setHeaderText("Are you sure to upload ?");

                                    Optional<ButtonType> result = al.showAndWait();
                                    if (result.get() == ButtonType.OK) {

                                        if (Utils.checkServerConnection()) {
                                            try {
                                                Parent root = FXMLLoader.load(getClass().getResource("/com/istl/view/FXML.fxml"));
                                                Scene scene = new Scene(root);
                                                scene.setFill(Color.TRANSPARENT);
                                                Stage stage1 = new Stage();
                                                primaryStage = stage1;
                                                stage1.initModality(Modality.APPLICATION_MODAL);
                                                stage1.initStyle(StageStyle.UNDECORATED);
                                                stage1.initStyle(StageStyle.TRANSPARENT);
                                                stage1.setScene(scene);
                                                stage1.show();
                                            } catch (Exception exception) {
                                                exception.printStackTrace();
                                            }
                                            Alert alert = new Alert(AlertType.ERROR);
                                            alert.setTitle("Enroll Failed");

                                            final Timer timer = new Timer();
                                            int delay = 0;
                                            int interval = 1000;
                                            try {
                                                timer.scheduleAtFixedRate(new TimerTask() {
                                                    public void run() {
                                                        TableRow tr = thisCell.getTableRow();
                                                        EnrollPerson ePerson = (EnrollPerson) tr.getItem();
                                                        PersonService p = new PersonService();
                                                        ePerson = p.getPersonById(ePerson.getId());
                                                        PersonBiometricDAOImp bio = new PersonBiometricDAOImp();
                                                        EnrollPersonBiometric eBiometric = bio.getBiometricById(ePerson.getId());

                                                        Person person = EnrollPersonConvert.EnrollPersonToPerson(ePerson, EnrollPersonBiometricConvert.PersonBiometricConvertTOEnrollPersonBiometric(eBiometric));

                                                        Short s = 1;
                                                        person.setStatus(s);

                                                        try {
                                                            AccessControlServiceManager acs = new AccessControlServiceManager();
                                                            AccessControlResponse res = acs.addEnrollPersonAndBiometric(person);
                                                            if (res.isOperationResult()) {
                                                                alert.setAlertType(AlertType.INFORMATION);
                                                                alert.setTitle("Information");
                                                            }
                                                            alert.setContentText(res.getErrorMsg());
                                                            alert.setHeaderText(res.getResponseMessage());
                                                            if (res.getResponseMessage() == null) {
                                                                alert.setHeaderText("Please Check Your Server Connection");
                                                            }
                                                            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

                                                            if (res.isOperationResult()) {
                                                                PersonService personService = new PersonService();
                                                                System.out.println("status before " + ePerson.getStatus());
                                                                ePerson.setStatus(ePerson.getStatus() + Utils.zone.getId() + ",");
                                                                System.out.println("status after " + ePerson.getStatus());
                                                                personService.onlyPersonUpdate(ePerson);
                                                            }

                                                        } catch (Exception e) {
                                                            System.out.println("========decorateActionExportColumn==========" + e);
                                                            e.printStackTrace();
                                                        }

                                                        timer.cancel();
                                                        timer.purge();

                                                        Platform.runLater(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                primaryStage.close();
                                                                alert.show();

                                                                try {
                                                                    FXMLLoader loader = new FXMLLoader();
                                                                    loader.setLocation(getClass().getResource("/com/istl/view/ExportData.fxml"));
                                                                    AnchorPane pane = (AnchorPane) loader.load();
                                                                    BorderPane border = LoginController.getRoot();
                                                                    border.setCenter(pane);
                                                                } catch (IOException iOException) {
                                                                }

                                                            }
                                                        });
                                                    }

                                                }, delay, interval
                                                );
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            Notification.errorMessage("Check your server connection");
                                        }

                                    } else {
                                        Notification.errorMessage("Check your server connection");
                                    }
                                }
                            }
                            );

                            hb.getChildren()
                                    .addAll(exportBtn);

                            this.setText(
                                    null);

                            this.setGraphic(hb);
                        }
                    }
                };
                return cell;
            }
        };
        tCol.setCellFactory(cellFactory);
    }
}
