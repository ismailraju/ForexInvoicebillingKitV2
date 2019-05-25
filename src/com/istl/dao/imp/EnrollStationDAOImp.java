/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.dao.imp;

import com.istl.dao.EnrollStationDAO;
import static com.istl.dao.imp.ParsonDAOImp.emf;
import com.istl.enroll_kit.model.EnrollDistrict;
import com.istl.enroll_kit.model.EnrollStation;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class EnrollStationDAOImp implements EnrollStationDAO{
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Enroll_kit");
    static EntityManager em = emf.createEntityManager();
   

    @Override
    public ObservableList<EnrollStation> getStationById(EnrollDistrict district) {
        
        
       // System.out.println("====getStationById==="+district.getId());
        List<EnrollStation> station = em.createQuery("SELECT d FROM EnrollStation d WHERE d.districtId=:districtId ORDER BY d.nameEn ASC")
                .setParameter("districtId",district)
                .getResultList();
        System.out.println("station : "+station.size());
        ObservableList<EnrollStation> list = FXCollections.observableArrayList();
        for (EnrollStation stations : station) {
            list.add(stations);
        }
        return list;
    }
    
}
