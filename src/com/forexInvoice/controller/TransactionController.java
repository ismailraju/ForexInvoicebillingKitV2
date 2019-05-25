package com.forexInvoice.controller;

import com.istl.controller.*;
import com.canon.controller.CanonViewController;
import com.forexInvoice.model.Bank;
import com.forexInvoice.model.Company;
import com.forexInvoice.model.Country;
import com.forexInvoice.model.Currency;
import com.forexInvoice.model.Customer;
import com.forexInvoice.model.Recipient;
import com.forexInvoice.model.Transaction;
import com.forexInvoice.pdf.GenaratePdf;
import com.forexInvoice.service.BankService;
import com.forexInvoice.service.BankServiceImp;
import com.forexInvoice.service.CompanyServiceImp;
import com.forexInvoice.service.CountryServiceImp;
import com.forexInvoice.service.CurrencyServiceImp;
import com.forexInvoice.service.CustomerServiceImp;
import com.forexInvoice.service.RecipientServiceImp;
import com.forexInvoice.service.TransactionServiceImp;
import com.google.gson.Gson;
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
import java.math.BigDecimal;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.util.StringConverter;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfRect;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.objdetect.CascadeClassifier;

public class TransactionController implements Initializable {
    
    @FXML
    public ComboBox<Bank> rbank;
    @FXML
    private ComboBox<Country> tcountry;
    @FXML
    private ComboBox<Currency> tsendCurrency;
    @FXML
    private ComboBox<Currency> treceiveCurrency;
    
    @FXML
    private TextField cfulName;
    @FXML
    private DatePicker cdob;
    @FXML
    private TextField ctelephone;
    @FXML
    private TextField caddress;
    @FXML
    private TextField cidNumber;
    @FXML
    private DatePicker cidExpiryDate;
    @FXML
    private TextField cissuePlace;
    @FXML
    private TextField cpurpose;
    @FXML
    private TextField coccupation;
    
    @FXML
    private TextField rfulName;
    @FXML
    private DatePicker rdob;
    @FXML
    private TextField rtelephone;
    @FXML
    private TextField raddress;
    @FXML
    private TextField ridNumber;
    
    @FXML
    private TextField rissuePlace;
    @FXML
    private TextField rpurpose;
    @FXML
    private TextField rreceivedMethod;
    
    @FXML
    private TextField ttransactionId;
    @FXML
    private TextField tamountSend;
    @FXML
    private TextField tcommission;
    @FXML
    private TextField ttotal;
    @FXML
    private TextField texchangeRate;
    @FXML
    private TextField tamountReceive;
    @FXML
    private TextField tpaymentMethod;
    
    @FXML
    private Button saveButton;
    public BankServiceImp bankService = new BankServiceImp();
    public CountryServiceImp countryService = new CountryServiceImp();
    public CurrencyServiceImp currencyService = new CurrencyServiceImp();
    public CustomerServiceImp customerService = new CustomerServiceImp();
    public RecipientServiceImp recipientService = new RecipientServiceImp();
    public TransactionServiceImp transactionService = new TransactionServiceImp();
    public CompanyServiceImp companyService = new CompanyServiceImp();
    
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> cfulName.requestFocus());
        
        List<Bank> banks = bankService.getBanks();
        List<Country> countrys = countryService.getCountrys();
        List<Currency> currencys = currencyService.getCurrencys();

