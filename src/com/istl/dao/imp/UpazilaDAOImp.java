package com.istl.dao.imp;

import com.istl.dao.UpazilaDAO;
import static com.istl.dao.imp.ParsonDAOImp.em;
import static com.istl.dao.imp.ParsonDAOImp.emf;
import com.istl.enroll_kit.model.EnrollDistrict;
import com.istl.enroll_kit.model.EnrollUpazila;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Persistence;


public class UpazilaDAOImp implements UpazilaDAO{
    public ObservableList<EnrollUpazila> getAllUpazilaByID(EnrollDistrict value) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        List<EnrollUpazila> upazila = em.createQuery("SELECT D FROM EnrollUpazila D WHERE D.districtCode=:districtCode")
                .setParameter("districtCode",value)
                .getResultList();
        ObservableList<EnrollUpazila> list = FXCollections.observableArrayList();
        for (EnrollUpazila upazilas : upazila) {
            list.add(upazilas);
        }
        
        return list;
    }       


}
