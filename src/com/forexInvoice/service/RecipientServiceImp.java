/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.dao.RecipientDaoImp;
import com.forexInvoice.model.Recipient;
import java.util.List;

/**
 *
 * @author User
 */
public class RecipientServiceImp implements RecipientService {

    RecipientDaoImp recipientDaoImp = new RecipientDaoImp();

    @Override
    public Recipient getRecipient(Integer id) {
        return recipientDaoImp.getRecipient(id);

    }

    @Override
    public Recipient addRecipient(Recipient b) {
        return recipientDaoImp.addRecipient(b);
    }

    @Override
    public void updateRecipient(Recipient b) {
        recipientDaoImp.updateRecipient(b);
    }

    @Override
    public void deleteRecipient(Integer id) {

        recipientDaoImp.deleteRecipient(id);
    }

    @Override
    public List<Recipient> getRecipients() {

        return recipientDaoImp.getRecipients();
    }

}
