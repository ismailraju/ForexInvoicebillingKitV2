/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.dao.CustomerDaoImp;
import com.forexInvoice.model.Customer;
import java.util.List;

/**
 *
 * @author User
 */
public class CustomerServiceImp implements CustomerService {

    CustomerDaoImp customerDaoImp = new CustomerDaoImp();

    @Override
    public Customer getCustomer(Integer id) {
        return customerDaoImp.getCustomer(id);

    }

    @Override
    public Customer addCustomer(Customer b) {
        return customerDaoImp.addCustomer(b);
    }

    @Override
    public void updateCustomer(Customer b) {
        customerDaoImp.updateCustomer(b);
    }

    @Override
    public void deleteCustomer(Integer id) {

        customerDaoImp.deleteCustomer(id);
    }

    @Override
    public List<Customer> getCustomers() {

        return customerDaoImp.getCustomers();
    }

}
