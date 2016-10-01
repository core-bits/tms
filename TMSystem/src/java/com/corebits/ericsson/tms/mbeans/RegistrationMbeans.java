
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.ConfigurationController;
import com.corebits.ericsson.tms.controllers.RegistrationController;
import com.corebits.ericsson.tms.models.BuisnessUnit;
import com.corebits.ericsson.tms.models.Department;
import com.corebits.ericsson.tms.models.StaffMember;
import com.corebits.ericsson.tms.utils.Utility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Tommy
 */
@ManagedBean(name = "register")
@ViewScoped
public class RegistrationMbeans implements Serializable {

    @Inject
    RegistrationController rc;
    @Inject
    ConfigurationController cc;

    StaffMember member;
    private UploadedFile file;
    private UploadedFile applicantSig;
    private Date startDateControl;
    String unitId;
    String departmentId;
    String bank;
    String memberId;
    String memberName;
    String memberAddress;
    String sex;
    String accountNumber;
    String mobileNumber;
    String email;
    String maritalStatus;
    String witnessName;
    String witnessResidentialAddress;
    byte[] witnessSignature;
    Date witnessApprovalDate;
    String nextOfKin;
    String relationshipToNok;
    String phoneOfNok;
    String addressOfNok;
    String referralName;
    String undertakingName;
    Double authorityToDeductAmount;
    Date authorityToDeductEffectiveDate;
    private byte[] applicantSignature;
    private byte[] applicantPhoto;
    Date applicationDate;
    byte[] secretarySignature;
    Date secretaryApprovalDate;
    Short secretaryApprovalStatus;
    byte[] presidentSignature;
    Date presidentApprovalDate;
    Short presidentApprovalStatus;

    public RegistrationMbeans() {
    }

    @PostConstruct
    private void init() {
        startDateControl = new Date();
    }

