/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Arun
 */
public class ImageUtil {
    
    public byte[] getBufferedImageToByte(BufferedImage bufferedImage) throws IOException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        byte[] byteImage = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        
        return byteImage;
    }
    
    public BufferedImage getImageToBufferedImage(Image image){
        return SwingFXUtils.fromFXImage(image, null);
    }
    
}
