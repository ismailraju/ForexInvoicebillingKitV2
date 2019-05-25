/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.model.Country;
import java.util.List;

/**
 *
 * @author User
 */
public abstract interface CountryService {
    
    public void addCountry(Country b);

    public void updateCountry(Country b);

    public Country getCountry(Integer id);

    public void deleteCountry(Integer id);

    public List<Country> getCountrys();
}
