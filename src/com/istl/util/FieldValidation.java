/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.util;

import com.sun.javafx.scene.control.skin.TextAreaSkin;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

/**
 *
 * @author User
 */
public class FieldValidation {
       
    ///++++++++++++++++++++++++++++++++++++++++++++++++++added by iqbal++++++++++++++++++++++++++++++++++++++++++++++++++
    
    
    
    public static PopOver pp = null;
    private final static String mailstr = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    private final static String passport = "^[a-zA-Z0-9\\-\\ ]+";

    //Label label = new Label();
    public static void keyevent(TextField field, KeyEvent e) {
//        KeyCode a =  e.getCode();
//        System.out.println("com.istl.controller.RegistrationController.keyevent()"+a);
        //String s = (String)e.getCode();
        if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.BACK_SPACE) {
            
        } else {
            field.replaceText(0, field.getText().length(), convert(field.getText()));
        }
    }
    
    public static String convert(String str) {

        // Create a char array of given String 
        char ch[] = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {

            // If first character of a word is found 
            if (i == 0 && ch[i] != ' '
                    || ch[i] != ' ' && ch[i - 1] == ' '
                    || ch[i] != ' ' && ch[i - 1] == '-') {

                // If it is in lower-case 
                if (ch[i] >= 'a' && ch[i] <= 'z') {

                    // Convert into Upper-case 
                    ch[i] = (char) (ch[i] - 'a' + 'A');
                }
            } // If apart from first character 
            // Any one is in Upper-case 
            else if (ch[i] >= 'A' && ch[i] <= 'Z') // Convert into Lower-Case 
            {
                ch[i] = (char) (ch[i] + 'a' - 'A');
            }
        }

        // Convert the char array to equivalent String 
        String st = new String(ch);
        return st;
    }

//
  public static void exit()
  {
      if (pp != null) {
          pp.hide(Duration.ZERO);
          System.err.println("I am here");
      }
            

  }
    public static void checking(TextField field, String msg, boolean flag,String fieldType) {
        if (pp != null) {
            pp.hide();
        }
        String color = "red";
        Label lblName = new Label(" "+fieldType+" ");
       // Label lblStreet = new Label("Hints");
        Label lblCityStateZip = new Label(" "+msg+" ");
        lblName.setTextFill(Paint.valueOf(color));
        //lblStreet.setTextFill(Paint.valueOf(color));
        lblCityStateZip.setTextFill(Paint.valueOf(color));
        VBox vBox = new VBox(lblName, lblCityStateZip);
        
        pp = new PopOver(vBox);
        //pp.setStyle("width:500;Height:500");
        if (flag) {
            pp.hide();
        } else {
            pp.show(field);
            // pp.setAutoHide(false);
        }
        
    }
    
    public static void set(TextField txtfield, String type, String fieldType) {
        String msg = "";
        String msg1 = "Length should not be more than ";
        String regex = "";
        int length = 0;
        switch (type) {
            case "cash":
                msg = "Allowed digits only";
                regex = "[0-9]+";
                length = 8;
                msg1 += "8";
                break;
            case "homephn":
                msg = "Allowed digits only";
                regex = "[0-9]+";
                length = 15;
                msg1 += "15";
                break;
            case "weight":
                msg = "Allowed digits only";
                regex = "[0-9]+";
                length = 3;
                msg1 = "3";
                break;
            case "en":
                msg = "Allowed character \"A-Za-z\"";
                regex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
                length = 48;
                msg1 += "50 ";
                break;
            case "email":
                msg = "Ex: test@istlbd.com";
                regex = mailstr;
                length = 98;
                msg1 += "100 ";
                break;
            case "post":
                msg = "Allowed digits only";
                regex = "[0-9]+";
                length = 4;
                msg1 = "Postal code should be 4 digits";
                break;
            case "nid":
                msg = "Allowed digits only";
                regex = "[0-9]+";
                length = 17;
                msg1 = "NID number should be 10 or 17 digits";
                break;
            case "pass":
                msg = "Allowed specific format only";
                regex = passport;
                length = 10;
                msg1 = "Passport number should be 10 digits";
                break;
            case "birth":
                msg = "Allowed digits only";
                regex = "[0-9]+";
                length = 17;
                msg1 = "Birth Identification number should be 17 digits";
                break;
                
            case "phn":
                msg = "Allowed digits only";
//                regex = "^01[3-9]\\d{8}$";
                regex = "^01[3-9][0-9]+$";
                length = 11;
                msg1 = "Phone Number should be 11 digits";
                break;
            default:
                break;
            
        }
        
        if (!txtfield.getText().matches(regex)) {
            setColor(txtfield);
            checking(txtfield, msg, false,fieldType);
        } else if ((type == "post" || type == "phn"|| type == "pass" || type == "birth") && (txtfield.getText().length() != length)) {
            setColor(txtfield);
            checking(txtfield, msg1, false,fieldType);
        } else if (type == "nid" && (txtfield.getText().length() != length && txtfield.getText().length() != 10)) {
            setColor(txtfield);
            checking(txtfield, msg1, false,fieldType);
        } else if (txtfield.getText().length() > length) {
            setColor(txtfield);
            checking(txtfield, msg1, false,fieldType);
        } else {
            removeColor(txtfield);
            checking(txtfield, "", true,fieldType);
        }
        
    }
    
    public static void setColor(TextField field) {
        // field.setStyle("-fx-control-inner-background: #FF0000");
        field.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
        //field.setStyle("-fx-border-width: 2;");
    }
    
    public static void removeColor(TextField field) {
        // String value0 = "white";
        // field.setStyle("-fx-control-inner-background: #FFFFFF");
        field.setStyle("-fx-border-color: transparent;");
    }
    
     public static void textAreaNavigation(TextArea a) {
        a.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.TAB)) {
                    Node node = (Node) event.getSource();
//                    if (node instanceof TextField) {
//                        TextFieldSkin skin = (TextFieldSkin) ((TextField) node).getSkin();
//                        if (event.isShiftDown()) {
//                            skin.getBehavior().traversePrevious();
//                        } else {
//                            skin.getBehavior().traverseNext();
//                        }
//                    } else
                        if (node instanceof TextArea) {
                        TextAreaSkin skin = (TextAreaSkin) ((TextArea) node).getSkin();
                        if (event.isShiftDown()) {
                            skin.getBehavior().traversePrevious();
                        } else {
                            skin.getBehavior().traverseNext();
                        }
                    }

                    event.consume();
                }
            }
        });
    }

    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++ended by iqbal+++++++++++++++++++++++++++++++++++++++++++++++++++

}