//        ObservableList<Bank> banksO = FXCollections.observableArrayList();
//        for (Bank b : banks) {
//            banksO.add(b);
//        }
        try {
            
            this.rbank.setItems(FXCollections.observableArrayList(banks));
            this.tcountry.setItems(FXCollections.observableArrayList(countrys));
            this.tsendCurrency.setItems(FXCollections.observableArrayList(currencys));
            this.treceiveCurrency.setItems(FXCollections.observableArrayList(currencys));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveButton.setDefaultButton(true);
        
    }
    
    @FXML
    protected void resetAction(ActionEvent event) {
        clearAll();
    }
    
    @FXML
    protected void submitAction(ActionEvent event) throws IOException {
        
        if (isInputValid()) {
            try {
                Customer c = new Customer();
                Recipient r = new Recipient();
                Transaction t = new Transaction();
                
                c.setFulName(cfulName.getText());
                if (cdob.getValue() != null) {
                    LocalDate ldate = cdob.getValue();
                    Instant instant = Instant.from(ldate.atStartOfDay(ZoneId.systemDefault()));
                    Date date = Date.from(instant);
                    System.out.println(date);
                    c.setDob(date);
                    
                }
                
                c.setTelephone(ctelephone.getText());
                c.setAddress(caddress.getText());
                c.setIdNumber(cidNumber.getText());
                
                if (cidExpiryDate.getValue() != null) {
                    LocalDate ldate = cidExpiryDate.getValue();
                    Instant instant = Instant.from(ldate.atStartOfDay(ZoneId.systemDefault()));
                    Date date = Date.from(instant);
                    System.out.println(date);
                    c.setIdExpiryDate(date);
                    
                }
                c.setIssuePlace(cissuePlace.getText());
                c.setPurpose(cpurpose.getText());
                c.setOccupation(coccupation.getText());
/////////////////////////////////////////
                r.setFulName(rfulName.getText());
                if (rdob.getValue() != null) {
                    LocalDate ldate = rdob.getValue();
                    Instant instant = Instant.from(ldate.atStartOfDay(ZoneId.systemDefault()));
                    Date date = Date.from(instant);
                    System.out.println(date);
                    r.setDob(date);
                    
                }
                
                r.setTelephone(rtelephone.getText());
                r.setAddress(raddress.getText());
                r.setIdNumber(ridNumber.getText());
                
                r.setReceivedMethod(rreceivedMethod.getText());
                r.setBank(rbank.getValue());
/////////////////////////////////      

                t.setSendCurrency(tsendCurrency.getValue());
                if ((tamountSend.getText() != null) && (tamountSend.getText() != "")) {
                    
                    BigDecimal bigDecimal = new BigDecimal(tamountSend.getText());
                    t.setAmountSend(bigDecimal);
                }
                if ((tcommission.getText() != null) && (tcommission.getText() != "")) {
                    
                    BigDecimal bigDecimal = new BigDecimal(tcommission.getText());
                    t.setCommission(bigDecimal);
                }
                
                if ((ttotal.getText() != null) && (ttotal.getText() != "")) {
                    
                    BigDecimal bigDecimal = new BigDecimal(ttotal.getText());
                    t.setTotal(bigDecimal);
                }
                
                t.setCountry(tcountry.getValue());
                t.setReceiveCurrency(treceiveCurrency.getValue());
                
                if ((texchangeRate.getText() != null) && (texchangeRate.getText() != "")) {
                    
                    BigDecimal bigDecimal = new BigDecimal(texchangeRate.getText());
                    t.setExchangeRate(bigDecimal);
                }
                
                if ((tamountReceive.getText() != null) && (tamountReceive.getText() != "")) {
                    
                    BigDecimal bigDecimal = new BigDecimal(tamountReceive.getText());
                    t.setAmountReceive(bigDecimal);
                }
                
                t.setPaymentMethod(tpaymentMethod.getText());
                
                Customer addCustomer = customerService.addCustomer(c);
                Recipient addRecipient = recipientService.addRecipient(r);
                
                t.setCustomer(addCustomer);
                t.setRecipient(addRecipient);
                transactionService.addTransaction(t);
                
                Company company = companyService.getCompany(1);
                GenaratePdf genaratePdf=new GenaratePdf();
                genaratePdf.genaratePdfByte(t, company);
/////////////////////////////////////////////////
//                if (result) {
//                    Notification.successfullyMessage("Registration Successful");
//                    clearAll();
//                } else {
//                    Notification.errorMessage("Fail to create.Please check all fields");
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }

    /**
     * Remove the user input in the fields.
     *
     *
     */
    private void clearAll() {
        rbank.setValue(null);
        
        cfulName.clear();
        
        cdob.setValue(null);

//        FieldValidation.removeColor(phoneNumber);
//        choiceBoxNationality.setStyle("-fx-border-color:  #016ebc;-fx-border-width: 2;-fx-border-radius: 3");
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

//        if (nameEn.getText().length() > 49) {
//            errorMessage += "English Name Length is too high!\n";
//            FieldValidation.setColor(nameEn);
//        }
//        if (nameEn.getText() == null || nameEn.getText().length() == 0 || !nameEn.getText().matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
//
//            FieldValidation.setColor(nameEn);
//            errorMessage += "No valid English Name !\n";
//
//        }
//         
//        if (dateOfBirth.getValue() == null) {
//            dateOfBirth.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
//
//            errorMessage += "No valid Date of Birth!\n";
//        }
//
//        if (choiceBoxNationality.getValue() == null) {
//            choiceBoxNationality.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
//
//            errorMessage += "No valid Nationality!\n";
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
