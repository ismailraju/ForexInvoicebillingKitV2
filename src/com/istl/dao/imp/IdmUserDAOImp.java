/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.dao.imp;

import com.istl.dao.IdmUserDAO;
import com.istl.enroll_kit.model.IdmUser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Arun
 */
public class IdmUserDAOImp implements IdmUserDAO{
    static EntityManagerFactory emf;
    static EntityManager em;

    @Override
    public boolean addUser(IdmUser user) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        if(user!=null){
            try {
                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();
                int result=user.getId();
                if(result>0){
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }
    
    @Override
    public IdmUser getDBPassword(String username) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        IdmUser iser= new IdmUser();
        List<IdmUser> user = em.createQuery("SELECT i FROM IdmUser i WHERE i.userName = :userName")
                .setParameter("userName", username)
                .getResultList();
        for(IdmUser iuser:user){
            iser=iuser;
        }
       return iser;
    }

    @Override
    public boolean updateUser(IdmUser user) {
        emf = Persistence.createEntityManagerFactory("Enroll_kit");
        em = emf.createEntityManager();
        if(user!=null){
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
    

    
    
}
