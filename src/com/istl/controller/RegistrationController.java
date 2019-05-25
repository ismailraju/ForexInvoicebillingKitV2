package com.istl.controller;

import com.canon.controller.CanonViewController;
import com.istl.controller.enrollment.fingerprint.FingerprintEnrollmentController;
import com.istl.enroll_kit.model.EnrollDistrict;
import com.istl.enroll_kit.model.EnrollDivision;
import com.istl.enroll_kit.model.EnrollLookup;
import com.istl.enroll_kit.model.EnrollNationalityLookup;
import com.istl.enroll_kit.model.EnrollPerson;
import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istl.enroll_kit.model.EnrollStation;

import com.istl.enroll_kit.model.EnrollUtils;

import com.istl.iris.controller.MainController;
import com.istl.service.EnrollStationService;
import com.istl.service.EnrollUtilService;
import com.istl.service.PersonService;
import com.istl.util.FingerPrintEnrollmentUtils;
import com.istl.util.Notification;
import com.istl.util.User;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;


import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import com.istl.util.CanonUtil;
import com.istl.util.FieldValidation;
import com.istl.util.IrisUtil;
import com.istl.util.SelectKeyComboBoxListener;

import java.time.format.DateTimeFormatter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.util.StringConverter;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
 

public class RegistrationController implements Initializable {

    @FXML
    private ComboBox<EnrollNationalityLookup> choiceBoxNationality;
    @FXML
    private ComboBox<EnrollDivision> choiceBoxPermanentDivision;
    @FXML
    private ComboBox<EnrollDistrict> choiceBoxPermanentDistrict;
    @FXML
    private ComboBox<EnrollStation> choiceBoxPermanentPoliceStation;
    @FXML
    private ComboBox<EnrollDivision> choiceBoxPresentDivision;
    @FXML
    private ComboBox<EnrollDistrict> choiceBoxPresentDistrict;
    @FXML
    private ComboBox<EnrollStation> choiceBoxPresentPoliceStation;
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
    private TextArea presentAddress;
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
    private TextArea permanentAddress;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private TextField permanentPostCode;
    @FXML
    private TextField presentPostCode;
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

    //boolean flag = false;
    EnrollPersonBiometric bio = new EnrollPersonBiometric();
    PersonService service = new PersonService();
    EnrollUtilService utilService = new EnrollUtilService();
    EnrollStationService stationService = new EnrollStationService();
    EnrollPerson p = new EnrollPerson();

    ObservableList<EnrollNationalityLookup> nList = service.getAllNationality();

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

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> nameEn.requestFocus());
        saveButton.setDefaultButton(true);

        ///textarea tab navigation
        FieldValidation.textAreaNavigation(remarks);
        FieldValidation.textAreaNavigation(presentAddress);
        FieldValidation.textAreaNavigation(permanentAddress);
        ///
        System.out.println(User.getUser());
        bloodgrp.setItems(getBloodList());

        choiceBoxNationality.setItems(nList);
        choiceBoxNationality.setValue(nList.get(18));

        choiceBoxPermanentDivision.setItems(service.getAllDivision());
        choiceBoxPresentDivision.setItems(service.getAllDivision());

        categories.setItems(utilService.getUtilsList("Categories"));
        organization.setItems(utilService.getUtilsList("Organization"));

        categories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollUtils>() {
            @Override
            public void changed(ObservableValue<? extends EnrollUtils> observable, EnrollUtils oldValue, EnrollUtils newValue) {
                if (newValue != null) {
                    categories.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
                    remarks.setText(newValue.getRemarks());
                }
            }
        });
        organization.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollUtils>() {
            @Override
            public void changed(ObservableValue<? extends EnrollUtils> observable, EnrollUtils oldValue, EnrollUtils newValue) {
                if (newValue != null) {
                    organization.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");

                }
            }
        });

        choiceBoxPermanentDivision.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollDivision>() {
            @Override
            public void changed(ObservableValue<? extends EnrollDivision> observable, EnrollDivision oldValue, EnrollDivision newValue) {
                if (newValue != null) {
                    choiceBoxPermanentDivision.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
                    choiceBoxPermanentDistrict.setItems(service.getDistrictByID(newValue));
                }
            }
        });

        choiceBoxPermanentDistrict.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollDistrict>() {
            @Override
            public void changed(ObservableValue<? extends EnrollDistrict> observable, EnrollDistrict oldValue, EnrollDistrict newValue) {
                if (newValue != null) {
                    choiceBoxPermanentDistrict.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
                    choiceBoxPermanentPoliceStation.setItems(stationService.getStationById(newValue));
                }
            }
        });
        //
        choiceBoxPresentDivision.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollDivision>() {
            @Override
            public void changed(ObservableValue<? extends EnrollDivision> observable, EnrollDivision oldValue, EnrollDivision newValue) {
                if (newValue != null) {
                    choiceBoxPresentDivision.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
                    choiceBoxPresentDistrict.setItems(service.getDistrictByID(newValue));
                }
            }
        });

        choiceBoxPresentDistrict.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollDistrict>() {
            @Override
            public void changed(ObservableValue<? extends EnrollDistrict> observable, EnrollDistrict oldValue, EnrollDistrict newValue) {
                if (newValue != null) {
                    choiceBoxPresentDistrict.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
                    choiceBoxPresentPoliceStation.setItems(stationService.getStationById(newValue));
                }
            }
        });

        ///color removing
        choiceBoxbirthPlace.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollDistrict>() {
            @Override
            public void changed(ObservableValue<? extends EnrollDistrict> observable, EnrollDistrict oldValue, EnrollDistrict newValue) {
                if (newValue != null) {
                    choiceBoxbirthPlace.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
                }
            }
        });
        choiceBoxPresentPoliceStation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollStation>() {
            @Override
            public void changed(ObservableValue<? extends EnrollStation> observable, EnrollStation oldValue, EnrollStation newValue) {
                if (newValue != null) {
                    choiceBoxPresentPoliceStation.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
                }
            }
        });
        choiceBoxPermanentPoliceStation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollStation>() {
            @Override
            public void changed(ObservableValue<? extends EnrollStation> observable, EnrollStation oldValue, EnrollStation newValue) {
                if (newValue != null) {
                    choiceBoxPermanentPoliceStation.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
                }
            }
        });

        //
        //
