/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.controllers;

import com.corebits.ericsson.tms.models.AccountType;
import com.corebits.ericsson.tms.models.Accounts;
import com.corebits.ericsson.tms.utils.Utility;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tommy
 */
@Stateless
public class AccountsController {

    @PersistenceContext(unitName = Utility.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(AccountsController.class.getName());

    public void persist(Object object) {
        em.persist(object);
    }

    public void createAccountType(AccountType at) {
        em.persist(at);
    }

    public void createAccounts(Accounts a) {
        em.persist(a);
    }

    public boolean validateAccountType(String code) {
        String name = "AccountType.findByAccountTypeCode";
        try {
            AccountType at = (AccountType) em.createNamedQuery(name, AccountType.class).setParameter("accountTypeCode", code).getSingleResult();
            if (at != null) {
                return true;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in validateAccountType : {0}", e.getMessage());
        }
        return false;
    }
    
    public boolean validateAccounts(String accountNumber) {
        String name = "Accounts.findByAccountNumber";
        try {
            Accounts at = (Accounts) em.createNamedQuery(name, Accounts.class).setParameter("accountNumber", accountNumber).getSingleResult();
            if (at != null) {
                return true;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in validateAccounts : {0}", e.getMessage());
        }
        return false;
    }
    
    public Accounts getAccounts(String accountNumber) {
        String name = "Accounts.findByAccountNumber";
        try {
            Accounts at = (Accounts) em.createNamedQuery(name, Accounts.class).setParameter("accountNumber", accountNumber).getSingleResult();
            if (at != null) {
                return at;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getAccounts : {0}", e.getMessage());
        }
        return null;
    }
    
    public AccountType getAccountType(String code) {
        String name = "AccountType.findByAccountTypeCode";
        try {
            AccountType at = (AccountType) em.createNamedQuery(name, AccountType.class).setParameter("accountTypeCode", code).getSingleResult();
            if (at != null) {
                return at;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getAccountType : {0}", e.getMessage());
        }
        return null;
    }

    public List<AccountType> allAccountType() {
        String name = "AccountType.findAll";
        List<AccountType> accountTypes = new ArrayList<>();
        try {
            accountTypes = em.createNamedQuery(name, AccountType.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in allAccountType : {0}", e.getMessage());
        }
        return accountTypes;
    }
    
    public List<Accounts> allAccounts() {
        String name = "Accounts.findAll";
        List<Accounts> accounts = new ArrayList<>();
        try {
            accounts = em.createNamedQuery(name, Accounts.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in allAccounts : {0}", e.getMessage());
        }
        return accounts;
    }
}
