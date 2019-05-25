/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.dao.imp;

import com.istl.dao.EnrollUtilDAO;
import static com.istl.dao.imp.IdmUserDAOImp.em;
import static com.istl.dao.imp.IdmUserDAOImp.emf;
import com.istl.enroll_kit.model.EnrollUtils;
import java.util.ArrayList;
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
public class EnrollUtilDAOImp implements EnrollUtilDAO {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Enroll_kit");
    static EntityManager em = emf.createEntityManager();

    @Override
    public boolean save(EnrollUtils util, int a) {

        if (util != null) {
            try {
                em.getTransaction().begin();
                if (a == 1) {
                    em.persist(util);
                    System.err.println("New item saved : "+util.getName());
                }
                if (a == 2) {
                    em.merge(util);
                    System.err.println("Old item updated : "+util.getName());
                }
                em.getTransaction().commit();
                String result = util.getId();
                if (result!=null && !result.equals("")) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public ObservableList<EnrollUtils> getUtilsList(String name) {

        List<EnrollUtils> list = new ArrayList<EnrollUtils>();
        list = em.createQuery("SELECT e FROM EnrollUtils e WHERE e.type = :type ORDER BY e.priority").setParameter("type", name).getResultList();
        
        return FXCollections.observableArrayList(list);
    }

    @Override
    public boolean removeItem(EnrollUtils utils) {

        em.getTransaction().begin();
        int value = em.createQuery("DELETE FROM EnrollUtils E WHERE E.id=:id")
                .setParameter("id", utils.getId()).executeUpdate();
        em.getTransaction().commit();
        em.clear();
        return value == 1;
    }

    @Override
    public boolean check(String a) {
        EnrollUtils utils = new EnrollUtils();
        List<EnrollUtils> list = new ArrayList<EnrollUtils>();
        list = em.createNamedQuery("EnrollUtils.findById").setParameter("id", a).getResultList();
        for (EnrollUtils enrollUtils : list) {
            utils = enrollUtils;
        }
        return utils.getId() != null;
    }

}
