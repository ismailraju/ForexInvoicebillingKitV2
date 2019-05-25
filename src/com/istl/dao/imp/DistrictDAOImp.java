package com.istl.dao.imp;

import com.istl.dao.DistrictDAO;
import static com.istl.dao.imp.ParsonDAOImp.em;
import static com.istl.dao.imp.ParsonDAOImp.emf;
import com.istl.enroll_kit.model.EnrollDistrict;
import com.istl.enroll_kit.model.EnrollDivision;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Persistence;




public class DistrictDAOImp implements DistrictDAO{

   
    
    public ObservableList<EnrollDistrict> findAllDistrict() {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        List<EnrollDistrict> district = em.createNamedQuery("EnrollDistrict.findAll").getResultList();
        ObservableList<EnrollDistrict> list = FXCollections.observableArrayList();
        for (EnrollDistrict districts : district) {
            list.add(districts);
        }

        return list;
    }
    
   
    
    
    public ObservableList<EnrollDistrict> getAllDistrictByID(EnrollDivision value) {
        
        List<EnrollDistrict> district = em.createQuery("SELECT D FROM EnrollDistrict D WHERE D.divisionCode=:divisionCode")
                .setParameter("divisionCode",value)
                .getResultList();
        ObservableList<EnrollDistrict> list = FXCollections.observableArrayList();
        for (EnrollDistrict districts : district) {
            list.add(districts);
        }
        return list;
    }
    
    
  

    
   
}
