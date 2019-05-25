/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.dao;

import com.forexInvoice.service.*;
import com.forexInvoice.dao.RecipientDaoImp;
import com.forexInvoice.model.Recipient;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class RecipientDaoImp implements RecipientDao {

    public EntityManagerFactory emf;
    public EntityManager em;

    public RecipientDaoImp() {

        emf = Persistence.createEntityManagerFactory("Enroll_kit");

        em = emf.createEntityManager();

    }

    @Override
    public Recipient addRecipient(Recipient b) {
//        getCurrentSession().save(invoiceLine);
        /* Persist entity */
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();

        return b;
    }

    @Override
    public void updateRecipient(Recipient b) {

        em.getTransaction().begin();
        Recipient recipientToUpdate = em.find(Recipient.class, b.getId());

        recipientToUpdate.setId(b.getId());
        recipientToUpdate.setFulName(b.getFulName());
        recipientToUpdate.setDob(b.getDob());
        recipientToUpdate.setAddress(b.getAddress());
        recipientToUpdate.setIdNumber(b.getIdNumber());
        recipientToUpdate.setReceivedMethod(b.getReceivedMethod());
        recipientToUpdate.setBank(b.getBank());

        System.out.println("Recipient after updation :- " + b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();

    }

    @Override
    public Recipient getRecipient(Integer id) {

        return em.find(Recipient.class, id);

    }

    @Override
    public void deleteRecipient(Integer id) {
        Recipient b = getRecipient(id);
        if (b != null) {

            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
//            em.flush();
//            em.clear();
        }

    }

    @Override
    public List<Recipient> getRecipients() {

        return em.createQuery("SELECT b from Recipient b").getResultList();
    }
}
