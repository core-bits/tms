package com.corebits.ericsson.tms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LoanAllocationGuidelines.class)
public abstract class LoanAllocationGuidelines_ {

	public static volatile SingularAttribute<LoanAllocationGuidelines, String> loanName;
	public static volatile SingularAttribute<LoanAllocationGuidelines, Double> interestRate;
	public static volatile SingularAttribute<LoanAllocationGuidelines, Integer> maximumTenure;
	public static volatile SingularAttribute<LoanAllocationGuidelines, Integer> minimumTenure;
	public static volatile SingularAttribute<LoanAllocationGuidelines, LoanType> loanType;
	public static volatile SingularAttribute<LoanAllocationGuidelines, Double> maximumAmount;
	public static volatile SingularAttribute<LoanAllocationGuidelines, Double> minimumAmount;
	public static volatile SingularAttribute<LoanAllocationGuidelines, Integer> id;

}

