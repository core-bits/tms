
package com.corebits.ericsson.tms.controllers;

import com.corebits.ericsson.tms.models.AccountType;
import com.corebits.ericsson.tms.models.Accounts;
import com.corebits.ericsson.tms.models.Journal;
import com.corebits.ericsson.tms.utils.Utility;
import java.io.Serializable;
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
public class ReportsController implements Serializable{

    @PersistenceContext(unitName = Utility.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(ReportsController.class.getName());

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Journal> getAllTransactions(){
        String name = "Journal.findAll";
        List<Journal> journals = new ArrayList<>();
        try {
            journals = em.createNamedQuery(name).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getAllTransactions ", e);
        }
        return journals;
    }
    
    public List<Accounts> getAccountList(){
        String name = "Accounts.findAll";
        try {
            return em.createNamedQuery(name).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getAccountList ", e);
        }
        return new ArrayList<>();
    }
    
    public List<AccountType> getAccountTypeList(){
        String name = "AccountType.findAll";
        try {
            return em.createNamedQuery(name).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getAccountTypeList ", e);
        }
        return new ArrayList<>();
    }
    
    public List<Journal> getAccountEntriesByAccount(Accounts account){
        String name = "Journal.findByAccount";
        try {
            return em.createNamedQuery(name).setParameter("account", account).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getAccountEntries ", e);
        }
        return new ArrayList<>();
    }
    
    public List<Accounts> getAccountListByAccountType(AccountType acctType){
        String name = "Accounts.findByAccountListByAccountType";
        try {
            return em.createNamedQuery(name).setParameter("accountType", acctType).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getAccountList ", e);
        }
        return new ArrayList<>();
    }
    
}
