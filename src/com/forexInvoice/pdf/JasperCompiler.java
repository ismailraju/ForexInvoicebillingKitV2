//package com.forexInvoice.pdf;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.raju.pdf;
//
///**
// *
// * @author ismail
// */
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.filefilter.RegexFileFilter;
//import org.apache.commons.io.filefilter.TrueFileFilter;
//
///*
// * @author Dagvadorj Galbadrakh <dagvadorj@gmail.com>
// */
//public class JasperCompiler {
//
//    public static void main(String[] args) throws JRException {//
//
//        // Get currently running directory
//        String currentPath = System.getProperty("user.dir");
////		String currentPath = "/home/ismail/NetBeansProjects/sesp/SESPMIS/web/WEB-INF/resourcesNew/reporttemplates/";
////		String outputPath = "/home/ismail/NetBeansProjects/sesp/SESPMIS/web/WEB-INF/resourcesNew/reporttemplates/";
//        String outputPath = "/resource/";
//        ///home/ismail/NetBeansProjects/up/DijnetHUScrapMailPdf/SMTPmail/src/resource/DijnetHUReport1.jrxml
//        System.out.println(currentPath + outputPath);
//        JasperCompileManager.compileReportToFile(currentPath + outputPath + "DijnetHUReportEn.jrxml", currentPath + outputPath + "DijnetHUReportEn" + ".jasper");
//
////                List<String> fileNameList=new ArrayList<>();
////                fileNameList.add("Report-1");
////                fileNameList.add("Report-1-bn");
////                fileNameList.add("Report-4");
////                fileNameList.add("Report-4-bn");
////                fileNameList.add("Report-5a");
////                fileNameList.add("Report-5a-bn");
////                fileNameList.add("Report-5b");
////                fileNameList.add("Report-5b-bn");
////                fileNameList.add("Report-6");
////                fileNameList.add("Report-6-bn");
////                fileNameList.add("Report-7");
////                fileNameList.add("Report-7-bn");
////
////
////
////                for (String string : fileNameList) {
////
////                    System.out.println(">>"+string);
////                     JasperCompileManager.compileReportToFile(currentPath + string+".jrxml",outputPath+string+".jasper");//
////
////            }
//    }
//}
