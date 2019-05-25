/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.nfiq;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

/**
 *
 * @author Arun
 */
public interface NFIQLibrary extends StdCallLibrary
{
    public static final String JNA_LIBRARY_NAME = "nfiq";
    public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(NFIQLibrary.JNA_LIBRARY_NAME);
    public static final NFIQLibrary INSTANCE = (NFIQLibrary)Native.loadLibrary(NFIQLibrary.JNA_LIBRARY_NAME, NFIQLibrary.class);
    public int computeFPQuality(byte[] imgData,int imgDataLen,byte fmt,int imgWidth,int imgHeight);
    public int comp_nfiq(IntByReference onfiq, FloatByReference oconf, byte[] idata,int iw,int ih,int id,int ippi,IntByReference optflag);
    
     
    
//    //int computeFPQuality(byte[] imgData,int imgDataLen,byte fmt,int imgWidth,int imgHeight);
//    int comp_nfiq(IntByReference onfiq, FloatByReference oconf, byte[] idata,int iw,int ih,int id,int ippi,IntByReference optflag);    

}
