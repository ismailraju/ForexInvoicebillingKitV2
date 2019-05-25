/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.fingerprint.library;

import com.sun.jna.Native;
import java.util.HashMap;

/**
 *
 * @author Arun
 */
public class LibraryLoader {
    
    private static HashMap libMap = new HashMap();
    
    private static Object loadLibrary(String libName,String className) throws ClassNotFoundException{
        try{
            return Native.loadLibrary(libName,Class.forName(className));
        }catch (UnsatisfiedLinkError e){
            return loadFromJar(libName,className);
        }
    }
    private static Object loadFromJar(String libName,String className){        
        return loadLib(libName, className);
    }
    
    private static Object loadLib(String libName, String className){
        Object myLib = null;
        
        String toLoad = libName;
        libName = libName + ".dll";
        try{
            myLib = Native.loadLibrary(toLoad, Class.forName(className));
        }catch (Exception e){
            e.printStackTrace();
        }
        return myLib;
    }
    
    public static Object getLibrary(String libName,String className) throws Exception{
        if(!libMap.containsKey(libName)){
            Object lib = loadLibrary(libName,className);
            if(lib!=null){
                libMap.put(libName, lib);
            }
        }
        return libMap.get(libName);
    }
}
