/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.controllers;

import com.corebits.ericsson.tms.models.ContributionModification;
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
public class ModificationController {

    @PersistenceContext(unitName = Utility.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(ModificationController.class.getName());

    public void persist(Object object) {
        em.persist(object);
    }
    
    public void merge(Object object) {
        em.merge(object);
    }
    
    public ContributionModification getContributionModification(Integer id){
//        String name = "ContributionModification.findById";
        ContributionModification contribution = null;
        try {
            contribution = em.find(ContributionModification.class, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception retriving adjusted contribution : ", e);
        }
        return contribution;
    }

    public List<ContributionModification> getContributionModifications(Short status) {
        String name = "ContributionModification.findByStatus";
        List<ContributionModification> list = new ArrayList<>();
        try {
            if (status != null) {
                list = em.createNamedQuery(name).setParameter("applicationStatus", status).getResultList();
            }else{
                status = 0;
                list = em.createNamedQuery(name).setParameter("applicationStatus", status).setMaxResults(1000).getResultList();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getContributionModifications ", e);
        }
        return list;
    }

}
