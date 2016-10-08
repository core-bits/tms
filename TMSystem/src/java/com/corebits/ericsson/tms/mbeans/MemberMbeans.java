package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.MemberController;
import com.corebits.ericsson.tms.models.StaffMember;
import com.corebits.ericsson.tms.utils.EmailMessageBuilder;
import com.corebits.ericsson.tms.utils.SendEmail;
import com.corebits.ericsson.tms.utils.Utility;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Tommy
 */
@Named(value = "member")
@SessionScoped
public class MemberMbeans implements Serializable {

    List<StaffMember> members;
    private StaffMember member;

    @Inject
    MemberController mc;

    public MemberMbeans() {
    }

    public List<StaffMember> getMembersAll() {
        List<StaffMember> members1 = mc.getMembers();
        members = members1;
        return members1;
    }

    public void activateMemeber() {
        FacesMessage message;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest hsr = (HttpServletRequest) context.getExternalContext().getRequest();
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String get = requestParameterMap.get("approve");
//        System.out.println("Value "+get);
        try {
            StaffMember singleStaffMember = mc.getSingleStaffMember(get);            
            singleStaffMember.setRegistrationStatus(Utility.APPLICATION_STATUS_APPROVED);
            mc.merge(singleStaffMember);
//          Send email to member
            SendEmail sender = new SendEmail();
            EmailMessageBuilder messageBuilder = new EmailMessageBuilder();
            String approveMember = messageBuilder.approveMember(singleStaffMember.getMemberName(), getBaseURL(hsr));
            sender.SendMailTLS(singleStaffMember.getEmail(), approveMember);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Completed successfully");
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", e.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        members = mc.getMembers();
    }
    public static String getBaseURL(final HttpServletRequest request) throws MalformedURLException {
        return new URL(request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                request.getContextPath()).toString();
    }

    public void deActivateMemeber() {
        FacesMessage message;
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String get = requestParameterMap.get("disable");
        System.out.println("Value " + get);
        try {
            StaffMember singleStaffMember = mc.getSingleStaffMember(get);
            singleStaffMember.setRegistrationStatus(Utility.PROFILE_STATUS_DISABLED);
            mc.merge(singleStaffMember);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert", "Completed successfully");
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alert", e.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<StaffMember> getMembers() {
        return members;
    }

    public void setMembers(List<StaffMember> members) {
        this.members = members;
    }

    public StaffMember getMember() {
        return member;
    }

    public void setMember(StaffMember member) {
        this.member = member;
    }


}
