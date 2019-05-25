package com.istl.dao.imp;

import com.istl.dao.DivisionDAO;
import static com.istl.dao.imp.ParsonDAOImp.em;
import static com.istl.dao.imp.ParsonDAOImp.emf;
import com.istl.enroll_kit.model.EnrollDivision;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Persistence;





public class DivisionDAOImp implements DivisionDAO
{

  
    
    public ObservableList<EnrollDivision> findAllDivision() {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        List <EnrollDivision> division=em.createNamedQuery("EnrollDivision.findAll").getResultList();
        ObservableList<EnrollDivision> list = FXCollections.observableArrayList();
        for (EnrollDivision divisions : division) {
            list.add(divisions);
        }
        return list;
    }
    
  
   
}
