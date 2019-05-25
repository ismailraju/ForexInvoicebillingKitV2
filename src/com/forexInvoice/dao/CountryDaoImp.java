/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.dao;

import com.forexInvoice.model.Country;
import com.forexInvoice.service.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class CountryDaoImp implements CountryDao {

    public EntityManagerFactory emf;
    public EntityManager em;

    public CountryDaoImp() {

        emf = Persistence.createEntityManagerFactory("Enroll_kit");

        em = emf.createEntityManager();

    }

    @Override
    public void addCountry(Country b) {
//        getCurrentSession().save(invoiceLine);
        /* Persist entity */
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();
    }

    @Override
    public void updateCountry(Country b) {

        em.getTransaction().begin();
        Country bankToUpdate = em.find(Country.class, b.getId());

        bankToUpdate.setName(b.getName());
        bankToUpdate.setId(b.getId());

        System.out.println("Country after updation :- " + b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();

    }

    @Override
    public Country getCountry(Integer id) {

        return em.find(Country.class, id);

    }

    @Override
    public void deleteCountry(Integer id) {
        Country b = getCountry(id);
        if (b != null) {

            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
//            em.flush();
//            em.clear();
        }

    }

    @Override
    public List<Country> getCountrys() {

        return em.createQuery("SELECT b from Country b").getResultList();
    }
}
