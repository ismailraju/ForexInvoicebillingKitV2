package com.istl.controller;

import com.canon.controller.CanonViewController;
import com.istl.controller.enrollment.fingerprint.FingerprintEnrollmentController;
import com.istl.enroll_kit.model.EnrollDistrict;
import com.istl.enroll_kit.model.EnrollDivision;
import com.istl.enroll_kit.model.EnrollNationalityLookup;
import com.istl.enroll_kit.model.EnrollPerson;
import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istl.enroll_kit.model.EnrollStation;

import com.istl.enroll_kit.model.EnrollUtils;

import com.istl.iris.controller.MainController;
import com.istl.service.EnrollStationService;
import com.istl.service.EnrollUtilService;
import com.istl.service.PersonService;
import com.istl.util.CanonUtil;
import com.istl.util.FieldValidation;
import com.istl.util.FingerPrintEnrollmentUtils;
import com.istl.util.IrisUtil;
import com.istl.util.Notification;
import com.istl.util.PersonTableUtil;
import com.istl.util.SelectKeyComboBoxListener;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;


public class EditController implements Initializable {

    @FXML
    private ComboBox<EnrollNationalityLookup> choiceBoxNationality;

    @FXML
    private ComboBox<EnrollDivision> choiceBoxPresentDivision;
    @FXML
    private ComboBox<EnrollDistrict> choiceBoxPresentDistrict;
    @FXML
    private ComboBox<EnrollStation> choiceBoxPresentPoliceStation;
    @FXML
    private TextField presentPostCode;
    @FXML
    private TextArea presentAddress;

    @FXML
    private ComboBox<EnrollDivision> choiceBoxPermanentDivision;
    @FXML
    private ComboBox<EnrollDistrict> choiceBoxPermanentDistrict;
    @FXML
    private ComboBox<EnrollStation> choiceBoxPermanentPoliceStation;
    @FXML
    private TextField permanentPostCode;
    @FXML
    private TextArea permanentAddress;

    @FXML
    private ComboBox<EnrollDistrict> choiceBoxbirthPlace;
    @FXML
    private ImageView photoImage;
    @FXML
    private ImageView leftThumb;
    @FXML
    private ImageView leftIndex;
    @FXML
    private ImageView rightThumb;
    @FXML
    private ImageView rightIndex;
    //added by iqbal
    @FXML
    private ComboBox<String> bloodgrp;
    @FXML
    private ComboBox<EnrollUtils> categories;
    @FXML
    private ComboBox<EnrollUtils> organization;
    @FXML
    private TextField designation;

    @FXML
    private TextArea remarks;
//     @FXML
//    private ImageView rightrng;
//    @FXML
//    private ImageView rightsml;
//    @FXML
//    private TextField nameBn;
    @FXML
    private TextField nameEn;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField nid;
    @FXML
    private TextField nameFather;
    @FXML
    private TextField nameMother;
    @FXML
    private TextField nameSpouse;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton others;
    @FXML
    private RadioButton married;
    @FXML
    private RadioButton unmarried;
    @FXML
    private ImageView irisLeft;
    @FXML
    private ImageView irisRight;
    @FXML
    private Button saveButton;

    File fingerprintFile;
    File photoFile;

    byte[] image;
    byte[] fingerPrint;

    Stage dialogStage;
    EnrollPerson enrollPerson = new EnrollPerson();
    EnrollPersonBiometric biometric = new EnrollPersonBiometric();
    PersonService service = new PersonService();
    EnrollStationService stationService = new EnrollStationService();
    EnrollUtilService utilService = new EnrollUtilService();
   // PopOver pp = null;

    public void setImage(EnrollPersonBiometric bio) {
        if (bio.getPhoto() != null) {
            ByteArrayInputStream is = new ByteArrayInputStream(bio.getPhoto());
            photoImage.setImage(new Image(is));
            image = bio.getPhoto();
        } else {
            System.out.println("Image is not found");
        }
    }

   
    public ObservableList<String> getBloodList() {
        ObservableList<String> bloodList = FXCollections.observableArrayList();
        bloodList.add("A+");
        bloodList.add("A-");
        bloodList.add("B+");
        bloodList.add("B-");
        bloodList.add("O+");
        bloodList.add("O-");
        bloodList.add("AB+");
        bloodList.add("AB-");
        return bloodList;
    }

