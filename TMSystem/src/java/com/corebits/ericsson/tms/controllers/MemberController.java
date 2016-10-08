package com.corebits.ericsson.tms.controllers;

import com.corebits.ericsson.tms.models.StaffMember;
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
    
    public void merge(Object object) {
        em.merge(object);
    }

    public List<StaffMember> getMembers() {
        String name = "StaffMember.findAll";
        List<StaffMember> members = new ArrayList<>();
        try {
            members = em.createNamedQuery(name).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getMembers", e);
        }
        return members;
    }

    public StaffMember getSingleStaffMember(String staffId) {
        String name = "StaffMember.findByMemberId";
        StaffMember staff = null;
        try {
            staff = (StaffMember) em.createNamedQuery(name).setParameter("memberId", staffId).getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "exception in getMembers", e);
        }
        return staff;
    }

}
