/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.dao.TransactionDaoImp;
import com.forexInvoice.model.Transaction;
import java.util.List;

/**
 *
 * @author User
 */
public class TransactionServiceImp implements TransactionServices {

    TransactionDaoImp transactionDaoImp = new TransactionDaoImp();

    @Override
    public Transaction getTransaction(Integer id) {
        return transactionDaoImp.getTransaction(id);

    }

    @Override
    public Transaction addTransaction(Transaction b) {
        return transactionDaoImp.addTransaction(b);
    }

    @Override
    public void updateTransaction(Transaction b) {
        transactionDaoImp.updateTransaction(b);
    }

    @Override
    public void deleteTransaction(Integer id) {

        transactionDaoImp.deleteTransaction(id);
    }

    @Override
    public List<Transaction> getTransactions() {

        return transactionDaoImp.getTransactions();
    }
}
