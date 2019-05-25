/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.dao.CompanyDaoImp;
import com.forexInvoice.model.Company;
import java.util.List;

/**
 *
 * @author User
 */
public class CompanyServiceImp implements CompanyService {

    CompanyDaoImp companyDaoImp = new CompanyDaoImp();

    @Override
    public Company getCompany(Integer id) {
        return companyDaoImp.getCompany(id);

    }

    @Override
    public void addCompany(Company b) {
        companyDaoImp.addCompany(b);
    }

    @Override
    public void updateCompany(Company b) {
        companyDaoImp.updateCompany(b);
    }

    @Override
    public void deleteCompany(Integer id) {

        companyDaoImp.deleteCompany(id);
    }

    @Override
    public List<Company> getCompanys() {

        return companyDaoImp.getCompanys();
    }

}
