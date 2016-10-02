package com.corebits.ericsson.tms.models;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContributionModification.class)
public abstract class ContributionModification_ {

	public static volatile SingularAttribute<ContributionModification, Date> commencementDate;
	public static volatile SingularAttribute<ContributionModification, String> increaseDecrease;
	public static volatile SingularAttribute<ContributionModification, Short> applicationStatus;
	public static volatile SingularAttribute<ContributionModification, Date> approvalDate;
	public static volatile SingularAttribute<ContributionModification, String> approveBy;
	public static volatile SingularAttribute<ContributionModification, BigDecimal> totalSavingsMonthly;
	public static volatile SingularAttribute<ContributionModification, Integer> id;
	public static volatile SingularAttribute<ContributionModification, BigDecimal> increaseDecreaseAmount;
	public static volatile SingularAttribute<ContributionModification, Date> applicationDate;
	public static volatile SingularAttribute<ContributionModification, StaffMember> memberId;

}

