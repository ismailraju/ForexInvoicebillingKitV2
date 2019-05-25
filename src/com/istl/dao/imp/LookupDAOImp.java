/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.dao.imp;

import com.istl.dao.LookupDAO;
import com.istl.enroll_kit.model.EnrollLookup;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class LookupDAOImp implements LookupDAO{
    static EntityManagerFactory emf;
    static EntityManager em;

    @Override
    public EnrollLookup getLookup(Short id) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        List<EnrollLookup> lookUp=em.createQuery("SELECT D FROM EnrollLookup D WHERE D.id=:id")
                .setParameter("id", id)
                .getResultList();
        EnrollLookup enrollLookup=null;
        for(EnrollLookup lookup:lookUp){
            enrollLookup=lookup;
        }
        return enrollLookup;
    }
    
}
