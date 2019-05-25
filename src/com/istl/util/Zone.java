/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.util;

/**
 *
 * @author User
 */
public class Zone {
    private String name;
    private Integer id;
    private String protocol;
    private String host;
    private String port;
    private String path;

    public Zone() {
    }

    public Zone(String name, Integer id, String protocol, String host, String port, String path) {
        this.name = name;
        this.id = id;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
    

    @Override
    public String toString() {
        return name;
    }
    
    
}
