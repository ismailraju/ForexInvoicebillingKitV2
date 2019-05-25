package com.istl.controller;

import com.istl.enroll_kit.model.IdmUser;
import com.istl.service.IdmUserService;
import com.istl.util.Notification;
import java.net.URL;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class NewUserController implements Initializable {
    
    @FXML
    private TextField nameEn;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private DatePicker date;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton others;
    @FXML
    private Button btn;
    
  
    private Stage dialogStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn.setDefaultButton(true);
        btn.setOnAction((event) -> {
            try {
                newuser();
            } catch (InvalidKeySpecException invalidKeySpecException) {
            }
        });
       
    } 
    
    public void newuser() throws InvalidKeySpecException
    {
         if(isInputValid()){
            IdmUser user= new IdmUser();
            IdmUserService service= new IdmUserService();
            user.setFirstName(nameEn.getText());
            user.setLastName("No");
            user.setUserName(userName.getText());
            user.setPassword(service.getSecurepassword(password.getText()));
            user.setEmail(email.getText());
            user.setPhone(phone.getText());
            LocalDate ldate = date.getValue();
            Instant instant = Instant.from(ldate.atStartOfDay(ZoneId.of("GMT")));
            Date date = Date.from(instant);
            user.setDateOfBirth(date);
            
            if(male.isSelected()){
                int value=8;
                user.setGender(value);
            }
            
            if(female.isSelected()){
                int value=9;
                user.setGender(value);
            }
            
            if(others.isSelected()){
                int value=10;
                user.setGender(value);
            }
            user.setOfficeId(1);
            user.setStatus(1);
            System.out.println(user);
            
            
            if(service.result(user)){
                Notification.successfullyMessage("Successfully Created");
                System.out.println("updated");
            }else{
                Notification.errorMessage("Something went wrong");
                System.out.println("notupdated");
            }
            clearAll();
        }
    }
    
    @FXML
    protected void newUserAction(ActionEvent event) throws InvalidKeySpecException {
       newuser();
    }
    
    
    private void clearAll() {
        nameEn.clear();
        userName.clear();
        password.clear();
        email.clear();
        phone.clear();
        date.setValue(null);
        male.setVisible(false);
        female.setVisible(false);
        others.setVisible(false);
    }
    
    
     /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (nameEn.getText().length() == 0) {
            errorMessage += "Invalid English Name!\n";
        }
        if (userName.getText().length() == 0) {
            errorMessage += "Invalid User Name!\n";
        }
        if (password.getText().length() == 0) {
            errorMessage += "Invalid Password!\n";
        }
        if (email.getText().length() == 0) {
            errorMessage += "Invalid Email!\n";
        }
        if (phone.getText().length() == 0) {
            errorMessage += "Invalid Phone!\n";
            
        }if (date.getValue() == null) {
            errorMessage += "Invalid Date of Birth!\n";
            
        }
        if(male.isSelected()){
        }else if(female.isSelected()){
        }else if(others.isSelected()){
        }else{
            errorMessage += "Gender is not Selected!\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Notification.errorMessage(errorMessage);
            return false;
        }
    }
}
