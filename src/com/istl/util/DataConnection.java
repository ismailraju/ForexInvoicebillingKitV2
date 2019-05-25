package com.istl.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DataConnection {
    
    static EntityManagerFactory emf;
    static EntityManager em;
    
    public static void getDataConnection(){
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
    }
    
}
