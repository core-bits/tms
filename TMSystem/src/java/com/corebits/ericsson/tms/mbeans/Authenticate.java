package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.AuthenticationController;
import com.corebits.ericsson.tms.controllers.RegistrationController;
import com.corebits.ericsson.tms.models.StaffMember;
import com.corebits.ericsson.tms.models.User;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Tommy
 */
@Named(value = "authenticate")
@RequestScoped
public class Authenticate implements Serializable {

    @Inject
    AuthenticationController ac;
    @Inject
    RegistrationController rc;

    private String loginId;
    private String userpassword;

    private String userFullName;
    private String userEmail;
    String userName;
    String password;
    User appUser;

    public Authenticate() {
    }

    public String createUser() {
        FacesMessage message;
        User u = new User();
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        StaffMember member;
        try {
            Integer memId = (Integer) params.get("memberId");
            member = rc.getMember(memId);
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "New User", "Internal error linking member Id to user");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "pretty:createuser";
        }

        try {
            String fullname = (String) params.get("memberName");
            String useremail = (String) params.get("email");
            u.setUserName(fullname);
            u.setDateCreated(new Date());
            u.setFirstLoginStatus(Boolean.TRUE);
            u.setUserStatus(Boolean.TRUE);
            u.setUserEmail(useremail);
            u.setUserLoginId(loginId);
            u.setUserLoginPassword(userpassword);
            u.setMemberId(member);
            ac.persist(u);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "New User", "Completed successfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "New User", "Completed successfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "pretty:createuser";
        }
        return "pretty:authenticate";
    }

    public String login() {
        System.out.println("login....");
        System.out.println("User :" + userName + ", Password :" + password);
        FacesMessage message;
        User login = ac.login(userName, password);
        if (login != null) {
            Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            params.put("loginId", userName);
            params.put("fullname", login.getMemberId().getMemberName());
            params.put("email", login.getMemberId().getEmail());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Authentication", "Completed successfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication", "Invalid login Id or Password");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "pretty:authenticate";
        }
        return "pretty:dashboard";
    }

    public String logout() {
        System.out.println("logging out....");
        FacesMessage message;
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String userId = (String) params.get("loginId");
        params.remove("loginId", userId);
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout", "Completed successfully");
        FacesContext.getCurrentInstance().addMessage("messages", message);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "pretty:logout";
    }

    public String userfullName() {
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String fullname = (String) params.get("fullname");
        userFullName = fullname;
        return userFullName;
    }

    public String userEmail() {
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String email = (String) params.get("email");
        userEmail = email;
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getAppUser() {
        return appUser;
    }

    public void setAppUser(User appUser) {
        this.appUser = appUser;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
