/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.fingerprint.GBNFIQ2_JAVA_Functions;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.WString;
import java.nio.ByteBuffer;

/**
 *
 * @author andrea
 */
public class GBNFIQ2_DllWRapper {

    public interface GBNfiq2_Library extends Library {

        GBNfiq2_Library INSTANCE
                = (GBNfiq2_Library) Native.loadLibrary("GBNFIQ2", GBNfiq2_Library.class);
        
        /**
         * 
         * @return 
         */
        int GBNFIQ2_Load();

        /**
         * 
         * @param ImagePtr
         * @param ImageWidth
         * @param ImageHeight
         * @param ImageQuality
         * @return 
         */
        int GBNFIQ2_GetImageQuality(
                byte[] ImagePtr,
                int ImageWidth,
                int ImageHeight,
                IntByReference ImageQuality);
        
        /**
         * 
         * @return 
         */
        int GBNFIQ2_Unload();
    }
}
