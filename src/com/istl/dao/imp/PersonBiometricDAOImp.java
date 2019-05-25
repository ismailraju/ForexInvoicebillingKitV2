package com.istl.dao.imp;


import com.istl.dao.PersonBiometricDAO;
import static com.istl.dao.imp.ParsonDAOImp.emf;
import com.istl.enroll_kit.model.EnrollPerson;
import com.istl.enroll_kit.model.EnrollPersonBiometric;
import static java.util.Collections.list;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PersonBiometricDAOImp implements PersonBiometricDAO{
    static EntityManagerFactory emf;
    static EntityManager em;

    public EnrollPersonBiometric getBiometricById(Integer value){
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        EnrollPersonBiometric biometric=new EnrollPersonBiometric();
        EnrollPerson person=new EnrollPerson();
        person.setId(value);
        List<EnrollPersonBiometric> list=em.createQuery("SELECT D FROM EnrollPersonBiometric D WHERE D.personId=:personId")
               .setParameter("personId",person).getResultList();
        System.out.println("com.istl.dao.imp.PersonBiometricDAOImp.getBiometricById() : "+list);
        for (EnrollPersonBiometric bPerson:list){
            biometric=bPerson;
        }
        return biometric;
    
    }
    
     
    
}
