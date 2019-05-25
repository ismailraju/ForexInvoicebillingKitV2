/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.dao;

import com.forexInvoice.model.Currency;
import com.forexInvoice.service.*;
import com.forexInvoice.model.Currency;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class CurrencyDaoImp implements CurrencyDao {

    public EntityManagerFactory emf;
    public EntityManager em;

    public CurrencyDaoImp() {

        emf = Persistence.createEntityManagerFactory("Enroll_kit");

        em = emf.createEntityManager();

    }

    @Override
    public void addCurrency(Currency b) {
        /* Persist entity */
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();
    }

    @Override
    public void updateCurrency(Currency b) {

        em.getTransaction().begin();
        Currency currencyToUpdate = em.find(Currency.class, b.getId());

        currencyToUpdate.setId(b.getId());
        currencyToUpdate.setFullName(b.getFullName());
        currencyToUpdate.setShortName(b.getShortName());
        currencyToUpdate.setSign(b.getSign());
        currencyToUpdate.setCountry(b.getCountry());

        System.out.println("Currency after updation :- " + b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();

    }

    @Override
    public Currency getCurrency(Integer id) {

        return em.find(Currency.class, id);

    }

    @Override
    public void deleteCurrency(Integer id) {
        Currency b = getCurrency(id);
        if (b != null) {

            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
//            em.flush();
//            em.clear();
        }

    }

    @Override
    public List<Currency> getCurrencys() {

        return em.createQuery("SELECT b from Currency b").getResultList();
    }
}
