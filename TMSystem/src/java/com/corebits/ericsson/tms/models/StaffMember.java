
package com.corebits.ericsson.tms.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tommy
 */
@Entity
@Table(name = "member")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StaffMember.findAll", query = "SELECT m FROM StaffMember m ORDER BY m.id DESC"),
    @NamedQuery(name = "StaffMember.findById", query = "SELECT m FROM StaffMember m WHERE m.id = :id"),
    @NamedQuery(name = "StaffMember.findByMemberId", query = "SELECT m FROM StaffMember m WHERE m.memberId = :memberId"),
    @NamedQuery(name = "StaffMember.findByMemberName", query = "SELECT m FROM StaffMember m WHERE m.memberName = :memberName"),
    @NamedQuery(name = "StaffMember.findByMemberAddress", query = "SELECT m FROM StaffMember m WHERE m.memberAddress = :memberAddress"),
    @NamedQuery(name = "StaffMember.findBySex", query = "SELECT m FROM StaffMember m WHERE m.sex = :sex"),
    @NamedQuery(name = "StaffMember.findByBank", query = "SELECT m FROM StaffMember m WHERE m.bank = :bank"),
    @NamedQuery(name = "StaffMember.findByAccountNumber", query = "SELECT m FROM StaffMember m WHERE m.accountNumber = :accountNumber"),
    @NamedQuery(name = "StaffMember.findByMobileNumber", query = "SELECT m FROM StaffMember m WHERE m.mobileNumber = :mobileNumber"),
    @NamedQuery(name = "StaffMember.findByEmail", query = "SELECT m FROM StaffMember m WHERE m.email = :email"),
    @NamedQuery(name = "StaffMember.findByMaritalStatus", query = "SELECT m FROM StaffMember m WHERE m.maritalStatus = :maritalStatus"),
    @NamedQuery(name = "StaffMember.findByWitnessName", query = "SELECT m FROM StaffMember m WHERE m.witnessName = :witnessName"),
    @NamedQuery(name = "StaffMember.findByWitnessResidentialAddress", query = "SELECT m FROM StaffMember m WHERE m.witnessResidentialAddress = :witnessResidentialAddress"),
    @NamedQuery(name = "StaffMember.findByWitnessApprovalDate", query = "SELECT m FROM StaffMember m WHERE m.witnessApprovalDate = :witnessApprovalDate"),
    @NamedQuery(name = "StaffMember.findByNextOfKin", query = "SELECT m FROM StaffMember m WHERE m.nextOfKin = :nextOfKin"),
    @NamedQuery(name = "StaffMember.findByRelationshipToNok", query = "SELECT m FROM StaffMember m WHERE m.relationshipToNok = :relationshipToNok"),
    @NamedQuery(name = "StaffMember.findByPhoneOfNok", query = "SELECT m FROM StaffMember m WHERE m.phoneOfNok = :phoneOfNok"),
    @NamedQuery(name = "StaffMember.findByAddressOfNok", query = "SELECT m FROM StaffMember m WHERE m.addressOfNok = :addressOfNok"),
    @NamedQuery(name = "StaffMember.findByReferralName", query = "SELECT m FROM StaffMember m WHERE m.referralName = :referralName"),
    @NamedQuery(name = "StaffMember.findByUndertakingDate", query = "SELECT m FROM StaffMember m WHERE m.undertakingDate = :undertakingDate"),
    @NamedQuery(name = "StaffMember.findByUndertakingName", query = "SELECT m FROM StaffMember m WHERE m.undertakingName = :undertakingName"),
    @NamedQuery(name = "StaffMember.findByAuthorityToDeductAmount", query = "SELECT m FROM StaffMember m WHERE m.authorityToDeductAmount = :authorityToDeductAmount"),
    @NamedQuery(name = "StaffMember.findByAuthorityToDeductEffectiveDate", query = "SELECT m FROM StaffMember m WHERE m.authorityToDeductEffectiveDate = :authorityToDeductEffectiveDate"),
    @NamedQuery(name = "StaffMember.findByApplicationDate", query = "SELECT m FROM StaffMember m WHERE m.applicationDate = :applicationDate"),
    @NamedQuery(name = "StaffMember.findBySecretaryApprovalDate", query = "SELECT m FROM StaffMember m WHERE m.secretaryApprovalDate = :secretaryApprovalDate"),
    @NamedQuery(name = "StaffMember.findBySecretaryApprovalStatus", query = "SELECT m FROM StaffMember m WHERE m.secretaryApprovalStatus = :secretaryApprovalStatus"),
    @NamedQuery(name = "StaffMember.findByPresidentApprovalDate", query = "SELECT m FROM StaffMember m WHERE m.presidentApprovalDate = :presidentApprovalDate"),
    @NamedQuery(name = "StaffMember.findByPresidentApprovalStatus", query = "SELECT m FROM StaffMember m WHERE m.presidentApprovalStatus = :presidentApprovalStatus")})