    public String createMember() {
        member = new StaffMember();
        System.out.println("Create member button clicked");
        FacesMessage message;
        try {
            System.out.println("Unit Id :" + unitId + ", Department :" + departmentId);
            BuisnessUnit u = cc.getBuisnessUnit(Integer.parseInt(unitId));
            Department d = cc.getDepartment(Integer.parseInt(departmentId));
            member.setBuisnessUnit(u);
            member.setDepartment(d);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            String format = String.format("Error retriving biz/department :%s", e.getMessage());
            System.out.println("message :" + format);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", format);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "pretty:newmember";
        }

//        try {
//            String validateMember = rc.validateMember(memberId);
//            if (Utility.OPERATION_STATUS.equalsIgnoreCase(validateMember)) {
//                System.out.println("Validation complete, user does exist");
//            } else {
//                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", validateMember);
//                FacesContext.getCurrentInstance().addMessage(null, message);
//                return "pretty:newmember";
//            }
//        } catch (Exception e) {
//            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Internal server error validating member Id");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//            return "pretty:newmember";
//        }

        try {
            member.setBank(bank);
            member.setMemberId(memberId);
            member.setAccountNumber(accountNumber);
            member.setAddressOfNok(addressOfNok);
            member.setApplicationDate(new Date());
            member.setAuthorityToDeductAmount(new BigDecimal(authorityToDeductAmount));
            member.setAuthorityToDeductEffectiveDate(authorityToDeductEffectiveDate);
            member.setEmail(email);
            member.setMaritalStatus(maritalStatus);
            member.setMemberAddress(memberAddress);
            member.setMemberName(memberName);
            member.setMobileNumber(mobileNumber);
            member.setNextOfKin(nextOfKin);
            member.setPhoneOfNok(phoneOfNok);
            member.setReferralName(referralName);
            member.setRelationshipToNok(relationshipToNok);
            member.setSex(sex);
            member.setUndertakingDate(new Date());
            member.setUndertakingName(undertakingName);
            member.setWitnessName(witnessName);
            member.setWitnessResidentialAddress(witnessResidentialAddress);
            member.setSecretaryApprovalStatus(Short.valueOf("0"));
            member.setSecretaryApprovalStatus(Short.valueOf("0"));
            member.setRegistrationStatus(Short.valueOf("0"));

            String response = rc.createMember(member);
            if (response != null && response.equalsIgnoreCase(Utility.OPERATION_STATUS)) {
                Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//                Map map = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
                params.put("memberId", member.getId());
                params.put("memberName", memberName);
                params.put("email", email);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Member", "Member successfully created");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "New Member", response);
            }
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "New Member", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "pretty:newmember";
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "pretty:newmembercont";
    }

    public String witnessSignature(FileUploadEvent event) {
        System.out.println("Continue create member, upload witness Signature");
        FacesMessage message;
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        try {
            Integer memId = (Integer) params.get("memberId");
            member = rc.getMember(memId);
        } catch (Exception e) {
            String format = String.format("Error retriving member Id from session :%s", e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Alert", format);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "pretty:newmembercont";
        }

        try {
            try {
                witnessSignature = event.getFile().getContents();
                member.setWitnessSignature(witnessSignature);
            } catch (Exception e) {
                String format = String.format("Error retriving witness signature :%s", e.getMessage());
                System.out.println("message :" + format);
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Alert", format);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "pretty:newmembercont";
            }
            System.out.println("file name :" + event.getFile().getFileName());
            String response = rc.updateMember(member);
            if (response != null && response.equalsIgnoreCase(Utility.OPERATION_STATUS)) {
//                Map map = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Member", "Member profile successfully updated");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "New Member", "Could not update member profile");
            }
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "New Member", e.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage("Alert", message);
        return "pretty:newmembercont";
    }

    public String applicantSignature(FileUploadEvent event) {
        System.out.println("Continue create member, upload applicant Signature");
        FacesMessage message;
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        try {
            Integer memId = (Integer) params.get("memberId");
            member = rc.getMember(memId);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            String format = String.format("Error retriving member Id from session :%s", e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Alert", format);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "pretty:newmembercont";
        }

        try {
            try {
                applicantSignature = event.getFile().getContents();
                member.setApplicantSignature(applicantSignature);
            } catch (Exception e) {
                String format = String.format("Error retriving applicant signature :%s", e.getMessage());
                System.out.println("message :" + format);
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Alert", format);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "pretty:newmembercont";
            }
            System.out.println("file name :" + event.getFile().getFileName());

            String response = rc.updateMember(member);
            if (response != null && response.equalsIgnoreCase(Utility.OPERATION_STATUS)) {
//                Map map = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Member", "Member profile successfully updated");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "New Member", "Could not update member profile");
            }
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "New Member", e.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "pretty:newmembercont";
    }
    
    public String applicantPhoto(FileUploadEvent event) {
        System.out.println("Continue create member, upload applicant Signature");
        FacesMessage message;
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        try {
            Integer memId = (Integer) params.get("memberId");
            member = rc.getMember(memId);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            String format = String.format("Error retriving member Id from session :%s", e.getMessage());
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Alert", format);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "pretty:newmembercont";
        }

        try {
            try {
                applicantPhoto = event.getFile().getContents();
                member.setMemberPhoto(applicantPhoto);
            } catch (Exception e) {
                String format = String.format("Error retriving applicant photo :%s", e.getMessage());
                System.out.println("message :" + format);
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Alert", format);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "pretty:newmembercont";
            }
            System.out.println("file name :" + event.getFile().getFileName());

            String response = rc.updateMember(member);
            if (response != null && response.equalsIgnoreCase(Utility.OPERATION_STATUS)) {
//                Map map = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Member", "Member profile successfully updated");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "New Member", "Could not update member profile");
            }
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "New Member", e.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "pretty:newmembercont";
    }
    
    public String homePage() {
        System.out.println("homePage button clicked");
        return "pretty:home";
    }

    public StaffMember getMember() {
        return member;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getApplicantSig() {
        return applicantSig;
    }

    public void setApplicantSig(UploadedFile applicantSig) {
        this.applicantSig = applicantSig;
    }

    public void setMember(StaffMember member) {
        this.member = member;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getWitnessName() {
        return witnessName;
    }

    public void setWitnessName(String witnessName) {
        this.witnessName = witnessName;
    }

    public String getWitnessResidentialAddress() {
        return witnessResidentialAddress;
    }

    public void setWitnessResidentialAddress(String witnessResidentialAddress) {
        this.witnessResidentialAddress = witnessResidentialAddress;
    }

    public byte[] getWitnessSignature() {
        return witnessSignature;
    }

    public void setWitnessSignature(byte[] witnessSignature) {
        this.witnessSignature = witnessSignature;
    }

    public Date getWitnessApprovalDate() {
        return witnessApprovalDate;
    }

    public void setWitnessApprovalDate(Date witnessApprovalDate) {
        this.witnessApprovalDate = witnessApprovalDate;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public String getRelationshipToNok() {
        return relationshipToNok;
    }

    public void setRelationshipToNok(String relationshipToNok) {
        this.relationshipToNok = relationshipToNok;
    }

    public String getPhoneOfNok() {
        return phoneOfNok;
    }

    public void setPhoneOfNok(String phoneOfNok) {
        this.phoneOfNok = phoneOfNok;
    }

    public String getAddressOfNok() {
        return addressOfNok;
    }

    public void setAddressOfNok(String addressOfNok) {
        this.addressOfNok = addressOfNok;
    }

    public String getReferralName() {
        return referralName;
    }

    public void setReferralName(String referralName) {
        this.referralName = referralName;
    }

    public String getUndertakingName() {
        return undertakingName;
    }

    public void setUndertakingName(String undertakingName) {
        this.undertakingName = undertakingName;
    }

    public Double getAuthorityToDeductAmount() {
        return authorityToDeductAmount;
    }

    public void setAuthorityToDeductAmount(Double authorityToDeductAmount) {
        this.authorityToDeductAmount = authorityToDeductAmount;
    }

    public Date getAuthorityToDeductEffectiveDate() {
        return authorityToDeductEffectiveDate;
    }

    public void setAuthorityToDeductEffectiveDate(Date authorityToDeductEffectiveDate) {
        this.authorityToDeductEffectiveDate = authorityToDeductEffectiveDate;
    }

    public byte[] getApplicantSignature() {
        return applicantSignature;
    }

    public void setApplicantSignature(byte[] applicantSignature) {
        this.applicantSignature = applicantSignature;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public byte[] getSecretarySignature() {
        return secretarySignature;
    }

    public void setSecretarySignature(byte[] secretarySignature) {
        this.secretarySignature = secretarySignature;
    }

    public Date getSecretaryApprovalDate() {
        return secretaryApprovalDate;
    }

    public void setSecretaryApprovalDate(Date secretaryApprovalDate) {
        this.secretaryApprovalDate = secretaryApprovalDate;
    }

    public Short getSecretaryApprovalStatus() {
        return secretaryApprovalStatus;
    }

    public void setSecretaryApprovalStatus(Short secretaryApprovalStatus) {
        this.secretaryApprovalStatus = secretaryApprovalStatus;
    }

    public byte[] getPresidentSignature() {
        return presidentSignature;
    }

    public void setPresidentSignature(byte[] presidentSignature) {
        this.presidentSignature = presidentSignature;
    }

    public Date getPresidentApprovalDate() {
        return presidentApprovalDate;
    }

    public void setPresidentApprovalDate(Date presidentApprovalDate) {
        this.presidentApprovalDate = presidentApprovalDate;
    }

    public Short getPresidentApprovalStatus() {
        return presidentApprovalStatus;
    }

    public void setPresidentApprovalStatus(Short presidentApprovalStatus) {
        this.presidentApprovalStatus = presidentApprovalStatus;
    }

    public Date getStartDateControl() {
        return startDateControl;
    }

    public void setStartDateControl(Date startDateControl) {
        this.startDateControl = startDateControl;
    }

    public byte[] getApplicantPhoto() {
        return applicantPhoto;
    }

    public void setApplicantPhoto(byte[] applicantPhoto) {
        this.applicantPhoto = applicantPhoto;
    }

}
