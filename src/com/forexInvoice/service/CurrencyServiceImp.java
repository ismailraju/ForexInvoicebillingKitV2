/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.dao.CurrencyDaoImp;
import com.forexInvoice.model.Currency;
import java.util.List;

/**
 *
 * @author User
 */
public class CurrencyServiceImp implements CurrencyService{
    CurrencyDaoImp currencyDaoImp = new CurrencyDaoImp();

    @Override
    public Currency getCurrency(Integer id) {
        return currencyDaoImp.getCurrency(id);

    }

    @Override
    public void addCurrency(Currency b) {
        currencyDaoImp.addCurrency(b);
    }

    @Override
    public void updateCurrency(Currency b) {
        currencyDaoImp.updateCurrency(b);
    }

    @Override
    public void deleteCurrency(Integer id) {

        currencyDaoImp.deleteCurrency(id);
    }

    @Override
    public List<Currency> getCurrencys() {

        return currencyDaoImp.getCurrencys();
    }
  
}
