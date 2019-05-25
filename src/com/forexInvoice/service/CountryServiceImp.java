/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.dao.CountryDaoImp;
import com.forexInvoice.dao.CountryDaoImp;
import com.forexInvoice.model.Country;
import com.forexInvoice.model.Country;
import java.util.List;

/**
 *
 * @author User
 */
public class CountryServiceImp implements CountryService{

    CountryDaoImp countryDaoImp = new CountryDaoImp();

    @Override
    public Country getCountry(Integer id) {
        return countryDaoImp.getCountry(id);

    }

    @Override
    public void addCountry(Country b) {
        countryDaoImp.addCountry(b);
    }

    @Override
    public void updateCountry(Country b) {
        countryDaoImp.updateCountry(b);
    }

    @Override
    public void deleteCountry(Integer id) {

        countryDaoImp.deleteCountry(id);
    }

    @Override
    public List<Country> getCountrys() {

        return countryDaoImp.getCountrys();
    }

}
