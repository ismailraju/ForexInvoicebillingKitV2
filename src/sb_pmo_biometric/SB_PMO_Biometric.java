package sb_pmo_biometric;

import com.forexInvoice.model.Bank;
import com.forexInvoice.model.Company;
import com.forexInvoice.model.Transaction;
import com.forexInvoice.pdf.GenaratePdf;
import com.forexInvoice.service.BankServiceImp;
import com.forexInvoice.service.CompanyServiceImp;
import com.forexInvoice.service.TransactionServiceImp;
import com.forexInvoice.smtpmail.PdfTLSEmail;
import com.istl.util.FieldValidation;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.opencv.core.Core;

public class SB_PMO_Biometric extends Application {
    
    private Stage primaryStage = new Stage();
    
    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/com/istl/view/Login.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/com/istl/view/TransactionCreate.fxml"));
        Scene scene = new Scene(root);
        // scene.getStylesheets().add(SB_PMO_Biometric.class.getResource("/com/istl/style/login.css").toExternalForm());
        primaryStage = stage;
        stage.setResizable(false);
        stage.setTitle("Transaction Form");
        stage.getIcons().add(new Image("/logo/logo.png"));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(confirmCloseEventHandler);
        
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
        } else {
            FieldValidation.exit();
            System.out.println("unodc.UNODC.methodName()");
        }
    };
    
    public static void main(String[] args) {

//        BankServiceImp bankService = new BankServiceImp();
//        Bank bank = bankService.getBank(1);
//        System.out.println("bank:" + bank.getBranchName());
//
//        List<Bank> banks = bankService.getBanks();
//        System.out.println("banks" + banks);
        TransactionServiceImp transactionService = new TransactionServiceImp();
        Transaction t = transactionService.getTransaction(5);
        CompanyServiceImp companyService = new CompanyServiceImp();
        Company company = companyService.getCompany(1);
        GenaratePdf genaratePdf = new GenaratePdf();
        genaratePdf.genaratePdfByte(t, company);
        
        PdfTLSEmail email = new PdfTLSEmail();
        email.sendPdfTLSEmail(t, company);
        
        launch(args);
    }
    
}
