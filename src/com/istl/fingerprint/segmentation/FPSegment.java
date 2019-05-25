/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.fingerprint.segmentation;

import java.io.FileOutputStream;

/**
 *
 * @author Arun
 */
public class FPSegment extends Rect 
{
   private byte[] segData;
   private int segDataSz;
   private int segErr;
   private int score;
   
   public FPSegment()
   {
       super(0,0,0,0);
       this.segData = null;
       this.segDataSz = 0;
       this.segErr = 0;
       this.score = -1;
   }
   public FPSegment(int x,int y, int w, int h, byte[] iData , int iSz)
   {
       super(x,y,w,h);
       this.segData = iData;
       this.segDataSz = iSz;
       this.segErr = 0;
       this.score = -1;
   }

    public byte[] getSegData() 
    {
        return segData;
    }

    public void setSegData(byte[] segData) 
    {
        this.segData = segData;
    }

    public int getSegDataSz() 
    {
        return segDataSz;
    }

    public void setSegDataSz(int segDataSz) 
    {
        this.segDataSz = segDataSz;
    }

    public int getScore() 
    {
        return score;
    }

    public void setScore(int score) 
    {
        this.score = score;
    }

    public int getSegErr() 
    {
        return segErr;
    }

    public void setSegErr(int segErr) 
    {
        this.segErr = segErr;
    }          
    
    public void saveRawSegment(String fname)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(fname);
            fos.write(this.segData);
            fos.flush();
            fos.close();
        }catch(Exception exc)
        {
        }
    }
}
