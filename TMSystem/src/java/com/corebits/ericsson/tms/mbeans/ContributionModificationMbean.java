package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.AuthenticationController;
import com.corebits.ericsson.tms.controllers.ModificationController;
import com.corebits.ericsson.tms.models.ContributionModification;
import com.corebits.ericsson.tms.models.StaffMember;
import com.corebits.ericsson.tms.models.User;
import com.corebits.ericsson.tms.utils.Utility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Tommy
 */
@ManagedBean(name = "modifycontri")
@ViewScoped
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
    private String contributionStatus;
    private List<ContributionModification> list;
    StaffMember member;
    private Double newCalculatedAmount;
    Date startDateControl;

    ContributionModification conmod;

    public ContributionModificationMbean() {
        startDateControl = new Date();
    }

    @PostConstruct
    private void init() {
        list = lmc.getContributionModifications(null);
    }

    public String modifyLoan() {
        System.out.println("........... Got here 1");
        FacesMessage message;
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (newCalculatedAmount != null && newCalculatedAmount < 20000) {
            System.out.println("Message : " + newCalculatedAmount);
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Modify Contribution", "Contribution amount cannot be less than N20,000.00");
            FacesContext.getCurrentInstance().addMessage("messages", message);
            return "pretty:modifycontribution";
        }
        System.out.println("........... Got here 2");
        try {
            String userId = (String) params.get("loginId");
            StaffMember user = ac.getUser(userId).getMemberId();
            if (user != null) {
                ContributionModification lm = new ContributionModification();
                lm.setApplicationDate(new Date());
                lm.setCommencementDate(commencementDate);
                lm.setIncreaseDecrease(action);
                lm.setMemberId(user);
                lm.setIncreaseDecreaseAmount(new BigDecimal(newAmount));
                lm.setTotalSavingsMonthly(new BigDecimal(newCalculatedAmount));
                lm.setApplicationStatus(Utility.APPLICATION_STATUS_PENDING);
                lmc.persist(lm);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modify Loan", "Completed successfully, queued for approval");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Modify Loan", "Unknown user identify, request could not be fulfilled");
                FacesContext.getCurrentInstance().addMessage("messages", message);
                return "pretty:modifycontribution";
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Modify Loan", "Internal server error when processing your request");
            FacesContext.getCurrentInstance().addMessage("messages", message);
            return "pretty:modifycontribution";
        }
        FacesContext.getCurrentInstance().addMessage("messages", message);
        return "pretty:dashboard";
    }

    public StaffMember getMemberDetails() {
        FacesMessage message;
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        StaffMember user;
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
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String userId = (String) params.get("loginId");
        StaffMember user = ac.getUser(userId).getMemberId();
        displayAction = "";
        if (action != null && !action.isEmpty()) {
            if ("INCREASE".equalsIgnoreCase(action)) {
                displayAction = "INCREASE";
                if (newAmount == null) {
                    newAmount = "0";
                }
                System.out.println("New Amount :" + newAmount);
                BigDecimal namt = new BigDecimal(newAmount);
                newCalculatedAmount = user.getAuthorityToDeductAmount().add(namt).doubleValue();
            } else {
                displayAction = "DECREASE";
                if (newAmount == null) {
                    newAmount = "0";
                }
                BigDecimal namt = BigDecimal.valueOf(Double.valueOf(newAmount));
                newCalculatedAmount = user.getAuthorityToDeductAmount().subtract(namt).doubleValue();
            }
            allowSubmit = true;
            return displayAction;
        }
        allowSubmit = false;
        return displayAction;
    }

    public String approveAction() {
        System.out.println("approving contribution adjustment...");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String requestId = params.get("approveAction");
        try {
//            System.out.println("approving contribution adjustment... selected requested id : " + requestId);
            ContributionModification cm = lmc.getContributionModification(Integer.valueOf(requestId));
            cm.setApplicationStatus(Utility.APPLICATION_STATUS_APPROVED);
            StaffMember md = getMemberDetails();
//            System.out.println("approving contribution adjustment...member name : " + md.getMemberName());
            cm.setApproveBy(md.getMemberName());
            cm.setApprovalDate(new Date());
            md.setAuthorityToDeductAmount(cm.getIncreaseDecreaseAmount());
            lmc.merge(cm);
            lmc.merge(md);
        } catch (Exception e) {
            System.out.println("error approving contribution adjustment..." + e.getMessage());
        }
        return "pretty:viewcontribution";
    }

    public String disApproveAction() {
        System.out.println("disapproving contribution adjustment...");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String requestId = params.get("approveAction");
        try {
            ContributionModification contributionModification = lmc.getContributionModification(Integer.valueOf(requestId));
            contributionModification.setApplicationStatus(Utility.APPLICATION_STATUS_REJECTED);
            StaffMember memberDetails = getMemberDetails();
            contributionModification.setApproveBy(memberDetails.getMemberName());
            lmc.merge(contributionModification);
        } catch (Exception e) {
            System.out.println("error disapproving contribution adjustment...");
        }
        return "pretty:viewcontribution";
    }

    public List<ContributionModification> allContributionModifications() {
        Short status;
        if (contributionStatus != null) {
            status = Short.parseShort(contributionStatus);
        } else {
            status = Short.valueOf("0");
        }
        List<ContributionModification> result = lmc.getContributionModifications(status);
        list = result;
        return result;
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

    public StaffMember getMember() {
        return member;
    }

    public void setMember(StaffMember member) {
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

    public String getContributionStatus() {
        return contributionStatus;
    }

    public void setContributionStatus(String contributionStatus) {
        this.contributionStatus = contributionStatus;
    }

    public List<ContributionModification> getList() {
        return list;
    }

    public void setList(List<ContributionModification> list) {
        this.list = list;
    }

    public ContributionModification getConmod() {
        return conmod;
    }

    public void setConmod(ContributionModification conmod) {
        this.conmod = conmod;
    }

    public Double getNewCalculatedAmount() {
        return newCalculatedAmount;
    }

    public void setNewCalculatedAmount(Double newCalculatedAmount) {
        this.newCalculatedAmount = newCalculatedAmount;
    }

    public Date getStartDateControl() {
        return startDateControl;
    }

    public void setStartDateControl(Date startDateControl) {
        this.startDateControl = startDateControl;
    }

}
