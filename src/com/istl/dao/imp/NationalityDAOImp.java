/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.dao.imp;

import com.istl.dao.NationalityDAO;
import static com.istl.dao.imp.ParsonDAOImp.em;
import static com.istl.dao.imp.ParsonDAOImp.emf;
import com.istl.enroll_kit.model.EnrollNationalityLookup;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Persistence;

/**
 *
 * @author arun
 */
public class NationalityDAOImp implements NationalityDAO{

   
    public ObservableList<EnrollNationalityLookup> findAllNationality() {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        List <EnrollNationalityLookup> nationality=em.createQuery("SELECT e FROM EnrollNationalityLookup e ORDER BY e.countryNationality ASC").getResultList();
        ObservableList<EnrollNationalityLookup> list= FXCollections.observableArrayList();
        for(EnrollNationalityLookup nationalitys : nationality) {
            list.add(nationalitys);
         //   System.out.println(" id: "+nationalitys.getId()+" name: "+nationalitys.getCountryNameEn());
        }
        return list;
    }       
            
    public EnrollNationalityLookup getEnrollNationalityLookup(short value){
        EnrollNationalityLookup na = em.find(EnrollNationalityLookup.class, value);
        return na;
    }
}
