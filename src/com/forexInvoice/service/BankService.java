/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.model.Bank;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public abstract interface BankService {

    public void addBank(Bank b);

    public void updateBank(Bank b);

    public Bank getBank(Integer id);

    public void deleteBank(Integer id);

    public List<Bank> getBanks();

    public ObservableList<Bank> getBanksO();
}
