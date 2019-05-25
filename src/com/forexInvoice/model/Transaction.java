/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forexInvoice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "transaction")
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findById", query = "SELECT t FROM Transaction t WHERE t.id = :id"),
    @NamedQuery(name = "Transaction.findByTransactionId", query = "SELECT t FROM Transaction t WHERE t.transactionId = :transactionId"),
    @NamedQuery(name = "Transaction.findByAmountSend", query = "SELECT t FROM Transaction t WHERE t.amountSend = :amountSend"),
    @NamedQuery(name = "Transaction.findByCommission", query = "SELECT t FROM Transaction t WHERE t.commission = :commission"),
    @NamedQuery(name = "Transaction.findByTotal", query = "SELECT t FROM Transaction t WHERE t.total = :total"),
    @NamedQuery(name = "Transaction.findByExchangeRate", query = "SELECT t FROM Transaction t WHERE t.exchangeRate = :exchangeRate"),
    @NamedQuery(name = "Transaction.findByAmountReceive", query = "SELECT t FROM Transaction t WHERE t.amountReceive = :amountReceive"),
    @NamedQuery(name = "Transaction.findByPaymentMethod", query = "SELECT t FROM Transaction t WHERE t.paymentMethod = :paymentMethod")})
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "transaction_id")
    private String transactionId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "amount_send")
    private BigDecimal amountSend;
    @Basic(optional = false)
    @Column(name = "commission")
    private BigDecimal commission;
    @Basic(optional = false)
    @Column(name = "total")
    private BigDecimal total;
    @Basic(optional = false)
    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;
    @Basic(optional = false)
    @Column(name = "amount_receive")
    private BigDecimal amountReceive;
    @Basic(optional = false)
    @Column(name = "payment_method")
    private String paymentMethod;
    @JoinColumn(name = "country", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Country country;
    @JoinColumn(name = "receive_currency", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Currency receiveCurrency;
    @JoinColumn(name = "send_currency", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Currency sendCurrency;

    @JoinColumn(name = "customer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customer;

    @JoinColumn(name = "recipient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Recipient recipient;

    public Transaction() {
    }

    public Transaction(Integer id) {
        this.id = id;
    }

    public Transaction(Integer id, BigDecimal amountSend, BigDecimal commission, BigDecimal total, BigDecimal exchangeRate, BigDecimal amountReceive, String paymentMethod) {
        this.id = id;
        this.amountSend = amountSend;
        this.commission = commission;
        this.total = total;
        this.exchangeRate = exchangeRate;
        this.amountReceive = amountReceive;
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmountSend() {
        return amountSend;
    }

    public void setAmountSend(BigDecimal amountSend) {
        this.amountSend = amountSend;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getAmountReceive() {
        return amountReceive;
    }

    public void setAmountReceive(BigDecimal amountReceive) {
        this.amountReceive = amountReceive;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Currency getReceiveCurrency() {
        return receiveCurrency;
    }

    public void setReceiveCurrency(Currency receiveCurrency) {
        this.receiveCurrency = receiveCurrency;
    }

    public Currency getSendCurrency() {
        return sendCurrency;
    }

    public void setSendCurrency(Currency sendCurrency) {
        this.sendCurrency = sendCurrency;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.forexInvoice.model.Transaction[ id=" + id + " ]";
    }

}
