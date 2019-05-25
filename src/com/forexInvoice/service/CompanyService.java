/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.model.Company;
import java.util.List;

/**
 *
 * @author User
 */
public abstract interface CompanyService {

    public void addCompany(Company b);

    public void updateCompany(Company b);

    public Company getCompany(Integer id);

    public void deleteCompany(Integer id);

    public List<Company> getCompanys();
}
