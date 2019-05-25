package com.forexInvoice.pdf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.forexInvoice.model.Company;
import com.forexInvoice.model.Customer;
import com.forexInvoice.model.Recipient;
import com.forexInvoice.model.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author ismail
 */
public class GenaratePdf {

//    private static String[] columnNames = {"field2", "field1"};
//    private static String[][] data = {
//        {"111", "G Conger",},
//        {"222", "A Date"},
//        {"333", "R Linz"},
//        {"444", "V Sethi"},
//        {"555", "K Rao"},
//        {"666", "V Santana"},
//        {"777", "J Pollock"},
//        {"888", "H David"},
//        {"999", "P Patel"},
//        {"101", "C Comer"}
//
//    };
    public GenaratePdf() {
    }

    public byte[] genaratePdfByte(Transaction t, Company c) {
        try {
//            String currentPath = System.getProperty("user.dir") + "/resource/";
//            String currentPath = System.getProperty("user.dir") + "/src/main/java/com/raju/pdf/";
            String currentPath = System.getProperty("user.dir") + "/";
//        JasperReport jasperReport = JasperCompileManager.compileReport("/logo/ForexInvoiceBilling.fxml");
//            JasperReport jasperReport = JasperCompileManager.compileReport(currentPath + "DijnetHUReportEn.jrxml");
//            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(currentPath + "DijnetHUReportEn.jasper");
//            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/ForexInvoiceBilling.jasper"));
//            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResource("/logo/ForexInvoiceBilling.jasper"));
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/logo/ForexInvoiceBilling.jasper"));

//            JRDataSource jRDataSource = new JREmptyDataSource();
//            JRDataSource jRDataSource = new JRTableModelDataSource(new DefaultTableModel(data, columnNames));
//            for (SiofokInvoiceLine il : ils) {
//                list.add(new People(il.getInvoiceLineName(), il.getInvoiceLineGrossAmount() + " Ft"));
//            }
//            list.add(new People("rrr", "fsdfsd"));
//            list.add(new People("rrr", "fsdfsd"));
//            list.add(new People("rrr", "fsdfsd"));
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(null);
            Map<String, Object> parameter = new HashMap<String, Object>();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");

            parameter.put("HeaderText1", c.getAddress());
            parameter.put("HeaderText2", c.getTelephone() + " | " + c.getFax());
            parameter.put("HeaderText3", c.getWebAddress());
            parameter.put("HeaderText4", "I-" + String.format("%06d", t.getId()));
            parameter.put("HeaderText5", t.getSendCurrency().getShortName() + " " + t.getAmountSend());
            parameter.put("HeaderText6", t.getSendCurrency().getShortName() + " " + t.getCommission());
            parameter.put("HeaderText7", t.getSendCurrency().getShortName() + " " + t.getTotal());
            parameter.put("HeaderText8", t.getCountry().getName());
            parameter.put("HeaderText9", t.getReceiveCurrency().getShortName() + " " + t.getExchangeRate());
            parameter.put("HeaderText10", t.getReceiveCurrency().getShortName() + " " + t.getAmountReceive());
            parameter.put("HeaderText11", t.getPaymentMethod());
            Customer cu = t.getCustomer();
            parameter.put("HeaderText12", cu.getId());
            parameter.put("HeaderText13", cu.getFulName());
            parameter.put("HeaderText14", format2.format(cu.getDob()));
            parameter.put("HeaderText15", cu.getTelephone());
            parameter.put("HeaderText16", cu.getAddress());
            parameter.put("HeaderText17", cu.getIdNumber());
            parameter.put("HeaderText18", format2.format(cu.getIdExpiryDate()));
            parameter.put("HeaderText19", cu.getIssuePlace());
            parameter.put("HeaderText20", cu.getPurpose());
            parameter.put("HeaderText21", cu.getOccupation());

            Recipient r = t.getRecipient();

            parameter.put("HeaderText23", r.getId());
            parameter.put("HeaderText24", r.getFulName());
            parameter.put("HeaderText25", format2.format(r.getDob()));
            parameter.put("HeaderText26", r.getTelephone());
            parameter.put("HeaderText27", r.getAddress());
            parameter.put("HeaderText28", r.getIdNumber());
            parameter.put("HeaderText29", r.getReceivedMethod());
            parameter.put("HeaderText30", r.getBank().getName());
            parameter.put("HeaderText31", r.getBank().getBranchName());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, beanColDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, currentPath + "I-" + String.format("%06d", t.getId()) + ".pdf");
            byte[] pdfByte = JasperExportManager.exportReportToPdf(jasperPrint);

            return pdfByte;
        } catch (Exception jRException) {

            jRException.printStackTrace();
        }

        return null;
    }

//    public static void main(String[] args) {
//        ppp p = new ppp();
//        try {
//            p.r();
//        } catch (JRException ex) {
//            Logger.getLogger(GenaratePdf.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
