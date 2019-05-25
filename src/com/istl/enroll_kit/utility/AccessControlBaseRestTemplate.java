/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.utility;

import com.istl.util.Utils;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Raju
 */
public class AccessControlBaseRestTemplate {

    public static final boolean IS_PRODUCTION = true;//false means DEVELOPMENT, true means PRODUCTION ie. public or demo
    public static final boolean IS_SSL = false;
    private static String PROTOCOL = "http://";

//    public static String HOST = "192.168.10.29";//"localhost";http://192.168.11.101/  192.168.22.20
//    public static String HOST = "192.168.10.28";//"localhost";http://192.168.11.101/  192.168.22.20
    public static String HOST = "192.168.10.222";//"localhost";http://192.168.11.101/  192.168.22.20
    public static Integer PORT = 8084;
    public static String PATH = "/Enroll";

    static {
        if (IS_PRODUCTION) {
            PORT = 8080;
            PATH = "/Enroll";
//            HOST = "localhost";
        }
        if (IS_SSL) {//http://192.168.22.20:8080/enrollment
            PORT = 8443;
            PROTOCOL = "http://192.168.10.31:8080/enrollment";
        }
    }

    public static String getUrl() {
        //String url = PROTOCOL + HOST + ":" + PORT + PATH;
        String url = Utils.zone.getProtocol()+Utils.zone.getHost()+":"+Utils.zone.getPort()+Utils.zone.getPath();
        return url;
    }
    public static String getContext() {
        //String url = PROTOCOL + HOST + ":" + PORT + PATH;
        String url = Utils.zone.getProtocol()+Utils.zone.getHost()+":"+Utils.zone.getPort()+"/Enroll/";
        return url;
    }

    public static RestTemplate getRestTemplate() {
        RestTemplate rt = new RestTemplate();

        rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        //rt.getMessageConverters().add(new StringHttpMessageConverter());

        return rt;
    }
}