public class StaffMember implements Serializable {

    @Lob
    @Column(name = "witness_signature")
    private byte[] witnessSignature;
    @Lob
    @Column(name = "applicant_signature")
    private byte[] applicantSignature;
    @Lob
    @Column(name = "secretary_signature")
    private byte[] secretarySignature;
    @Lob
    @Column(name = "president_signature")
    private byte[] presidentSignature;
    @Lob
    @Column(name = "member_photo")
    private byte[] memberPhoto;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "member_id")
    private String memberId;
    @Size(max = 100)
    @Column(name = "member_name")
    private String memberName;
    @Size(max = 200)
    @Column(name = "member_address")
    private String memberAddress;
    @Size(max = 7)
    @Column(name = "sex")
    private String sex;
    @Size(max = 50)
    @Column(name = "bank")
    private String bank;
    @Size(max = 20)
    @Column(name = "account_number")
    private String accountNumber;
    @Size(max = 20)
    @Column(name = "mobile_number")
    private String mobileNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 15)
    @Column(name = "marital_status")
    private String maritalStatus;
    @Size(max = 100)
    @Column(name = "witness_name")
    private String witnessName;
    @Size(max = 200)
    @Column(name = "witness_residential_address")
    private String witnessResidentialAddress;
    @Column(name = "witness_approval_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date witnessApprovalDate;
    @Size(max = 100)
    @Column(name = "next_of_kin")
    private String nextOfKin;
    @Size(max = 45)
    @Column(name = "relationship_to_nok")
    private String relationshipToNok;
    @Size(max = 20)
    @Column(name = "phone_of_nok")
    private String phoneOfNok;
    @Size(max = 200)
    @Column(name = "address_of_nok")
    private String addressOfNok;
    @Size(max = 100)
    @Column(name = "referral_name")
    private String referralName;
    @Column(name = "undertaking_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date undertakingDate;
    @Size(max = 100)
    @Column(name = "undertaking_name")
    private String undertakingName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "authority_to_deduct_amount")
    private BigDecimal authorityToDeductAmount;
    @Column(name = "authority_to_deduct_effective_date")
    @Temporal(TemporalType.DATE)
    private Date authorityToDeductEffectiveDate;
    @Column(name = "application_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationDate;
    @Column(name = "secretary_approval_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date secretaryApprovalDate;
    @Column(name = "secretary_approval_status")
    private Short secretaryApprovalStatus;
    @Column(name = "president_approval_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date presidentApprovalDate;
    @Column(name = "president_approval_status")    
    private Short presidentApprovalStatus;    
    
    @Column(name = "registration_status")
    private Short registrationStatus;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private List<LoanApplication> loanApplicationList;
    @JoinColumn(name = "buisness_unit", referencedColumnName = "id")
    @ManyToOne
    private BuisnessUnit buisnessUnit;
    @JoinColumn(name = "department", referencedColumnName = "id")
    @ManyToOne
    private Department department;
    @OneToMany(mappedBy = "memberId")
    private List<ContributionModification> contributionModificationList;
    @OneToMany(mappedBy = "memberId")
    private List<User> userList;

    public StaffMember() {
    }

    public StaffMember(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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

    public Date getUndertakingDate() {
        return undertakingDate;
    }

    public void setUndertakingDate(Date undertakingDate) {
        this.undertakingDate = undertakingDate;
    }

    public String getUndertakingName() {
        return undertakingName;
    }

    public void setUndertakingName(String undertakingName) {
        this.undertakingName = undertakingName;
    }

    public BigDecimal getAuthorityToDeductAmount() {
        return authorityToDeductAmount;
    }

    public void setAuthorityToDeductAmount(BigDecimal authorityToDeductAmount) {
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

    public BuisnessUnit getBuisnessUnit() {
        return buisnessUnit;
    }

    public void setBuisnessUnit(BuisnessUnit buisnessUnit) {
        this.buisnessUnit = buisnessUnit;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StaffMember)) {
            return false;
        }
        StaffMember other = (StaffMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corebits.ericsson.models.StaffMember[ id=" + id + " ]";
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<ContributionModification> getContributionModificationList() {
        return contributionModificationList;
    }

    public void setContributionModificationList(List<ContributionModification> contributionModificationList) {
        this.contributionModificationList = contributionModificationList;
    }    

    public List<LoanApplication> getLoanApplicationList() {
        return loanApplicationList;
    }

    public void setLoanApplicationList(List<LoanApplication> loanApplicationList) {
        this.loanApplicationList = loanApplicationList;
    }


    public byte[] getMemberPhoto() {
        return memberPhoto;
    }

    public void setMemberPhoto(byte[] memberPhoto) {
        this.memberPhoto = memberPhoto;
    }

    public Short getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(Short registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    
    
}
