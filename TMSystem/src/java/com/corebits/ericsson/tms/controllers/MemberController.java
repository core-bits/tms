/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.controllers;

import com.corebits.ericsson.tms.models.Member1;
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
public class MemberController {

    @PersistenceContext(unitName = "TMSystemPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(MemberController.class.getName());

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Member1> getMembers() {
        String name = "Member1.findAll";
        List<Member1> members = new ArrayList<>();
        try {
            members = em.createNamedQuery(name).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getMembers", e);
        }
        return members;
    }

}
