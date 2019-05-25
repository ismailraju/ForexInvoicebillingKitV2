package com.istl.controller;

import com.istl.enroll_kit.model.EnrollDistrict;
import com.istl.enroll_kit.model.EnrollDivision;
import com.istl.enroll_kit.model.EnrollLookup;
import com.istl.enroll_kit.model.EnrollNationalityLookup;
import com.istl.enroll_kit.model.EnrollPerson;
import com.istl.enroll_kit.model.EnrollStation;

import com.istl.service.EnrollStationService;
import com.istl.service.PersonService;
import com.istl.util.PersonTableUtil;
import com.istl.util.SelectKeyComboBoxListener;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.util.Callback;

public class SearchController implements Initializable {

    @FXML
    private TableView table;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField nameEn;
    @FXML
    private TextField nameFather;
    @FXML
    private TextField nameMother;
    @FXML
    private TextField nameSpouse;
    @FXML
    private TextField nID;
    @FXML
    private TextField mobileNumber;
    @FXML
    private ComboBox<EnrollDivision> choiceBoxDivision;
    @FXML
    private ComboBox<EnrollDistrict> choiceBoxDistrict;
    @FXML
    private ComboBox<EnrollStation> choiceBoxPolicStation;
    @FXML
    private ComboBox<EnrollNationalityLookup> choiceBoxNationality;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton others;

    @FXML
    private ComboBox<Integer> top;
    @FXML
    private TitledPane titledPane;

    PersonService service = new PersonService();
    EnrollStationService stationService = new EnrollStationService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            tableOparetion();

            choiceBoxNationality.setItems(service.getAllNationality());
            choiceBoxNationality.setValue(null);
            choiceBoxDivision.setItems(service.getAllDivision());
            choiceBoxDivision.setValue(null);
            choiceBoxDistrict.setValue(null);
            choiceBoxDivision.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollDivision>() {

                @Override
                public void changed(ObservableValue<? extends EnrollDivision> observable, EnrollDivision oldValue, EnrollDivision newValue) {
                    choiceBoxDistrict.setItems(service.getDistrictByID(newValue));
                }
            });
            choiceBoxPolicStation.setValue(null);
            choiceBoxDistrict.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollDistrict>() {
                @Override
                public void changed(ObservableValue<? extends EnrollDistrict> observable, EnrollDistrict oldValue, EnrollDistrict newValue) {
                    choiceBoxPolicStation.setItems(stationService.getStationById(newValue));
                }
            });

            ObservableList<Integer> list = FXCollections.observableArrayList(2, 10, 20, 25, 50, 100);
            top.setValue(10);
            top.setItems(list);
            top.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    txtSearch.clear();
                    tableOparetion();
                    table.setItems(service.findTopNPerson(newValue));
                }
            });

            table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
         new SelectKeyComboBoxListener(choiceBoxNationality);
        new SelectKeyComboBoxListener(choiceBoxDivision);
        new SelectKeyComboBoxListener(choiceBoxDistrict);
        new SelectKeyComboBoxListener(choiceBoxPolicStation);
        
    }

    public void setTableValue() throws ParseException {
        table.setItems(service.getAllParson());
    }

    @FXML
    protected void refreshAction(ActionEvent event) throws IOException {
        try {
            tableOparetion();
            setTableValue();
            txtSearch.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void searchAction(ActionEvent event) throws IOException {
        String searchName = txtSearch.getText();

        try {
            tableOparetion();
            if (searchName.length() != 0) {
                table.setItems(service.searchByName(searchName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void advancedSearch(ActionEvent event) throws IOException {

        boolean bmale = male.isSelected();
        boolean bfemal = female.isSelected();
        boolean bothers = others.isSelected();
        short m = 8;
        short f = 9;
        short o = 10;
        EnrollPerson person = new EnrollPerson();
        String name = nameEn.getText();
        String father = nameFather.getText();
        String mother = nameMother.getText();
        String spouse = nameSpouse.getText();
        String nid = nID.getText();
        if (bmale == true) {
            EnrollLookup gender = new EnrollLookup();
            gender.setId(m);
            person.setGender(gender);
        }
        if (bfemal == true) {
            EnrollLookup gender = new EnrollLookup();
            gender.setId(f);
            person.setGender(gender);
        }
        if (bothers == true) {
            EnrollLookup gender = new EnrollLookup();
            gender.setId(o);
            person.setGender(gender);
        }
        person.setNationalityId(choiceBoxNationality.getValue());
        person.setPermanentDivisionId(choiceBoxDivision.getValue());
        person.setPermanentDistrictId(choiceBoxDistrict.getValue());
        person.setPermanentStationId(choiceBoxPolicStation.getValue());
        person.setFirstNameEn(name);
        person.setFatherName(father);
        person.setMotherName(mother);
        person.setSpouseName(spouse);
        person.setNationalId(nid);
        person.setMobileNumber(mobileNumber.getText());

        try {
            tableOparetion();
            if (person != null) {
                table.setItems(service.personAdvancedSearch(person));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        titledPane.setExpanded(false);
    }

    @FXML
    protected void resetAction(ActionEvent event) throws IOException, ParseException {
        reSet();
        tableOparetion();
        setTableValue();
    }

    public void tableOparetion() {

        ObservableList<TableColumn> tcList = table.getColumns();
        for (TableColumn tc : tcList) {
            switch (tc.getId()) {
                case "NameEn":
                    PersonTableUtil.decorateFirstNameColumn(tc);
                    break;
//                case "NameBn":
//                    PersonTableUtil.decorateLastNameColumn(tc);
//                    break;
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
                case "status":
                    PersonTableUtil.decorateStatusColumn(tc);
                    break;
                case "tblAction":
                    PersonTableUtil.decorateActionColumn(tc);
                    break;
                default:
                    break;
            }
        }
    }

    public void reSet() {
        txtSearch.clear();
        nameEn.clear();
        nameFather.clear();
        nameMother.clear();
        nameSpouse.clear();
        nID.clear();
        mobileNumber.clear();
        choiceBoxDivision.setValue(null);
        choiceBoxDistrict.setValue(null);
        choiceBoxPolicStation.setValue(null);
        choiceBoxNationality.setValue(null);
//        male.setVisible(false);
//        female.setVisible(false);
//        others.setVisible(false);
    }

    public static class ColumnFormatter<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

        public final DateTimeFormatter format;

        public ColumnFormatter(DateTimeFormatter format) {
            super();
            this.format = format;
        }

        @Override
        public TableCell<S, T> call(TableColumn<S, T> arg0) {
            return new TableCell<S, T>() {
                @Override
                public void updateItem(T item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        Date input = (Date) item;
                        LocalDate ld = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        String val = ld.format(format);
                        setGraphic(new Label(val));
                    }
                }
            };
        }
    }

}
