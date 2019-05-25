/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.imageprocessing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Arun
 */
public class Utils {
    
    
    
    
    
    public static BufferedImage ByteArraytoBufferedImage(byte[] data, int w, int h){
        DataBuffer buffer = new DataBufferByte(data, data.length);
        WritableRaster raster = Raster.createInterleavedRaster(buffer, w, h, w, 1, new int[]{0}, null);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorModel cm = new ComponentColorModel(cs, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        
        return new BufferedImage(cm, raster, false, null);
    }
    
    public static BufferedImage convertToGray(BufferedImage image) throws IOException{
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        
        return newImage;
    }
    
    public static byte[] BufferedImagetoByteArray(BufferedImage image){
        
        WritableRaster raster = image.getRaster();
        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();

        return buffer.getData();
    }
    
 
    public static Image ByteArraytoImage(byte[] data, int w, int h){
        DataBuffer buffer = new DataBufferByte(data, data.length);
        WritableRaster raster = Raster.createInterleavedRaster(buffer, w, h, w, 1, new int[]{0}, null);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorModel cm = new ComponentColorModel(cs, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        return SwingFXUtils.toFXImage(new BufferedImage(cm, raster, false, null), null);
    }
    
//    public static byte[] rawToBmp(byte[] rawBytes, int width, int height)
//		{
//			byte[] bmpBytes = null; 
//			if(rawBytes != null)
//			{
//				ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//				int[] nBits = {8};
//
//				ColorModel cm = new ComponentColorModel(cs,nBits, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
//
//				SampleModel sm = cm.createCompatibleSampleModel(width, height);
//				DataBufferByte db = new DataBufferByte(rawBytes, width * height);
//
//				WritableRaster raster = Raster.createWritableRaster(sm, db, null);
//
//				BufferedImage buffImage = new BufferedImage(cm,raster, false,null);
//
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				try {
//					ImageIO.write(buffImage,"bmp",baos);
//					baos.flush();
//
//					bmpBytes = baos.toByteArray();
//
//					baos.close();
//				} catch (IOException e) {
//				}
//
//			}
//			return bmpBytes;	
//
//		}
    
    
     public static byte[] rawToBmp(byte[] rawBytes, int width, int height) throws IOException
		{
                    System.out.println(">>>>"+rawBytes);
                    System.out.println(">>>>>"+width);
                    System.out.println(">>>>>>"+height);
			byte[] bmpBytes = null; 
			if(rawBytes != null)
			{
                            System.out.println(">>>>>>>>>>>"+rawBytes);
				ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
				int[] nBits = {8};

				ColorModel cm = new ComponentColorModel(cs,nBits, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);

				SampleModel sm = cm.createCompatibleSampleModel(width, height);
				DataBufferByte db = new DataBufferByte(rawBytes, width * height);

				WritableRaster raster = Raster.createWritableRaster(sm, db, null);

				BufferedImage buffImage = reSizeImage(new BufferedImage(cm,raster, false,null));
//                                BufferedImage buffImage = new BufferedImage(cm,raster, false,null);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try {
					ImageIO.write(buffImage,"bmp",baos);
					baos.flush();

					bmpBytes = baos.toByteArray();

					baos.close();
				} catch (IOException e) {
				}

			}
			return bmpBytes;	

		}
     
     
          public static byte[] rawToBmpIris(byte[] rawBytes, int width, int height) throws IOException
		{
                    System.out.println(">>>>"+rawBytes);
                    System.out.println(">>>>>"+width);
                    System.out.println(">>>>>>"+height);
			byte[] bmpBytes = null; 
			if(rawBytes != null)
			{
                            System.out.println(">>>>>>>>>>>"+rawBytes);
				ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
				int[] nBits = {8};

				ColorModel cm = new ComponentColorModel(cs,nBits, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);

				SampleModel sm = cm.createCompatibleSampleModel(width, height);
				DataBufferByte db = new DataBufferByte(rawBytes, width * height);

				WritableRaster raster = Raster.createWritableRaster(sm, db, null);

				BufferedImage buffImage = new BufferedImage(cm,raster, false,null);
                                
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try {
					ImageIO.write(buffImage,"bmp",baos);
					baos.flush();

					bmpBytes = baos.toByteArray();

					baos.close();
				} catch (IOException e) {
				}

			}
			return bmpBytes;	

		}
    
    
    public static BufferedImage  reSizeImage(BufferedImage image) throws IOException{
        BufferedImage tThumbImage = new BufferedImage( 320, 480, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D tGraphics2D = tThumbImage.createGraphics(); //create a graphics object to paint to
        tGraphics2D.setBackground( Color.WHITE );
        tGraphics2D.setPaint( Color.WHITE );
        tGraphics2D.fillRect( 0, 0, 320, 480 );
        tGraphics2D.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
        tGraphics2D.drawImage( image, 0, 0, 320, 480, null ); //draw the image scaled
      //  ImageIO.write(tThumbImage, "bmp", new File("output.bmp")); //write the image to a file
        return tThumbImage;
    }

    
}
