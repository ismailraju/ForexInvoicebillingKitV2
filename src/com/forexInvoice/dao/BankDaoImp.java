/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.dao;

import com.forexInvoice.service.*;
import com.forexInvoice.model.Bank;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
@Repository
public class BankDaoImp implements BankDao {

    public EntityManagerFactory emf;
    public EntityManager em;

    public BankDaoImp() {

        emf = Persistence.createEntityManagerFactory("Enroll_kit");

        em = emf.createEntityManager();

    }

    @Override
    public void addBank(Bank b) {
//        getCurrentSession().save(invoiceLine);
        /* Persist entity */
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();
    }

    @Override
    public void updateBank(Bank b) {

//        SiofokInvoiceLine invoiceLineToUpdate = getInvoiceLine(invoiceLine.getInvoiceLineId());
//        invoiceLineToUpdate.setInvoiceLineName(invoiceLine.getInvoiceLineName());
//        getCurrentSession().update(invoiceLineToUpdate);

        /* Update entity */
        em.getTransaction().begin();
        Bank bankToUpdate = em.find(Bank.class, b.getId());

//        landlordToUpdate.setIdproperty(landlord.getIdproperty());
        bankToUpdate.setBranchName(b.getBranchName());
        bankToUpdate.setIfscCode(b.getIfscCode());
        bankToUpdate.setName(b.getName());

        System.out.println("Bank after updation :- " + b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();

    }

    @Override
    public Bank getBank(Integer id) {
//        SiofokInvoiceLine invoiceLine = (SiofokInvoiceLine) getCurrentSession().get(SiofokInvoiceLine.class, id);
//        return invoiceLine;

        /* Retrieve entity */
        return em.find(Bank.class, id);

    }

    @Override
    public void deleteBank(Integer id) {
        Bank b = getBank(id);
        if (b != null) {
//            getCurrentSession().delete(invoiceLine);
            /* Remove entity */
            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
//            em.flush();
//            em.clear();
        }

    }

    @Override
    public List<Bank> getBanks() {

//        emf = Persistence.createEntityManagerFactory("Enroll_kit");
//        em = emf.createEntityManager();
//        return em.createQuery("SELECT e FROM Bank e").getResultList();
        return em.createQuery("SELECT b from Bank b").getResultList();
    }

    @Override
    public ObservableList<Bank> getBanksO() {

//        emf = Persistence.createEntityManagerFactory("Enroll_kit");
//        em = emf.createEntityManager();
//        return em.createQuery("SELECT e FROM Bank e").getResultList();
        List<Bank> banks = em.createQuery("SELECT b from Bank b").getResultList();
        ObservableList<Bank> banksO = FXCollections.observableArrayList();
        for (Bank b : banks) {
            banksO.add(b);
        }
        return banksO;
    }

}
