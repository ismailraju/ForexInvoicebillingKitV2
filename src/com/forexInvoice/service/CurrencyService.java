/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.model.Currency;
import java.util.List;

/**
 *
 * @author User
 */
public abstract interface  CurrencyService {
    public void addCurrency(Currency b);

    public void updateCurrency(Currency b);

    public Currency getCurrency(Integer id);

    public void deleteCurrency(Integer id);

    public List<Currency> getCurrencys(); 
}
