package com.istl.controller;


import com.istl.enroll_kit.model.IdmUser;
import com.istl.service.IdmUserService;
import com.istl.util.FieldValidation;
import com.istl.util.User;
import com.istl.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController implements Initializable{
    
    @FXML 
    private TextField userName;
    @FXML 
    private PasswordField password;
    @FXML 
    private javafx.scene.control.Button loginButton;
    
    private Stage primaryStage =new Stage();
    Stage dialogStage;
    private static BorderPane rootLayout =new BorderPane();
    
    public static BorderPane getRoot() {
    return rootLayout;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
    
        //loginButton.defaultButtonProperty().bind(loginButton.focusedProperty());
        Platform.runLater(()->userName.requestFocus());
        loginButton.setDefaultButton(true);
        loginButton.setOnAction((event) -> { 
            
            try {
                login();
            } catch (InvalidKeySpecException invalidKeySpecException) {
                
            }
        
        });
    }
    

    public void login() throws InvalidKeySpecException
    {
       String uname=this.userName.getText();

//    @FXML
//    protected void loginAction(ActionEvent event) throws IOException, InvalidKeySpecException{
////        String uname="arun";//this.userName.getText();
//        String uname=this.userName.getText();
//>>>>>>> 778c152a4aacbf84fdafe41cceac5085fbfd31e2
       // String password="arun";//this.password.getText();
        String password=this.password.getText();
      //if(true){
        if(isInputValid()){
             IdmUserService service= new IdmUserService();
             IdmUser user=service.getVerification(uname, password);
             if(user!=null){
                 User.setUser(user);
                 initRootLayout();
                 showHome();
             }else{
                 Alert alert = new Alert(AlertType.ERROR);
                 alert.initOwner(dialogStage);
                 alert.setTitle("Invalid Fields");
                 alert.setHeaderText("Please correct invalid fields");
                 alert.setContentText("Wrong Password");
                 alert.showAndWait();
             }
        } 
    }
    
    @FXML
    protected void loginAction(ActionEvent event) throws IOException, InvalidKeySpecException{
       // String uname="arun";//this.userName.getText();
        
       
        login();
       
        
        
//        uname="admin";
//        password="admin";
//        
//        if(uname.equals("admin") && password.equals("admin")){
//            initRootLayout();
//            showHome();
//        }
    }
    
      /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginController.class.getResource("/com/istl/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("EnrollmentKit");
            primaryStage.getIcons().add(new Image("/logo/logo.png"));
            primaryStage.setScene(scene);
            primaryStage.show();
            Stage login=(Stage)loginButton.getScene().getWindow();
            login.close();
            primaryStage.setOnCloseRequest(confirmCloseEventHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to exit?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Exit");
        closeConfirmation.setHeaderText("Confirm Exit");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(this.primaryStage);

        // normally, you would just use the default alert positioning,
        // but for this simple sample the main stage is small,
        // so explicitly position the alert so that the main window can still be seen.
//        closeConfirmation.setX(primaryStage.getX());
//        closeConfirmation.setY(primaryStage.getY() + primaryStage.getHeight());

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
        else
        {
            Utils.timerFlag = true;
            FieldValidation.exit();
            System.out.println("unodc.UNODC.methodName()");
        }
    };

    /**
     * Shows the person overview inside the root layout.
     */
    public void showHome() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginController.class.getResource("/com/istl/view/Home.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private boolean isInputValid() {
        String errorMessage = "";
        
        if (userName.getText() == null || userName.getText().length() == 0) {
            errorMessage += "No valid User Name!\n";
        }
        
        if (password.getText() == null || password.getText().length() == 0) {
            errorMessage += "No valid Password !\n"; 
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}
