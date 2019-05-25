package com.istl.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.istl.controller.ExportDataController.zoneList;
import com.istl.controller.LoginController;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public final class Utils {

    public static boolean timerFlag = false;
   
    public static Zone zone = new Zone();
    Stage primaryStage = new Stage();

    public static BufferedImage convertToGray(BufferedImage image) throws IOException {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return newImage;
    }

    public static byte[] BufferedImagetoByteArray(BufferedImage image) {

        WritableRaster raster = image.getRaster();
        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();

        return buffer.getData();
    }

    public static BufferedImage ByteArraytoImage(byte[] data, int w, int h) {

        DataBuffer buffer = new DataBufferByte(data, data.length);
        WritableRaster raster = Raster.createInterleavedRaster(buffer, w, h, w, 1, new int[]{0}, null);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorModel cm = new ComponentColorModel(cs, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);

        return new BufferedImage(cm, raster, false, null);

    }

    public static boolean checkServerConnection() {
        boolean flag = false;

        try {

            String ipAddress = Utils.zone.getHost();
            InetAddress inet = InetAddress.getByName(ipAddress);
//            System.out.println("Sending Ping Request to " + ipAddress);
            if (inet.isReachable(5000)) {
//                System.out.println(ipAddress + " is reachable.");
                flag = true;

            } else {
//                System.out.println(ipAddress + " NOT reachable.");
                flag = false;
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
        return flag;
    }

    public static ObservableList<Zone> getIp() {
        String zoneJson = "";
        try {
            //        Utilitys.zoneList.add(new Zone("192.168.10.29", "192.168.10.101", "192.168.10.111","192.168.10.112"));
            zoneJson = new String(Files.readAllBytes(Paths.get("C:/AccessControl/serverinfo.txt")));
            ObjectMapper mapper = new ObjectMapper();
            zoneList = mapper.readValue(zoneJson, new TypeReference<List<Zone>>() {
            });
        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(WebMvcConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }

//        System.out.println("=======zoneList=======" + zoneList.size());
//        for (Zone zone : zoneList) {
//            System.out.println("=======zone name=======" + zone.getName() + "=======zone ip=======" + zone.getHost());
//        }
        return FXCollections.observableArrayList(zoneList);
    }

    public void stageType(String fxmlPageSource, String title) {
        try {
            Utils.timerFlag = true;
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPageSource));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle(title);
            stage.getIcons().add(new Image("/logo/logo.png"));
            stage.setScene(scene);
            stage.show();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public FXMLLoader loaderType(String fxmlPageSource) {
        Utils.timerFlag = true;
        FXMLLoader loader = new FXMLLoader();
        try {

            loader.setLocation(getClass().getResource(fxmlPageSource));
            AnchorPane pane = (AnchorPane) loader.load();
            BorderPane border = LoginController.getRoot();
            border.setCenter(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }

    public void anchorType(String fxmlPageSource) {
        try {
            Utils.timerFlag = true;
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource(fxmlPageSource));
            BorderPane border = LoginController.getRoot();
            border.setCenter(pane);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

   

}
