package com.corebits.ericsson.tms.models;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StaffMember.class)
public abstract class StaffMember_ {

	public static volatile SingularAttribute<StaffMember, Short> registrationStatus;
	public static volatile SingularAttribute<StaffMember, String> phoneOfNok;
	public static volatile SingularAttribute<StaffMember, String> mobileNumber;
	public static volatile SingularAttribute<StaffMember, String> addressOfNok;
	public static volatile SingularAttribute<StaffMember, String> memberName;
	public static volatile SingularAttribute<StaffMember, String> memberAddress;
	public static volatile SingularAttribute<StaffMember, String> referralName;
	public static volatile SingularAttribute<StaffMember, BuisnessUnit> buisnessUnit;
	public static volatile SingularAttribute<StaffMember, Date> presidentApprovalDate;
	public static volatile SingularAttribute<StaffMember, String> bank;
	public static volatile SingularAttribute<StaffMember, Date> secretaryApprovalDate;
	public static volatile ListAttribute<StaffMember, User> userList;
	public static volatile SingularAttribute<StaffMember, byte[]> presidentSignature;
	public static volatile SingularAttribute<StaffMember, Date> undertakingDate;
	public static volatile SingularAttribute<StaffMember, String> witnessName;
	public static volatile ListAttribute<StaffMember, LoanApplication> loanApplicationList;
	public static volatile SingularAttribute<StaffMember, Integer> id;
	public static volatile SingularAttribute<StaffMember, Department> department;
	public static volatile SingularAttribute<StaffMember, byte[]> memberPhoto;
	public static volatile SingularAttribute<StaffMember, String> email;
	public static volatile SingularAttribute<StaffMember, String> memberId;
	public static volatile SingularAttribute<StaffMember, String> witnessResidentialAddress;
	public static volatile SingularAttribute<StaffMember, String> nextOfKin;
	public static volatile SingularAttribute<StaffMember, byte[]> witnessSignature;
	public static volatile SingularAttribute<StaffMember, String> sex;
	public static volatile SingularAttribute<StaffMember, Date> authorityToDeductEffectiveDate;
	public static volatile SingularAttribute<StaffMember, String> accountNumber;
	public static volatile SingularAttribute<StaffMember, String> relationshipToNok;
	public static volatile SingularAttribute<StaffMember, String> undertakingName;
	public static volatile SingularAttribute<StaffMember, Date> witnessApprovalDate;
	public static volatile SingularAttribute<StaffMember, Short> presidentApprovalStatus;
	public static volatile ListAttribute<StaffMember, ContributionModification> contributionModificationList;
	public static volatile SingularAttribute<StaffMember, Short> secretaryApprovalStatus;
	public static volatile SingularAttribute<StaffMember, byte[]> secretarySignature;
	public static volatile SingularAttribute<StaffMember, byte[]> applicantSignature;
	public static volatile SingularAttribute<StaffMember, String> maritalStatus;
	public static volatile SingularAttribute<StaffMember, BigDecimal> authorityToDeductAmount;
	public static volatile SingularAttribute<StaffMember, Date> applicationDate;

}

