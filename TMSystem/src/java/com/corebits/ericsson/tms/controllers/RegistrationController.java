/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tommy
 */
@Stateless
public class RegistrationController {

    @PersistenceContext(unitName = "TMSystemPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
}
