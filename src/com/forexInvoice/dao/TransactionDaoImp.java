/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.dao;

import com.forexInvoice.model.Transaction;
import com.forexInvoice.service.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class TransactionDaoImp implements TransactionDao {

    public EntityManagerFactory emf;
    public EntityManager em;

    public TransactionDaoImp() {

        emf = Persistence.createEntityManagerFactory("Enroll_kit");

        em = emf.createEntityManager();

    }

    @Override
    public Transaction addTransaction(Transaction b) {
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
    public void updateTransaction(Transaction b) {

        em.getTransaction().begin();
        Transaction transactionToUpdate = em.find(Transaction.class, b.getId());

        transactionToUpdate.setId(b.getId());
        transactionToUpdate.setTransactionId(b.getTransactionId());
        transactionToUpdate.setSendCurrency(b.getSendCurrency());
        transactionToUpdate.setAmountSend(b.getAmountSend());
        transactionToUpdate.setCommission(b.getCommission());
        transactionToUpdate.setTotal(b.getTotal());
        transactionToUpdate.setCountry(b.getCountry());
        transactionToUpdate.setReceiveCurrency(b.getReceiveCurrency());
        transactionToUpdate.setExchangeRate(b.getExchangeRate());
        transactionToUpdate.setAmountReceive(b.getAmountReceive());
        transactionToUpdate.setPaymentMethod(b.getPaymentMethod());
        transactionToUpdate.setCustomer(b.getCustomer());
        transactionToUpdate.setRecipient(b.getRecipient());

        System.out.println("Transaction after updation :- " + b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();

    }

    @Override
    public Transaction getTransaction(Integer id) {

        return em.find(Transaction.class, id);

    }

    @Override
    public void deleteTransaction(Integer id) {
        Transaction b = getTransaction(id);
        if (b != null) {

            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
//            em.flush();
//            em.clear();
        }

    }

    @Override
    public List<Transaction> getTransactions() {

        return em.createQuery("SELECT b from Transaction b").getResultList();
    }
}