    public void initData(EnrollPerson person, EnrollPersonBiometric biometric) {

        this.enrollPerson = person;
        this.biometric = biometric;

        if (this.biometric.getId() != null) {

            if (biometric.getPhoto() != null) {
                ByteArrayInputStream is = new ByteArrayInputStream(biometric.getPhoto());
                photoImage.setImage(new Image(is));
            }
            if (biometric.getWsqLt() != null) {
                ByteArrayInputStream fing = new ByteArrayInputStream(biometric.getWsqLt());
                leftThumb.setImage(new Image(fing));
            }

            if (biometric.getWsqLi() != null) {
                ByteArrayInputStream fingIn = new ByteArrayInputStream(biometric.getWsqLi());
                leftIndex.setImage(new Image(fingIn));
            }

            if (biometric.getWsqRt() != null) {
                ByteArrayInputStream fingRth = new ByteArrayInputStream(biometric.getWsqRt());
                rightThumb.setImage(new Image(fingRth));

            }
            if (biometric.getWsqRi() != null) {
                ByteArrayInputStream fingRi = new ByteArrayInputStream(biometric.getWsqRi());
                rightIndex.setImage(new Image(fingRi));
            }

            if (biometric.getIrisLeft() != null) {
                ByteArrayInputStream irishLeft = new ByteArrayInputStream(biometric.getIrisLeft());
                irisLeft.setImage(new Image(irishLeft));
            }

            if (biometric.getIrisRight() != null) {
                ByteArrayInputStream irishRight = new ByteArrayInputStream(biometric.getIrisRight());
                irisRight.setImage(new Image(irishRight));
            }
        }

        if (person.getId() != null) {

            categories.setValue(person.getCategoriesId());
            organization.setValue(person.getOrganizationId());
            designation.setText(person.getDesignation());
            bloodgrp.setValue(person.getBloodGroup());
            presentAddress.setText(person.getPresentAddress());
            remarks.setText(person.getRemarks());
            //

            //
            choiceBoxNationality.setValue(person.getNationalityId());

            choiceBoxPermanentDivision.setValue(person.getPermanentDivisionId());
            choiceBoxPermanentDistrict.setValue(person.getPermanentDistrictId());
            choiceBoxPermanentPoliceStation.setValue(person.getPermanentStationId());
            if (person.getPermanentPostCode() != null) {
                permanentPostCode.setText(person.getPermanentPostCode().toString());
            }

            permanentAddress.setText(person.getPermanentAddress());

            choiceBoxPresentDivision.setValue(person.getPresentDivisionId());
            choiceBoxPresentDistrict.setValue(person.getPresentDistrictId());
            choiceBoxPresentPoliceStation.setValue(person.getPresentStationId());
            if (person.getPresentPostCode() != null) {
                presentPostCode.setText(person.getPresentPostCode().toString());
            }

            presentAddress.setText(person.getPresentAddress());

            choiceBoxbirthPlace.setValue(person.getPlaceOfBirth());

            nameEn.setText(person.getFirstNameEn());
            phoneNumber.setText(person.getMobileNumber());
            nid.setText(person.getNationalId());
            nameFather.setText(person.getFatherName());
            nameMother.setText(person.getMotherName());
            nameSpouse.setText(person.getSpouseName());

            if (person.getDateOfBirth() != null) {
                Date input = person.getDateOfBirth();
                Instant instant = input.toInstant();
                ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
                LocalDate date = zdt.toLocalDate();
                dateOfBirth.setValue(date);
            }

            if (person.getGender() != null) {
                if (person.getGender().getId() == 8) {
                    male.setSelected(true);
                } else if (person.getGender().getId() == 9) {
                    female.setSelected(true);
                } else {
                    others.setSelected(true);
                }
            }

            if (person.getMaritalStatus() != null) {
                if (person.getMaritalStatus() == 30) {
                    married.setSelected(true);
                } else {
                    unmarried.setSelected(true);
                }
            }

        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setBiometrics(EnrollPersonBiometric bio) {
        if (bio != null) {
            if (bio.getWsqLt() != null) {
                ByteArrayInputStream lt = new ByteArrayInputStream(bio.getWsqLt());
                leftThumb.setImage(new Image(lt));
            }
            if (bio.getWsqLi() != null) {
                ByteArrayInputStream li = new ByteArrayInputStream(bio.getWsqLi());
                leftIndex.setImage(new Image(li));

            }
            if (bio.getWsqRt() != null) {
                ByteArrayInputStream rt = new ByteArrayInputStream(bio.getWsqRt());
                rightThumb.setImage(new Image(rt));
            }
            if (bio.getWsqRi() != null) {
                ByteArrayInputStream ri = new ByteArrayInputStream(bio.getWsqRi());
                rightIndex.setImage(new Image(ri));
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("Need your finger print");
            alert.showAndWait();
        }
    }

    @FXML
    protected void photoUp(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        photoFile = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(photoFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            baos.flush();
            image = baos.toByteArray();
            biometric.setPhoto(image);
            baos.close();
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            photoImage.setImage(image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void fingerprintUp(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        fingerprintFile = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(fingerprintFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            fingerPrint = baos.toByteArray();
            baos.close();
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            leftThumb.setImage(image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void enrollmentAction(ActionEvent event) throws IOException {
        try {
            FingerPrintEnrollmentUtils f = new FingerPrintEnrollmentUtils();
            FingerprintEnrollmentController c = f.showPersonEditDialog(biometric);
            setBiometrics(biometric);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    protected void faceEnrollmentAction(ActionEvent event) throws IOException {

        try {
            CanonUtil canonUtil = new CanonUtil();
            CanonViewController canonController = canonUtil.showPersonEditDialog(biometric);
            setImage(biometric);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void setIris(EnrollPersonBiometric bio) throws IOException {
        if (bio.getIrisLeft() != null && bio.getIrisRight() != null) {
//             String leftPath = "./Images/leftIrisImage.bmp";
//             String rightPath = "./Images/rightIrisImage.bmp";
            System.out.println("Image found");

            ByteArrayInputStream il = new ByteArrayInputStream(bio.getIrisLeft());
            ByteArrayInputStream iR = new ByteArrayInputStream(bio.getIrisRight());
            BufferedImage bImage1 = ImageIO.read(il);
            BufferedImage bImage2 = ImageIO.read(iR);
            ImageIO.write(bImage1, "bmp", new File("il.bmp"));
            ImageIO.write(bImage2, "bmp", new File("iR.bmp"));
            irisLeft.setImage(SwingFXUtils.toFXImage(bImage1, null));
            irisRight.setImage(SwingFXUtils.toFXImage(bImage2, null));

        } else {
            System.out.println("Image is not found");
        }

    }

    @FXML
    protected void irishAction(ActionEvent event) throws IOException {

        try {
            IrisUtil canonUtil = new IrisUtil();
            MainController canonController = canonUtil.showPersonEditDialog(biometric);
            setIris(biometric);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    protected void updateAction(ActionEvent event) throws IOException {
        if (isInputValid()) {
            try {

                enrollPerson.setNationalityId(choiceBoxNationality.getValue());

                enrollPerson.setPermanentDivisionId(choiceBoxPermanentDivision.getValue());
                enrollPerson.setPermanentDistrictId(choiceBoxPermanentDistrict.getValue());
                enrollPerson.setPermanentStationId(choiceBoxPermanentPoliceStation.getValue());
                if (permanentPostCode.getText().length() != 0) {
                    enrollPerson.setPermanentPostCode(Short.valueOf(permanentPostCode.getText()));
                }
                enrollPerson.setPermanentAddress(permanentAddress.getText());

                enrollPerson.setPresentDivisionId(choiceBoxPresentDivision.getValue());
                enrollPerson.setPresentDistrictId(choiceBoxPresentDistrict.getValue());
                enrollPerson.setPresentStationId(choiceBoxPresentPoliceStation.getValue());
                if (presentPostCode.getText().length() != 0) {
                    enrollPerson.setPresentPostCode(Short.valueOf(presentPostCode.getText()));
                }
                enrollPerson.setPresentAddress(presentAddress.getText());

                enrollPerson.setPlaceOfBirth(choiceBoxbirthPlace.getValue());
                enrollPerson.setFirstNameEn(nameEn.getText());
                enrollPerson.setLastNameEn("");
                enrollPerson.setMobileNumber(phoneNumber.getText());
                enrollPerson.setNationalId(nid.getText());
                enrollPerson.setReferenceNo(nid.getText());
                enrollPerson.setFatherName(nameFather.getText());
                enrollPerson.setMotherName(nameMother.getText());
                enrollPerson.setSpouseName(nameSpouse.getText());

                //
                enrollPerson.setCategoriesId(categories.getValue());
                enrollPerson.setOrganizationId(organization.getValue());
                enrollPerson.setDesignation(designation.getText());
                enrollPerson.setBloodGroup(bloodgrp.getValue());

                enrollPerson.setRemarks(remarks.getText());

                //
                LocalDate d = dateOfBirth.getValue();
                Instant instant = Instant.from(d.atStartOfDay(ZoneId.systemDefault()));
                Date date = Date.from(instant);
                enrollPerson.setDateOfBirth(date);

                if (male.isSelected()) {
                    short value = 8;
                    PersonService gender = new PersonService();
                    enrollPerson.setGender(gender.getEnrollLookup(value));
                }

                if (female.isSelected()) {
                    short value = 9;
                    PersonService gender = new PersonService();
                    enrollPerson.setGender(gender.getEnrollLookup(value));
                }

                if (others.isSelected()) {
                    short value = 10;
                    PersonService gender = new PersonService();
                    enrollPerson.setGender(gender.getEnrollLookup(value));
                }

                if (married.isSelected()) {
                    short value = 30;
                    PersonService mariterial = new PersonService();
                    enrollPerson.setMaritalStatus(value);
                }

                if (unmarried.isSelected()) {
                    short value = 31;
                    PersonService mariterial = new PersonService();
                    enrollPerson.setMaritalStatus(value);
                }

                biometric.setPhoto(biometric.getPhoto());
                biometric.setWsqLt(biometric.getWsqLt());
                biometric.setWsqLi(biometric.getWsqLi());
                biometric.setWsqRt(biometric.getWsqRt());
                biometric.setWsqRi(biometric.getWsqRi());
                enrollPerson.setIsCompleted(0);
                if (biometric.getPhoto() != null) {
                    enrollPerson.setIsCompleted(1);
                }
                boolean data = service.personUpdate(enrollPerson, biometric);
                if (data) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("successfully message");
                    alert.setHeaderText("Done");
                    alert.setContentText("your data is updated");
                    alert.showAndWait();
                    try {
                        Stage st = (Stage) unmarried.getScene().getWindow();
                        st.close();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/istl/view/Search.fxml"));
                        AnchorPane pane = (AnchorPane) loader.load();
                        BorderPane border = LoginController.getRoot();
                        border.setCenter(pane);
                        SearchController controller = loader.getController();
                        controller.setTableValue();
                    } catch (IOException ex) {
                        Logger.getLogger(PersonTableUtil.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(PersonTableUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error message");
                    alert.setHeaderText("Error");
                    alert.setContentText("your data is not updated");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(() -> nameEn.requestFocus());
        saveButton.setDefaultButton(true);
        ///textarea tab navigation
        FieldValidation.textAreaNavigation(remarks);
        FieldValidation.textAreaNavigation(presentAddress);
        FieldValidation.textAreaNavigation(permanentAddress);
        ///

        dateOfBirth.setConverter(new StringConverter<LocalDate>() {
            String pattern = "dd/MM/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                dateOfBirth.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    dateOfBirth.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
                    return dateFormatter.format(date);
                } else {
                    return "";
                }

            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }

            
        });
        //

        bloodgrp.setItems(getBloodList());
        ObservableList<EnrollNationalityLookup> nList = service.getAllNationality();

        categories.setItems(utilService.getUtilsList("Categories"));
        organization.setItems(utilService.getUtilsList("Organization"));

        categories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollUtils>() {
            @Override
            public void changed(ObservableValue<? extends EnrollUtils> observable, EnrollUtils oldValue, EnrollUtils newValue) {
                if (newValue != null) {
                    remarks.setText(newValue.getRemarks());
                }
            }
        });
//        designation.setItems(utilService.getUtilsList("Designation"));

        choiceBoxNationality.setItems(nList);
        choiceBoxNationality.setValue(nList.get(18));
        

        choiceBoxPresentDivision.setItems(service.getAllDivision());
        choiceBoxPresentDivision.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollDivision>() {
            @Override
            public void changed(ObservableValue<? extends EnrollDivision> observable, EnrollDivision oldValue, EnrollDivision newValue) {
                if (newValue != null) {
                    choiceBoxPresentDistrict.setItems(service.getDistrictByID(newValue));
                }
            }
        });

        choiceBoxPresentDistrict.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollDistrict>() {
            @Override
            public void changed(ObservableValue<? extends EnrollDistrict> observable, EnrollDistrict oldValue, EnrollDistrict newValue) {
                if (newValue != null) {
                    choiceBoxPresentPoliceStation.setItems(stationService.getStationById(newValue));
                }
            }
        });

        choiceBoxPermanentDivision.setItems(service.getAllDivision());
        choiceBoxPermanentDivision.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollDivision>() {
            @Override
            public void changed(ObservableValue<? extends EnrollDivision> observable, EnrollDivision oldValue, EnrollDivision newValue) {
                if (newValue != null) {
                    choiceBoxPermanentDistrict.setItems(service.getDistrictByID(newValue));
                }
            }
        });

        choiceBoxPermanentDistrict.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollDistrict>() {
            @Override
            public void changed(ObservableValue<? extends EnrollDistrict> observable, EnrollDistrict oldValue, EnrollDistrict newValue) {
                if (newValue != null) {
                    choiceBoxPermanentPoliceStation.setItems(stationService.getStationById(newValue));
                }
            }
        });

        choiceBoxbirthPlace.setItems(service.getAllDistrict());
         new SelectKeyComboBoxListener(choiceBoxbirthPlace);
         new SelectKeyComboBoxListener(choiceBoxPermanentDivision);
         new SelectKeyComboBoxListener(choiceBoxPermanentDistrict);
         new SelectKeyComboBoxListener(choiceBoxPermanentPoliceStation);
          new SelectKeyComboBoxListener(choiceBoxPresentDivision);
         new SelectKeyComboBoxListener(choiceBoxPresentDistrict);
         new SelectKeyComboBoxListener(choiceBoxPresentPoliceStation);
         new SelectKeyComboBoxListener(choiceBoxNationality);
        new SelectKeyComboBoxListener(categories);
        new SelectKeyComboBoxListener(organization);
        new SelectKeyComboBoxListener(bloodgrp);
        

        nameEn.setOnKeyReleased((event) -> {
            FieldValidation.set(nameEn, "en","This Field is Mandatory");
            FieldValidation.keyevent(nameEn, event);

        });
        nameEn.setOnMouseClicked((event) -> {
            FieldValidation.set(nameEn, "en","This Field is Mandatory");
            if (nameEn.getText().length() == 0) {
                FieldValidation.removeColor(nameEn);
            }
        });
        nameFather.setOnKeyReleased((event) -> {
            FieldValidation.set(nameFather, "en","");
            FieldValidation.keyevent(nameFather, event);
        });
        nameFather.setOnMouseClicked((event) -> {
            FieldValidation.set(nameFather, "en","");
            if (nameFather.getText().length() == 0) {
                FieldValidation.removeColor(nameFather);
            }
        });
        nameMother.setOnKeyReleased((event) -> {
            FieldValidation.set(nameMother, "en","");
            FieldValidation.keyevent(nameMother, event);
        });
        nameMother.setOnMouseClicked((event) -> {
            FieldValidation.set(nameMother, "en","");
            if (nameMother.getText().length() == 0) {
                FieldValidation.removeColor(nameMother);
            }

        });
        nameSpouse.setOnKeyReleased((event) -> {
            FieldValidation.set(nameSpouse, "en","");
            FieldValidation.keyevent(nameSpouse, event);
        });
        nameSpouse.setOnMouseClicked((event) -> {

            FieldValidation.set(nameSpouse, "en","");
            if (nameSpouse.getText().length() == 0) {
                FieldValidation.removeColor(nameSpouse);
            }
        });
        permanentPostCode.setOnKeyReleased((event) -> {
            FieldValidation.set(permanentPostCode, "post","");

        });
        permanentPostCode.setOnMouseClicked((event) -> {
            FieldValidation.set(permanentPostCode, "post","");
            if (permanentPostCode.getText().length() == 0) {
                FieldValidation.removeColor(permanentPostCode);
            }
        });
        //
        presentPostCode.setOnKeyReleased((event) -> {
            FieldValidation.set(presentPostCode, "post","");

        });
        presentPostCode.setOnMouseClicked((event) -> {
            FieldValidation.set(presentPostCode, "post","");
            if (presentPostCode.getText().length() == 0) {
                FieldValidation.removeColor(presentPostCode);
            }
        });
        //
        nid.setOnKeyReleased((event) -> {

            FieldValidation.set(nid, "nid","This Field is Mandatory");
            if ((nid.getText().length() == 10 || nid.getText().length() == 17) && service.nidMatching(nid.getText(), enrollPerson)) {
                FieldValidation.setColor(nid);
                FieldValidation.checking(nid, "NID Duplicate", false,"This Field is Mandatory");
            }

        });
        nid.setOnMouseClicked((event) -> {

            FieldValidation.set(nid, "nid","This Field is Mandatory");
            if (nid.getText().length() == 0) {
                FieldValidation.removeColor(nid);

            }
            if ((nid.getText().length() == 10 || nid.getText().length() == 17) && service.nidMatching(nid.getText(), enrollPerson)) {
                FieldValidation.setColor(nid);
                FieldValidation.checking(nid, "NID Duplicate", false,"This Field is Mandatory");
            }

        });
        phoneNumber.setOnKeyReleased((event) -> {
            FieldValidation.set(phoneNumber, "phn","This Field is Mandatory");
            if (phoneNumber.getText().length() == 11 && service.mobileMatching(phoneNumber.getText(), enrollPerson)) {
                FieldValidation.setColor(phoneNumber);
                FieldValidation.checking(phoneNumber, "Phone Number Duplicate", false,"This Field is Mandatory");
            }
        });
        phoneNumber.setOnMouseClicked((event) -> {
            FieldValidation.set(phoneNumber, "phn","This Field is Mandatory");
            if (phoneNumber.getText().length() == 0) {
                FieldValidation.removeColor(phoneNumber);
            }

            if (phoneNumber.getText().length() == 11 && service.mobileMatching(phoneNumber.getText(), enrollPerson)) {
                FieldValidation.setColor(phoneNumber);
                FieldValidation.checking(phoneNumber, "Phone Number Duplicate", false,"This Field is Mandatory");
            }

        });
         designation.setOnKeyReleased((event) -> {
            FieldValidation.set(designation, "en", "This Field is Mandatory");
            FieldValidation.keyevent(designation, event);

        });
        designation.setOnMouseClicked((event) -> {
            FieldValidation.set(designation, "en", "This Field is Mandatory");
            if (designation.getText().length() == 0) {
                FieldValidation.removeColor(designation);
            }
        });
    }

    private boolean isInputValid() {
        String errorMessage = "";

//        if (nameBn.getText().length() > 98) {
//            errorMessage += "Bangla Name Length is too high!\n";
//        }
//        if (nameBn.getText() == null || nameBn.getText().length() == 0) {
//
//            errorMessage += "No valid Bangla Name !\n";
//
//        }
        if (nameEn.getText().length() > 49) {
            errorMessage += "English Name Length is too high!\n";
            FieldValidation.setColor(nameEn);
        }
        if (nameEn.getText() == null || nameEn.getText().length() == 0 || !nameEn.getText().matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {

            FieldValidation.setColor(nameEn);
            errorMessage += "No valid English Name !\n";

        }
        if (nameFather.getText().length() > 49) {
            FieldValidation.setColor(nameFather);
            errorMessage += "Father's Name Length is too high!\n";
        }
        if (nameFather.getText() != null && nameFather.getText().length() != 0 && !nameFather.getText().matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {

            FieldValidation.setColor(nameFather);
            errorMessage += "No valid Father's Name !\n";

        }

        if (nameMother.getText().length() > 49) {
            FieldValidation.setColor(nameMother);
            errorMessage += "Mother's Name Length is too high!\n";
        }
        if (nameMother.getText() != null && nameMother.getText().length() != 0 && !nameMother.getText().matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {

            FieldValidation.setColor(nameMother);
            errorMessage += "No valid Mother's Name !\n";

        }
        if (nameSpouse.getText().length() > 49) {
            FieldValidation.setColor(nameSpouse);
            errorMessage += "Spouse's Name Length is too high!\n";
        }
        if (nameSpouse.getText() != null && nameSpouse.getText().length() != 0 && !nameSpouse.getText().matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {

            FieldValidation.setColor(nameSpouse);
            errorMessage += "No valid Spouse's Name !\n";

        }

        if (phoneNumber.getText() == null || phoneNumber.getText().length() != 11 || !phoneNumber.getText().matches("[0-9]+")) {
            FieldValidation.setColor(phoneNumber);
            errorMessage += "No valid Mobile Number!\n";
        }

//        if (male.isSelected()) {
//
//        } else if (female.isSelected()) {
//
//        } else if (others.isSelected()) {
//
//        } else {
//            errorMessage += "Gender is not Selected!\n";
//        }
//
//        if (married.isSelected()) {
//
//        } else if (unmarried.isSelected()) {
//
//        } else {
//            errorMessage += "Mariterial Status is not Selected!\n";
//        }
        if (nid.getText() == null || (nid.getText().length() != 10 && nid.getText().length() != 17)) {
            FieldValidation.setColor(nid);
            errorMessage += "No valid National ID!\n";
        }

        if (dateOfBirth.getValue() == null) {
            dateOfBirth.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            
            errorMessage += "No valid Date of Birth!\n";
        }

        if (choiceBoxNationality.getValue() == null) {
            choiceBoxNationality.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
           
            errorMessage += "No valid Nationality!\n";
        }

        if (choiceBoxbirthPlace.getValue() == null) {
            choiceBoxbirthPlace.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
           
            errorMessage += "No valid Birth Place!\n";
        }

        if (choiceBoxPermanentDivision.getValue() == null) {
            choiceBoxPermanentDivision.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            
            errorMessage += "No valid PermanentDivision!\n";
        }

        if (choiceBoxPermanentDistrict.getValue() == null) {
            choiceBoxPermanentDistrict.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            
            errorMessage += "No valid Permanent District!\n";
        }

        if (choiceBoxPermanentPoliceStation.getValue() == null) {
            choiceBoxPermanentPoliceStation.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            
            errorMessage += "No valid Permanent Police Station!\n";
        }

        if (permanentPostCode.getText().length() != 0 && permanentPostCode.getText() != null && permanentPostCode.getText().length() != 4) {
            FieldValidation.setColor(permanentPostCode);
            errorMessage += "No valid Permanent Post Code!\n";
        }
        if (choiceBoxPresentDivision.getValue() == null) {
            choiceBoxPresentDivision.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            errorMessage += "No valid Present Division!\n";
        }

        if (choiceBoxPresentDistrict.getValue() == null) {
            choiceBoxPresentDistrict.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            errorMessage += "No valid Present District!\n";
        }

        if (choiceBoxPresentPoliceStation.getValue() == null) {
            choiceBoxPresentPoliceStation.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            errorMessage += "No valid Present Police Station!\n";
        }

        if (presentPostCode.getText().length() != 0 && presentPostCode.getText() != null && presentPostCode.getText().length() != 4) {
            FieldValidation.setColor(presentPostCode);
            errorMessage += "No valid Present Post Code!\n";
        }

//        if (presentAddress.getText().length() > 98) {
//            errorMessage += "Address length is too high!\n";
//        }
//        if (permanentAddress.getText() == null || permanentAddress.getText().length() == 0) {
//
//            errorMessage += "No valid Permanent Address!\n";
//
//        }
//        if (bloodgrp.getValue() == null) {
//            errorMessage += "No valid Blood Group!\n";
//        }
        if (categories.getValue() == null) {
            categories.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            errorMessage += "No valid Categories!\n";
        }
        if (organization.getValue() == null) {
            organization.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            errorMessage += "No valid Organization!\n";
        }
        if (designation.getText()== null || designation.getText().length() == 0) {
            FieldValidation.setColor(designation);
            errorMessage += "No valid Designation!\n";
        }
        if (remarks.getText().length() == 0) {
            remarks.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            errorMessage += "No valid Remarks Added!\n";
        }
        if (service.nidMatching(nid.getText(), enrollPerson)) {
            FieldValidation.setColor(nid);
            errorMessage += "Duplicate NID!\n";
        }
        if (service.mobileMatching(phoneNumber.getText(), enrollPerson)) {
            FieldValidation.setColor(phoneNumber);
            errorMessage += "Duplicate Phone Number!\n";
        }

//        if (image == null) {
//            errorMessage += "Insert a Image!\n";
//        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Notification.errorMessage("Mandatory fields should not be empty");
            return false;
        }
    }
}
