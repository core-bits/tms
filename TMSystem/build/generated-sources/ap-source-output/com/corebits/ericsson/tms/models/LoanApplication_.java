package com.corebits.ericsson.tms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LoanApplication.class)
public abstract class LoanApplication_ {

	public static volatile SingularAttribute<LoanApplication, Integer> approvalStatus;
	public static volatile SingularAttribute<LoanApplication, String> loanTypeDesc;
	public static volatile SingularAttribute<LoanApplication, Date> loanStartDate;
	public static volatile SingularAttribute<LoanApplication, LoanType> loanType;
	public static volatile SingularAttribute<LoanApplication, Date> dateOfApproval;
	public static volatile SingularAttribute<LoanApplication, String> approvedBy;
	public static volatile SingularAttribute<LoanApplication, Double> loanAmount;
	public static volatile SingularAttribute<LoanApplication, Integer> numberOfPayment;
	public static volatile SingularAttribute<LoanApplication, Double> annualInterestRate;
	public static volatile SingularAttribute<LoanApplication, LoanAllocationGuidelines> loanSubType;
	public static volatile SingularAttribute<LoanApplication, Double> totalCostOfLoan;
	public static volatile SingularAttribute<LoanApplication, Integer> loanStatus;
	public static volatile SingularAttribute<LoanApplication, String> loanSubTypeDesc;
	public static volatile SingularAttribute<LoanApplication, Date> dateOfApplication;
	public static volatile SingularAttribute<LoanApplication, Integer> id;
	public static volatile SingularAttribute<LoanApplication, Double> monthlyPaymentAmount;
	public static volatile SingularAttribute<LoanApplication, Double> totalInterest;
	public static volatile SingularAttribute<LoanApplication, String> loanId;
	public static volatile SingularAttribute<LoanApplication, StaffMember> memberId;

}

