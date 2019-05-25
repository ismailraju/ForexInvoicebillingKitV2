package com.istl.controller;

import com.istl.service.PersonService;
import com.istl.util.PersonTableUtil;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class RegisteredController implements Initializable {
    
    @FXML
    private TableView table;
     PersonService service = new PersonService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableOparetion();
        
    }
    
    public void setTableValue() throws ParseException{
        table.setItems(service.getAllParson());
    }
    
public void tableOparetion(){
        
        ObservableList<TableColumn> tcList = table.getColumns();
        for(TableColumn tc:tcList){
            switch(tc.getId()){
                case "tnameEn":
                    PersonTableUtil.decorateFirstNameColumn(tc);
                    break;
//                case "tnameBn":
//                    PersonTableUtil.decorateLastNameColumn(tc);
//                    break;
                case "tnationality":
                    PersonTableUtil.decorateNationalityColumn(tc);
                    break;
                case "tdateofBirth":
                    PersonTableUtil.decorateDateOfBirthColumn(tc);
                    break;
                case "tnid":
                    PersonTableUtil.decorateNationalIDColumn(tc);
                    break;
                case "tmobileNumber":
                    PersonTableUtil.decorateMobileNumberColumn(tc);
                    break;
                case "tfatherName":
                    PersonTableUtil.decorateFatherColumn(tc);
                    break;
                case "tmotherName":
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
    
}
