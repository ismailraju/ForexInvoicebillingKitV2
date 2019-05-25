/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.dao.BankDaoImp;
import com.forexInvoice.model.Bank;
import java.util.List;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
 
public class BankServiceImp implements BankService {

    BankDaoImp bankDaoImp = new BankDaoImp();

    @Override
    public Bank getBank(Integer id) {
        return bankDaoImp.getBank(id);

    }

    @Override
    public void addBank(Bank b) {
        bankDaoImp.addBank(b);
    }

    @Override
    public void updateBank(Bank b) {
        bankDaoImp.updateBank(b);
    }

    @Override
    public void deleteBank(Integer id) {

        bankDaoImp.deleteBank(id);
    }

    @Override
    public List<Bank> getBanks() {

        return bankDaoImp.getBanks();
    }

    @Override
    public ObservableList<Bank> getBanksO() {

        return bankDaoImp.getBanksO();
    }

}
