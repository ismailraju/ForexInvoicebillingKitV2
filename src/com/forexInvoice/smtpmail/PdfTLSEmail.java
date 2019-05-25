package com.forexInvoice.smtpmail;

/**
 *
 * @author Raju
 */
import com.forexInvoice.model.Company;
import com.forexInvoice.model.Transaction;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class PdfTLSEmail {

    /**
     * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use
     * authentication) Use Authentication: Yes Port for TLS/STARTTLS: 587
     */
    public void sendPdfTLSEmail(Transaction t, Company c) {
 
        String EMAIL = "automaticemailsender.GK@gmail.com";
        String EMAIL_PS = "j3Et.#lxdd!JlNWpZ##kujar";
        final String fromEmail = EMAIL; //requires valid gmail id
        final String password = EMAIL_PS; // correct password for gmail id

        final String toEmail = "ismailhossainraju19@gmail.com"; // can be any email id
//        final String toEmail = "thilagavathi.mani@letzsol.com"; // can be any email id

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

        String body = "Hello This is testing";

        String subject = "Test subject";
        String fileName = "I-" + String.format("%06d", t.getId()) + ".pdf";

//        EmailUtil.sendEmail(session, toEmail, "TLSEmail Testing Subject", "TLSEmail Testing Body");
        EmailUtil.sendAttachmentEmail(session, toEmail, subject, body, fileName);

//        EmailUtil.sendImageEmail(session, toEmail, "SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");
    }

}