//        choiceBoxPermanentDivision
//choiceBoxPermanentDistrict
//
//choiceBoxPresentDivision
//choiceBoxPresentDistrict
//
//categories
//organization
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
//        dateOfBirth.getEditor().setDisable(true);
//        dateOfBirth.set
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

        male.setSelected(true);
        unmarried.setSelected(true);
        bloodgrp.setValue(getBloodList().get(2));
        remarks.setText("VIP");

        nameEn.setOnKeyReleased((event) -> {
            FieldValidation.set(nameEn, "en", "This Field is Mandatory");
            FieldValidation.keyevent(nameEn, event);

        });
        nameEn.setOnMouseClicked((event) -> {
            FieldValidation.set(nameEn, "en", "This Field is Mandatory");
            if (nameEn.getText().length() == 0) {
                FieldValidation.removeColor(nameEn);
            }
        });
        nameFather.setOnKeyReleased((event) -> {
            FieldValidation.set(nameFather, "en", "");
            FieldValidation.keyevent(nameFather, event);
        });
        nameFather.setOnMouseClicked((event) -> {
            FieldValidation.set(nameFather, "en", "");
            if (nameFather.getText().length() == 0) {
                FieldValidation.removeColor(nameFather);
            }
        });
        nameMother.setOnKeyReleased((event) -> {
            FieldValidation.set(nameMother, "en", "");
            FieldValidation.keyevent(nameMother, event);
        });
        nameMother.setOnMouseClicked((event) -> {
            FieldValidation.set(nameMother, "en", "");
            if (nameMother.getText().length() == 0) {
                FieldValidation.removeColor(nameMother);
            }

        });
        nameSpouse.setOnKeyReleased((event) -> {
            FieldValidation.set(nameSpouse, "en", "");
            FieldValidation.keyevent(nameSpouse, event);
        });
        nameSpouse.setOnMouseClicked((event) -> {

            FieldValidation.set(nameSpouse, "en", "");
            if (nameSpouse.getText().length() == 0) {
                FieldValidation.removeColor(nameSpouse);
            }
        });
        permanentPostCode.setOnKeyReleased((event) -> {
            FieldValidation.set(permanentPostCode, "post", "");

        });
        permanentPostCode.setOnMouseClicked((event) -> {
            FieldValidation.set(permanentPostCode, "post", "");
            if (permanentPostCode.getText().length() == 0) {
                FieldValidation.removeColor(permanentPostCode);
            }
        });
        //
        presentPostCode.setOnKeyReleased((event) -> {
            FieldValidation.set(presentPostCode, "post", "");

        });
        presentPostCode.setOnMouseClicked((event) -> {
            FieldValidation.set(presentPostCode, "post", "");
            if (presentPostCode.getText().length() == 0) {
                FieldValidation.removeColor(presentPostCode);
            }
        });
        //
        nid.setOnKeyReleased((event) -> {

            FieldValidation.set(nid, "nid", "This Field is Mandatory");
            if ((nid.getText().length() == 10 || nid.getText().length() == 17) && service.nidMatching(nid.getText(), p)) {
                FieldValidation.setColor(nid);
                FieldValidation.checking(nid, "NID Duplicate", false, "This Field is Mandatory");
            }

        });
        nid.setOnMouseClicked((event) -> {

            FieldValidation.set(nid, "nid", "This Field is Mandatory");
            if (nid.getText().length() == 0) {
                FieldValidation.removeColor(nid);

            }
            if ((nid.getText().length() == 10 || nid.getText().length() == 17) && service.nidMatching(nid.getText(), p)) {
                FieldValidation.setColor(nid);
                FieldValidation.checking(nid, "NID Duplicate", false, "This Field is Mandatory");
            }

        });
        phoneNumber.setOnKeyReleased((event) -> {
            FieldValidation.set(phoneNumber, "phn", "This Field is Mandatory");
            if (phoneNumber.getText().length() == 11 && service.mobileMatching(phoneNumber.getText(), p)) {
                FieldValidation.setColor(phoneNumber);
                FieldValidation.checking(phoneNumber, "Phone Number Duplicate", false, "This Field is Mandatory");
            }
        });
        phoneNumber.setOnMouseClicked((event) -> {
            FieldValidation.set(phoneNumber, "phn", "This Field is Mandatory");
            if (phoneNumber.getText().length() == 0) {
                FieldValidation.removeColor(phoneNumber);
            }

            if (phoneNumber.getText().length() == 11 && service.mobileMatching(phoneNumber.getText(), p)) {
                FieldValidation.setColor(phoneNumber);
                FieldValidation.checking(phoneNumber, "Phone Number Duplicate", false, "This Field is Mandatory");
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

        //        
    }

    public boolean detected(String src) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String xmlFile = "C:/opencv/build/etc/haarcascades/haarcascade_frontalface_alt.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlFile);
        MatOfRect faceDetections = new MatOfRect();
        Mat m = Imgcodecs.imread(src);
        classifier.detectMultiScale(m, faceDetections);
        System.out.println(String.format("Detected %s faces",
                faceDetections.toArray().length));
        return faceDetections.toArray().length > 0;
    }

    @FXML
    protected void photoUp(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        photoFile = fileChooser.showOpenDialog(null);

        if (photoFile != null) {
            try {
                String src = photoFile.getAbsolutePath();//Imgcodecs.imread("Desktop/raju.jpg");
                BufferedImage bufferedImage = ImageIO.read(photoFile);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", baos);
                baos.flush();
                image = baos.toByteArray();
                baos.close();
                if (detected(src)) {
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    photoImage.setImage(image);
                } else {
                    Notification.errorMessage("Face Not Detected");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            Notification.errorMessage("Please Upload a Image");
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
            FingerprintEnrollmentController c = f.showPersonEditDialog(bio);
            setBiometrics(bio);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    protected void irishAction(ActionEvent event) {

        try {
            IrisUtil canonUtil = new IrisUtil();
            MainController canonController = canonUtil.showPersonEditDialog(bio);
            setIris(bio);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void resetAction(ActionEvent event) {
        clearAll();
    }

    @FXML
    protected void faceEnrollmentAction(ActionEvent event) throws IOException {
        try {

            CanonUtil canonUtil = new CanonUtil();
            CanonViewController canonController = canonUtil.showPersonEditDialog(bio);
            setImage(bio);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void setIris(EnrollPersonBiometric bio) throws IOException {
        if (bio.getIrisLeft() != null) {
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

    public void setImage(EnrollPersonBiometric bio) {
        if (bio.getPhoto() != null) {
            ByteArrayInputStream is = new ByteArrayInputStream(bio.getPhoto());
            photoImage.setImage(new Image(is));
            image = bio.getPhoto();
        } else {
            System.out.println("Image is not found");
        }
    }

    @FXML
    protected void submitAction(ActionEvent event) throws IOException {

        if (isInputValid()) {
            try {
                EnrollPerson person = new EnrollPerson();
                EnrollPersonBiometric biometric = new EnrollPersonBiometric();

                person.setNationalityId(choiceBoxNationality.getValue());

                person.setPermanentDivisionId(choiceBoxPermanentDivision.getValue());
                person.setPermanentDistrictId(choiceBoxPermanentDistrict.getValue());
                person.setPermanentStationId(choiceBoxPermanentPoliceStation.getValue());
                if (permanentPostCode.getText().length() != 0) {
                    person.setPermanentPostCode(Short.valueOf(permanentPostCode.getText()));
                }

                person.setPresentDivisionId(choiceBoxPresentDivision.getValue());
                person.setPresentDistrictId(choiceBoxPresentDistrict.getValue());
                person.setPresentStationId(choiceBoxPresentPoliceStation.getValue());
                if (presentPostCode.getText().length() != 0) {
                    person.setPresentPostCode(Short.valueOf(presentPostCode.getText()));
                }

                person.setPlaceOfBirth(choiceBoxbirthPlace.getValue());
                person.setFirstNameEn(nameEn.getText());
                person.setLastNameEn("");
//                person.setFirstNameLocal(nameBn.getText());
                person.setMobileNumber(phoneNumber.getText());
                person.setNationalId(nid.getText());
                person.setReferenceNo(nid.getText());
                person.setFatherName(nameFather.getText());
                person.setMotherName(nameMother.getText());
                person.setSpouseName(nameSpouse.getText());
                person.setPermanentAddress(permanentAddress.getText());

                if (dateOfBirth.getValue() != null) {
                    LocalDate ldate = dateOfBirth.getValue();
                    Instant instant = Instant.from(ldate.atStartOfDay(ZoneId.systemDefault()));
                    Date date = Date.from(instant);
                    System.out.println(date);
                    person.setDateOfBirth(date);

                }

                //added by iqbal
                person.setBloodGroup(bloodgrp.getValue());
                person.setCategoriesId(categories.getValue());
                person.setOrganizationId(organization.getValue());

                person.setDesignation(designation.getText());
                person.setPresentAddress(presentAddress.getText());
                if (remarks.getText().length() != 0) {
                    person.setRemarks(remarks.getText());
                }
                ///
                if (male.isSelected()) {
                    short value = 8;
                    PersonService gender = new PersonService();
                    person.setGender(gender.getEnrollLookup(value));
                }

                if (female.isSelected()) {
                    short value = 9;
                    PersonService gender = new PersonService();
                    person.setGender(gender.getEnrollLookup(value));
                }

                if (others.isSelected()) {
                    short value = 10;
                    PersonService gender = new PersonService();
                    person.setGender(gender.getEnrollLookup(value));
                }

                if (married.isSelected()) {
                    short value = 30;
                    person.setMaritalStatus(value);
                }

                if (unmarried.isSelected()) {
                    short value = 31;
                    person.setMaritalStatus(value);
                }
                person.setStatus(",");

                /**
                 * ********************
                 *
                 */
                EnrollLookup lookup = new EnrollLookup();
                lookup.setId((short) 11);
                EnrollLookup appStatus = new EnrollLookup();
                appStatus.setId((short) 15);
                EnrollStation enrStation = new EnrollStation();
                enrStation.setId((short) choiceBoxPermanentPoliceStation.getValue().getId());
                person.setCreatedBy(User.getUser().getId());
                person.setCreationDate(new Date());
                person.setLastUpdatedBy(1);
                person.setLastUpdateDate(new Date());
                person.setAfisMatchStatus(lookup);
                person.setApplicationStatus(appStatus);
                person.setEnrollStationId(enrStation);
                person.setTextMatchStatus(lookup);
                biometric.setPhoto(image);
                biometric.setWsqLt(bio.getWsqLt());
                biometric.setWsqLi(bio.getWsqLi());
                biometric.setWsqRt(bio.getWsqRt());
                biometric.setWsqRi(bio.getWsqRi());
                biometric.setWsqLm(bio.getWsqLm());
                biometric.setWsqLr(bio.getWsqLr());
                biometric.setWsqLs(bio.getWsqLs());
                biometric.setWsqRm(bio.getWsqRm());
                biometric.setWsqRr(bio.getWsqRr());
                biometric.setWsqRs(bio.getWsqRs());
                biometric.setIrisLeft(bio.getIrisLeft());
                biometric.setIrisRight(bio.getIrisRight());

                person.setIsCompleted(1);
                if (biometric.getPhoto() == null) {
                    person.setIsCompleted(0);
                }

                boolean result = service.addPersonData(person, biometric);
                if (result) {
                    Notification.successfullyMessage("Registration Successful");
                    clearAll();
                } else {
                    Notification.errorMessage("Fail to create.Please check all fields");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
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

//            ByteArrayInputStream lm=new ByteArrayInputStream(bio.getWsqLm());
            //  ByteArrayInputStream lr=new ByteArrayInputStream(bio.getWsqLr());
            // ByteArrayInputStream ls=new ByteArrayInputStream(bio.getWsqLs());
            // ByteArrayInputStream rm=new ByteArrayInputStream(bio.getWsqRm());
//            ByteArrayInputStream rr=new ByteArrayInputStream(bio.getWsqRr());
//            ByteArrayInputStream rs=new ByteArrayInputStream(bio.getWsqRs());
//            leftmdl.setImage(new Image(lm));
//            leftrng.setImage(new Image(lr));
//            leftsml.setImage(new Image(ls));
//            rightmdl.setImage(new Image(rm));
//            rightrng.setImage(new Image(rr));
//            rightsml.setImage(new Image(rs));
        } else {
            Notification.errorMessage("Need your finger print");
        }
    }

    /**
     * Remove the user input in the fields.
     *
     *
     */
    private void clearAll() {
        //choiceBoxNationality.setValue(null);
        choiceBoxPermanentDivision.setValue(null);
        choiceBoxPermanentDistrict.setValue(null);
        choiceBoxPermanentPoliceStation.setValue(null);

        choiceBoxPresentDivision.setValue(null);
        choiceBoxPresentDistrict.setValue(null);
        choiceBoxPresentPoliceStation.setValue(null);
//        choiceBoxUpazila.setValue(null);
//        choiceBoxUnion.setValue(null);

        choiceBoxbirthPlace.setValue(null);
        photoImage.setImage(new Image("/com/istl/resource/humenImage.jpg"));
        leftThumb.setImage(new Image("/com/istl/resource/fingerprint.png"));
        leftIndex.setImage(new Image("/com/istl/resource/fingerprint.png"));
        rightThumb.setImage(new Image("/com/istl/resource/fingerprint.png"));
        rightIndex.setImage(new Image("/com/istl/resource/fingerprint.png"));
        irisLeft.setImage(new Image("/com/istl/resource/iris.png"));
        irisRight.setImage(new Image("/com/istl/resource/iris.png"));
//        leftmdl.setImage(new Image("/com/istl/resource/fingerprint.png"));
//        leftrng.setImage(new Image("/com/istl/resource/fingerprint.png"));
//        leftsml.setImage(new Image("/com/istl/resource/fingerprint.png"));
//        rightmdl.setImage(new Image("/com/istl/resource/fingerprint.png"));
//        rightrng.setImage(new Image("/com/istl/resource/fingerprint.png"));
//        rightsml.setImage(new Image("/com/istl/resource/fingerprint.png"));
//        nameBn.clear();
        nameEn.clear();
        phoneNumber.clear();
        nid.clear();
        nameFather.clear();
        nameMother.clear();
        nameSpouse.clear();
        presentAddress.clear();
        dateOfBirth.setValue(null);
        permanentPostCode.clear();
        permanentPostCode.clear();
        presentPostCode.clear();
        permanentAddress.clear();
        categories.setValue(null);
        organization.setValue(null);
        bloodgrp.setValue(getBloodList().get(2));
        choiceBoxNationality.setValue(nList.get(18));
        //male.setSelected(false);
        //female.setSelected(false);
        //others.setSelected(false);
        //married.setSelected(false);
        //unmarried.setSelected(false);
        //
        FieldValidation.removeColor(phoneNumber);
        FieldValidation.removeColor(nid);
        FieldValidation.removeColor(nameEn);
        FieldValidation.removeColor(nameFather);
        FieldValidation.removeColor(nameMother);
        FieldValidation.removeColor(nameSpouse);
        FieldValidation.removeColor(presentPostCode);
        FieldValidation.removeColor(permanentPostCode);
        FieldValidation.removeColor(designation);
        choiceBoxNationality.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
        choiceBoxbirthPlace.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
        choiceBoxPermanentDivision.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
        choiceBoxPermanentDistrict.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
        choiceBoxPermanentPoliceStation.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
        choiceBoxPresentDivision.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
        choiceBoxPresentDistrict.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
        choiceBoxPresentPoliceStation.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
        categories.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
        organization.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
        dateOfBirth.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
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
        if (designation.getText() == null || designation.getText().length() == 0) {
            FieldValidation.setColor(designation);
            errorMessage += "No valid Designation!\n";
        }
        if (remarks.getText().length() == 0) {
            remarks.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            errorMessage += "No valid Remarks Added!\n";
        }
        if (service.nidMatching(nid.getText(), p)) {
            FieldValidation.setColor(nid);
            errorMessage += "Duplicate NID!\n";
        }
        if (service.mobileMatching(phoneNumber.getText(), p)) {
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
