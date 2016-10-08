/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.AccountsController;
import com.corebits.ericsson.tms.models.AccountType;
import com.corebits.ericsson.tms.models.Accounts;
import java.io.Serializable;
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
@Named(value = "accounts")
@ViewScoped
public class AccountsMbeans implements Serializable {

    @Inject
    AccountsController ac;

    String acctTypeName;
    String acctTypeCode;
    String acctTypeDesc;    
    
    private String accountNumber;
    private String accountName;
    private String accountAmount;
    private String description;
    
    List<AccountType> accountTypes;
    List<Accounts> accounts;

    public AccountsMbeans() {
    }

    @PostConstruct
    private void init(){
        accountTypes = ac.allAccountType();   
        accounts = ac.allAccounts();        
    }
    
    
    public void createAccountType() {
        FacesMessage message;
        if (acctTypeCode != null) {
            boolean validateAccountType = ac.validateAccountType(acctTypeCode);
            if (validateAccountType) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", "Account type already exist, please create a new one");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
        }
        try {
            AccountType at = new AccountType();
            at.setAccountTypeCode(acctTypeCode);
            at.setDescription(acctTypeDesc);
            at.setName(acctTypeName);
            ac.createAccountType(at);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Completed Successfully");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", "Internal error occured when processing your request");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        accountTypes = ac.allAccountType();
    }
    
    public void createAccounts() {
        FacesMessage message;        
            Accounts acct = new Accounts();
        if (acctTypeCode != null) {
            AccountType getAccountType = ac.getAccountType(acctTypeCode);
            if (getAccountType == null) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", "Selected account type could not be validated,"
                        + " please select a different account type");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }else{
                acct.setAccountType(getAccountType);
            }
        }
        if (accountNumber != null) {
            boolean validateAccounts = ac.validateAccounts(accountNumber);
            if (validateAccounts) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", "Account already exist, please create a new one");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
        }
        try {
            acct.setAccountName(accountName);
            acct.setAccountNumber(accountNumber);
            acct.setDescription(description);
            acct.setAccountBalance(Double.valueOf(accountAmount));
            ac.createAccounts(acct);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Completed Successfully");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", "Internal error occured when processing your request");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        accounts = ac.allAccounts();
    }

    public String getAcctTypeName() {
        return acctTypeName;
    }

    public void setAcctTypeName(String acctTypeName) {
        this.acctTypeName = acctTypeName;
    }

    public String getAcctTypeCode() {
        return acctTypeCode;
    }

    public void setAcctTypeCode(String acctTypeCode) {
        this.acctTypeCode = acctTypeCode;
    }

    public String getAcctTypeDesc() {
        return acctTypeDesc;
    }

    public void setAcctTypeDesc(String acctTypeDesc) {
        this.acctTypeDesc = acctTypeDesc;
    }

    public List<AccountType> getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(List<AccountType> accountTypes) {
        this.accountTypes = accountTypes;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }

    public String getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(String accountAmount) {
        this.accountAmount = accountAmount;
    }

}
