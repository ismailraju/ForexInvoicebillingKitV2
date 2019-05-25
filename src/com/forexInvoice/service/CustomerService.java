/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.model.Customer;
import java.util.List;

/**
 *
 * @author User
 */
public abstract interface CustomerService {
     
    public Customer addCustomer(Customer b);

    public void updateCustomer(Customer b);

    public Customer getCustomer(Integer id);

    public void deleteCustomer(Integer id);

    public List<Customer> getCustomers();   
}
