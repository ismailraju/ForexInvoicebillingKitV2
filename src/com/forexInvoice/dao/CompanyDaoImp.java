/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.dao;

import com.forexInvoice.service.*;
import com.forexInvoice.dao.CompanyDaoImp;
import com.forexInvoice.model.Company;
import com.forexInvoice.model.Company;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class CompanyDaoImp implements CompanyDao {

    public EntityManagerFactory emf;
    public EntityManager em;

    public CompanyDaoImp() {

        emf = Persistence.createEntityManagerFactory("Enroll_kit");

        em = emf.createEntityManager();

    }

    @Override
    public void addCompany(Company b) {
//        getCurrentSession().save(invoiceLine);
        /* Persist entity */
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();
    }

    @Override
    public void updateCompany(Company b) {

        em.getTransaction().begin();
        Company companyToUpdate = em.find(Company.class, b.getId());

        companyToUpdate.setId(b.getId());
        companyToUpdate.setName(b.getName());
        companyToUpdate.setEmail(b.getEmail());
        companyToUpdate.setFax(b.getFax());
        companyToUpdate.setTelephone(b.getTelephone());
        companyToUpdate.setWebAddress(b.getWebAddress());
        companyToUpdate.setAddress(b.getAddress());

        System.out.println("Company after updation :- " + b);
        em.getTransaction().commit();
//        em.flush();
//        em.clear();

    }

    @Override
    public Company getCompany(Integer id) {
//        SiofokInvoiceLine invoiceLine = (SiofokInvoiceLine) getCurrentSession().get(SiofokInvoiceLine.class, id);
//        return invoiceLine;

        /* Retrieve entity */
        return em.find(Company.class, id);

    }

    @Override
    public void deleteCompany(Integer id) {
        Company b = getCompany(id);
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
    public List<Company> getCompanys() {

        return em.createQuery("SELECT b from Company b").getResultList();
    }
}
