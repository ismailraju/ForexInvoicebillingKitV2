/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.service;

import com.forexInvoice.model.Recipient;
import java.util.List;

/**
 *
 * @author User
 */
public abstract interface RecipientService {

    public Recipient addRecipient(Recipient b);

    public void updateRecipient(Recipient b);

    public Recipient getRecipient(Integer id);

    public void deleteRecipient(Integer id);

    public List<Recipient> getRecipients();
}
