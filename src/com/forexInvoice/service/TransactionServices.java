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
public abstract interface TransactionServices {

    public Transaction addTransaction(Transaction b);

    public void updateTransaction(Transaction b);

    public Transaction getTransaction(Integer id);

    public void deleteTransaction(Integer id);

    public List<Transaction> getTransactions();
}
