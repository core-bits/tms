/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.controllers;

import com.corebits.ericsson.tms.models.Journal;
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
public class TransactionController {

    @PersistenceContext(unitName = Utility.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;    
    
    private static final Logger LOGGER = Logger.getLogger(TransactionController.class.getName());

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Journal> getAllJournals(){
        String name = "Journal.findAll";
         List<Journal> journals = new ArrayList<>();
        try {
            journals = em.createNamedQuery(name).setMaxResults(10000).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getAllJournals :{0}", e.getMessage());
        }
        return journals;
    }
}
