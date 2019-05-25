/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.pdf;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author User
 */
public class ppp {

    public void r() throws JRException {
        String currentPath = System.getProperty("user.dir") + "/";
        System.out.println("currentPath:" + currentPath);
//        JasperReport jasperReport = JasperCompileManager.compileReport("/logo/ForexInvoiceBilling.fxml");
//          JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResource("ForexInvoiceBilling.jasper"));
//        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResource("ForexInvoiceBilling.jasper"));
//        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResource("logo/ForexInvoiceBilling.jasper"));
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(  getClass().getResource("/logo/ForexInvoiceBilling.jasper") );
    }
}
