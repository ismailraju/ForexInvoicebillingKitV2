/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.dao;

import com.forexInvoice.service.*;
import com.forexInvoice.dao.CustomerDaoImp;
import com.forexInvoice.model.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class CustomerDaoImp implements CustomerDao {

    public EntityManagerFactory emf;
    public EntityManager em;

    public CustomerDaoImp() {

        emf = Persistence.createEntityManagerFactory("Enroll_kit");

        em = emf.createEntityManager();

    }

    @Override
    public Customer addCustomer(Customer b) {
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
    public void updateCustomer(Customer b) {

        em.getTransaction().begin();
        Customer customerToUpdate = em.find(Customer.class, b.getId());

        customerToUpdate.setId(b.getId());
        customerToUpdate.setFulName(b.getFulName());
        customerToUpdate.setDob(b.getDob());
        customerToUpdate.setAddress(b.getAddress());
        customerToUpdate.setIdNumber(b.getIdNumber());
        customerToUpdate.setIdExpiryDate(b.getIdExpiryDate());
        customerToUpdate.setIssuePlace(b.getIssuePlace());
        customerToUpdate.setPurpose(b.getPurpose());
        customerToUpdate.setOccupation(b.getOccupation());

        System.out.println("Customer after updation :- " + b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();

    }

    @Override
    public Customer getCustomer(Integer id) {

        return em.find(Customer.class, id);

    }

    @Override
    public void deleteCustomer(Integer id) {
        Customer b = getCustomer(id);
        if (b != null) {

            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
//            em.flush();
//            em.clear();
        }

    }

    @Override
    public List<Customer> getCustomers() {

        return em.createQuery("SELECT b from Customer b").getResultList();
    }
}
