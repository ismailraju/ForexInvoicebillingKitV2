package com.forexInvoice.smtpmail;

/**
 *
 * @author Raju
 */
import com.forexInvoice.model.Company;
import com.forexInvoice.model.Transaction;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class PdfTLSEmail {

    /**
     * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use
     * authentication) Use Authentication: Yes Port for TLS/STARTTLS: 587
     */
    public void sendPdfTLSEmail(Transaction t, Company c, String email) throws MessagingException, UnsupportedEncodingException {

        String EMAIL = "Invoiceletzsol@gmail.com";

        String EMAIL_PS = "P@ssword123$";
        final String fromEmail = EMAIL; //requires valid gmail id
        final String password = EMAIL_PS; // correct password for gmail id

//        final String toEmail = "ismailhossainraju19@gmail.com"; // can be any email id
//        final String toEmail = "thilagavathi.mani@letzsol.com"; // can be any email id
        final String toEmail = email; // can be any email id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        String body = "Customer Name: " + c.getName() + " " + " Send Amount: " + t.getAmountSend() + t.getSendCurrency().getShortName() + " Date: " + new Date() + " Transfer Method:  " + t.getPaymentMethod() + " Bank: " + t.getRecipient().getBank().getName() + " Branch: " + t.getRecipient().getBank().getBranchName() + " .";

        String subject = "dacco many transfer receipt";
        String currentPath = System.getProperty("user.dir") + "/";
        String fileName = currentPath + "I-" + String.format("%06d", t.getId()) + ".pdf";

//        EmailUtil.sendEmail(session, toEmail, "TLSEmail Testing Subject", "TLSEmail Testing Body");
        EmailUtil.sendAttachmentEmail(session, toEmail, subject, body, fileName);

//        EmailUtil.sendImageEmail(session, toEmail, "SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");
    }

}
