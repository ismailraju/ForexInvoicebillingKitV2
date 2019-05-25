package com.istl.controller;

import com.istl.enroll_kit.model.EnrollPerson;
import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istl.service.PersonService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ShowController implements Initializable {

    @FXML
    private Label nameEn;
    @FXML
    private Label maritStatus;
    @FXML
    private Label gender;
    @FXML
    private Label nameFather;
    @FXML
    private Label nameMother;
    @FXML
    private Label nID;
    @FXML
    private Label dateOfBirth;
    @FXML
    private Label nationality;

    @FXML
    private Label birthPlace;
    @FXML
    private Label SpouseName;
    @FXML
    private Label phone;
    @FXML
    private ImageView photoImage;
    @FXML
    private ImageView rightThumb;
    @FXML
    private ImageView rightIndex;

    @FXML
    private ImageView leftThumb;
    @FXML
    private ImageView leftIndex;

    @FXML
    private ImageView rightIris;
    @FXML
    private ImageView leftIris;
    //
    @FXML
    private Label designation;
    @FXML
    private Label organization;
    @FXML
    private Label categories;
    @FXML
    private Label bloodGrp;
    @FXML
    private Label remarks;

    //
    //Address
    @FXML
    private Label presentDivision;
    @FXML
    private Label presentDistrict;
    @FXML
    private Label presentStation;
    @FXML
    private Label presentPostCode;
    @FXML
    private Label presentAddress;

    @FXML
    private Label permanentDivision;
    @FXML
    private Label permanentDistrict;
    @FXML
    private Label permanentStation;
    @FXML
    private Label permanentPostCode;
    @FXML
    private Label permanentAddress;

    /**
     * This method accepts a person to initialize the view
     *
     * @param person
     *
     */
    PersonService service = new PersonService();

    public void initData(EnrollPerson person, EnrollPersonBiometric biometric) throws IOException {
        if (person.getId() != null) {
            nameEn.setText(person.getFirstNameEn());
//            nameBn.setText(person.getFirstNameLocal());
            phone.setText(person.getMobileNumber());
//            System.out.println("init Gender : "+person.getGender());
//            System.out.println("init Gender Name : "+person.getGender().getNameEn());
            if (person.getGender() != null) {
                gender.setText(person.getGender().getNameEn());
            }

            nameFather.setText(person.getFatherName());
            nameMother.setText(person.getMotherName());
            nID.setText(person.getNationalId());
            if (person.getDateOfBirth() != null) {
                SimpleDateFormat dtf = new SimpleDateFormat("dd-MM-yyyy");
                String date = dtf.format(person.getDateOfBirth());
                dateOfBirth.setText(date);
            }
            //gender.setText(person.getGender().getNameEn());
            if (person.getNationalityId() != null) {
                nationality.setText(person.getNationalityId().getCountryNameEn());
            }

            //
            if (person.getPermanentDivisionId() != null) {
                permanentDivision.setText(person.getPermanentDivisionId().getNameEn());
            }

            if (person.getPermanentDistrictId() != null) {
                permanentDistrict.setText(person.getPermanentDistrictId().getNameEn());
            }
            if (person.getPermanentStationId() != null) {
                permanentStation.setText(person.getPermanentStationId().getNameEn());
            }

            if (person.getPermanentPostCode() != null) {
                permanentPostCode.setText(Short.toString(person.getPermanentPostCode()));
            }
            permanentAddress.setText(person.getPermanentAddress());

            if (person.getPresentDivisionId() != null) {
                presentDivision.setText(person.getPresentDivisionId().getNameEn());
            }

            if (person.getPresentDistrictId() != null) {
                presentDistrict.setText(person.getPresentDistrictId().getNameEn());
            }
            if (person.getPresentStationId() != null) {
                presentStation.setText(person.getPresentStationId().getNameEn());
            }

            if (person.getPresentPostCode() != null) {
                presentPostCode.setText(Short.toString(person.getPresentPostCode()));
            }
            presentAddress.setText(person.getPresentAddress());

            if (person.getPlaceOfBirth() != null) {
                birthPlace.setText(person.getPlaceOfBirth().getNameEn());
            }

            SpouseName.setText(person.getSpouseName());
            if (person.getMaritalStatus() != null) {
                maritStatus.setText(service.getLookUP(person.getMaritalStatus()).getNameEn());
            }

            ///
            bloodGrp.setText(person.getBloodGroup());

            if (person.getCategoriesId() != null) {
                categories.setText(person.getCategoriesId().getName());
            }
            if (person.getOrganizationId() != null) {
                organization.setText(person.getOrganizationId().getName());
            }

            designation.setText(person.getDesignation());

            remarks.setText(person.getRemarks());
            //

        }
        if (biometric.getId() != null) {
            if (biometric.getPhoto() != null) {
                ByteArrayInputStream is = new ByteArrayInputStream(biometric.getPhoto());
                photoImage.setImage(new Image(is));
            }
            if (biometric.getWsqRt() != null) {
                rightThumb.setImage(new Image(new ByteArrayInputStream(biometric.getWsqRt())));
            }
            if (biometric.getWsqRi() != null) {
                rightIndex.setImage(new Image(new ByteArrayInputStream(biometric.getWsqRi())));
            }
//            if (biometric.getWsqRm() != null) {
//                rightMiddle.setImage(new Image(new ByteArrayInputStream(biometric.getWsqRm())));
//            }
//            if (biometric.getWsqRr() != null) {
//                rightRing.setImage(new Image(new ByteArrayInputStream(biometric.getWsqRr())));
//            }
//            if (biometric.getWsqRs() != null) {
//                rightLittle.setImage(new Image(new ByteArrayInputStream(biometric.getWsqRs())));
//            }
            if (biometric.getWsqLt() != null) {
                leftThumb.setImage(new Image(new ByteArrayInputStream(biometric.getWsqLt())));
            }
            if (biometric.getWsqLi() != null) {
                leftIndex.setImage(new Image(new ByteArrayInputStream(biometric.getWsqLi())));
            }
//            if (biometric.getWsqLm() != null) {
//                leftMiddle.setImage(new Image(new ByteArrayInputStream(biometric.getWsqLm())));
//            }
//            if (biometric.getWsqLr() != null) {
//                leftRing.setImage(new Image(new ByteArrayInputStream(biometric.getWsqLr())));
//            }
//            if (biometric.getWsqLs() != null) {
//                leftLittle.setImage(new Image(new ByteArrayInputStream(biometric.getWsqLs())));
//            }

            if (biometric.getIrisRight() != null) {
                rightIris.setImage(new Image(new ByteArrayInputStream(biometric.getIrisRight())));
            }
            if (biometric.getIrisLeft() != null) {
                leftIris.setImage(new Image(new ByteArrayInputStream(biometric.getIrisLeft())));
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
