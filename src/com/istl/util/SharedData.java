package com.istl.util;

import com.innovatrics.iface.Face;
import com.innovatrics.iface.VisualObject;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class SharedData
  {
    
    public volatile boolean processing = false;
    public volatile boolean capturing = false;

    private BufferedImage image = null;
    private Face[] faces = null;
    private VisualObject[] trackedObjects = null;

    public synchronized void updateModel(BufferedImage img)
      {
        this.image = (img == null) ? null : img;
      }

    public synchronized void updateModel(Face[] faces, VisualObject[] trackedObjects)
      {
        this.faces = faces;
        this.trackedObjects = trackedObjects;
      }

    public synchronized BufferedImage getImage()
      {
        return (image == null) ? null : imageCopy(image);
      }
 
    public synchronized Face[] getFaces()
      {
        return faces;
      }

    public synchronized VisualObject[] getTrackedObjects()
      {
        return trackedObjects;
      }

    public static BufferedImage imageCopy(BufferedImage bi)
      {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
      }
  }
