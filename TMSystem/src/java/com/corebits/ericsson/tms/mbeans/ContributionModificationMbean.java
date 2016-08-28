/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.AuthenticationController;
import com.corebits.ericsson.tms.controllers.ModificationController;
import com.corebits.ericsson.tms.models.ContributionModification;
import com.corebits.ericsson.tms.models.Member1;
import com.corebits.ericsson.tms.models.User;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Tommy
 */
@Named(value = "modifycontri")
@SessionScoped
public class ContributionModificationMbean implements Serializable {

    @Inject
    AuthenticationController ac;
    @Inject
    ModificationController lmc;

    private String newAmount;
    private String action;
    private String displayAction;
    private Date commencementDate;
    private String totalMonthlySavings;
    private boolean allowSubmit;

    Member1 member;

    public ContributionModificationMbean() {
    }

    public void modifyLoan() {
        FacesMessage message;
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        try {
            String userId = (String) params.get("loginId");
            Member1 user = ac.getUser(userId).getMemberId();
            if (user != null) {
                ContributionModification lm = new ContributionModification();
                lm.setApplicationDate(new Date());
                lm.setCommencementDate(commencementDate);
                lm.setIncreaseDecrease(action);
                lm.setMemberId(user);
                lm.setIncreaseDecreaseAmount(new BigDecimal(newAmount));
                lm.setTotalSavingsMonthly(new BigDecimal(totalMonthlySavings));
                lmc.persist(lm);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modify Loan", "Completed successfully, queued for approval");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Modify Loan", "Unknown user identify, request could not be fulfil ");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Modify Loan", "Internal server error when processing your request");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public Member1 getMemberDetails() {
        FacesMessage message;
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Member1 user;
        try {
            String userId = (String) params.get("loginId");
//            System.out.println("LoginId in getMemberDetails :" + userId);
            User user1 = ac.getUser(userId);
//            System.out.println("Member Id :" + user1.getId());
            user = user1.getMemberId();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Internal server error when processing your request");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
        return user;
    }

    public String newAction() {
        displayAction = "";
        if (action != null && !action.isEmpty()) {
            if ("INCREASE".equalsIgnoreCase(action)) {
                displayAction = "INCREASE";
            } else {
                displayAction = "DECREASE";
            }
            allowSubmit = true;
            return displayAction;
        }
        allowSubmit = false;
        return displayAction;
    }

    public String getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(String newAmount) {
        this.newAmount = newAmount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCommencementDate() {
        return commencementDate;
    }

    public void setCommencementDate(Date commencementDate) {
        this.commencementDate = commencementDate;
    }

    public String getTotalMonthlySavings() {
        return totalMonthlySavings;
    }

    public void setTotalMonthlySavings(String totalMonthlySavings) {
        this.totalMonthlySavings = totalMonthlySavings;
    }

    public Member1 getMember() {
        return member;
    }

    public void setMember(Member1 member) {
        this.member = member;
    }

    public String getDisplayAction() {
        return displayAction;
    }

    public void setDisplayAction(String displayAction) {
        this.displayAction = displayAction;
    }

    public boolean isAllowSubmit() {
        return allowSubmit;
    }

    public void setAllowSubmit(boolean allowSubmit) {
        this.allowSubmit = allowSubmit;
    }

}
