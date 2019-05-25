package com.istl.dao.imp;


import com.istl.dao.UnionDAO;
import static com.istl.dao.imp.ParsonDAOImp.em;
import static com.istl.dao.imp.ParsonDAOImp.emf;
import com.istl.enroll_kit.model.EnrollUnion;
import com.istl.enroll_kit.model.EnrollUpazila;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Persistence;


public class UnionDAOImp implements UnionDAO{
    public ObservableList<EnrollUnion> getAllUnionByID(EnrollUpazila value) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        List<EnrollUnion> union = em.createQuery("SELECT D FROM EnrollUnion D WHERE D.upazilaCode =:upazilaCode")
                .setParameter("upazilaCode",value.getCode())
                .getResultList();
        ObservableList<EnrollUnion> list = FXCollections.observableArrayList();
        for (EnrollUnion unions : union) {
            list.add(unions);
        }
        
        return list;
    }   
    
}
