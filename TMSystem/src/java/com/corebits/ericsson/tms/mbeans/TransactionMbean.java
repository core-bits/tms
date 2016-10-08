/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.AccountsController;
import com.corebits.ericsson.tms.controllers.TransactionController;
import com.corebits.ericsson.tms.models.Accounts;
import com.corebits.ericsson.tms.models.Journal;
import com.corebits.ericsson.tms.models.Operations;
import com.corebits.ericsson.tms.utils.SplitDate;
import com.corebits.ericsson.tms.utils.Utility;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Tommy
 */
@Named(value = "transaction")
@ViewScoped
public class TransactionMbean implements Serializable {

    @Inject
    TransactionController tc;

    @Inject
    AccountsController ac;

    private String note;
    private String amount;
    private String credebit;
    private String account;

    private String operation;

    List<Journal> journals;
    List<Operations> operations;

    public TransactionMbean() {
    }

    @PostConstruct
    private void init() {
        journals = tc.getAllJournals();
        operations = tc.getOperations();
    }

    public void postTransaction() {
        FacesMessage message;
        Date txnd = new Date();
        SplitDate dates = new SplitDate(txnd);
        Journal j = new Journal();
        j.setActualDate(txnd);
        j.setAmount(Double.valueOf(amount));
        j.setDate(dates.getTime());
        j.setMonth(dates.getMonth());
        j.setYear(dates.getYear());
        j.setJournalOperation(operation);
        j.setNote(note);
        Double curr_balance = 0.0;
        Accounts accounts;
        if (account != null) {
            accounts = ac.getAccounts(account);
            if (accounts != null) {
                j.setAccount(accounts);
                curr_balance = accounts.getAccountBalance();
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Posting Failed",
                        "Internal server error validating selected account for posting");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Posting Failed",
                        "Please select a valid account for posting");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
        }
        try {
            System.out.println("Action :" + credebit);
            if (credebit != null && Utility.OPERATION_CREDIT.equalsIgnoreCase(credebit)) {
                j.setCredit(Double.valueOf(amount));
                j.setDebit(Double.valueOf(0));
                curr_balance = curr_balance + Double.valueOf(amount);
            } else {
                j.setCredit(Double.valueOf(0));
                j.setDebit(Double.valueOf(amount));
                curr_balance = curr_balance - Double.valueOf(amount);
            }
            j.setBalance(curr_balance);
            tc.persist(j);
            accounts.setAccountBalance(curr_balance);
            tc.merge(accounts);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful Posting",
                    "Transaction successfully posted");
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Posting Failed",
                    "Internal server error when posting transaction");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        journals = tc.getAllJournals();
    }

    public List<Journal> allJournals() {
        journals = tc.getAllJournals();
        return journals;
    }

    public List<Operations> allOperations() {
        operations = tc.getOperations();
        return operations;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCredebit() {
        return credebit;
    }

    public void setCredebit(String credebit) {
        this.credebit = credebit;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }

    public List<Operations> getOperations() {
        return operations;
    }

    public void setOperations(List<Operations> operations) {
        this.operations = operations;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

}
