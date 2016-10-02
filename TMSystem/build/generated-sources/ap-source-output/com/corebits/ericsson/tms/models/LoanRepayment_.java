package com.corebits.ericsson.tms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LoanRepayment.class)
public abstract class LoanRepayment_ {

	public static volatile SingularAttribute<LoanRepayment, Double> repaymentAmount;
	public static volatile SingularAttribute<LoanRepayment, Date> dateOfPayment;
	public static volatile SingularAttribute<LoanRepayment, Date> repaymentPeriod;
	public static volatile SingularAttribute<LoanRepayment, String> description;
	public static volatile SingularAttribute<LoanRepayment, String> repaymentStatus;
	public static volatile SingularAttribute<LoanRepayment, Integer> id;
	public static volatile SingularAttribute<LoanRepayment, String> transactionId;
	public static volatile SingularAttribute<LoanRepayment, LoanApplication> loanId;
	public static volatile SingularAttribute<LoanRepayment, String> memberId;
	public static volatile SingularAttribute<LoanRepayment, Double> amountReceived;

}

