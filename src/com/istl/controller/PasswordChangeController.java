/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.controller;

import com.istl.enroll_kit.model.IdmUser;
import com.istl.service.IdmUserService;
import com.istl.util.Notification;
import com.istl.util.User;
import java.net.URL;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Arun
 */
public class PasswordChangeController implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    protected void saveAction(ActionEvent event) throws InvalidKeySpecException {
        IdmUser user=User.getUser();
        IdmUserService service = new IdmUserService();
        if(newPassword.getText().equals(confirmPassword.getText())){
            if(user.getUserName().equals(userName.getText())){
                String passwordNew=service.getSecurepassword(newPassword.getText());
                user.setPassword(passwordNew);
                if(service.updateUser(user)){
                    Notification.successfullyMessage("your password is updated");
                    clearAll();
                }else{
                    Notification.errorMessage("Database Problem");
                }
            }else{
                Notification.errorMessage("User Name is Wrong");
            }
        }else{
            Notification.errorMessage("Password is not Same");
        }
        
    }
    
    private void clearAll() {
        userName.clear();
        newPassword.clear();
        confirmPassword.clear();
    }
}
