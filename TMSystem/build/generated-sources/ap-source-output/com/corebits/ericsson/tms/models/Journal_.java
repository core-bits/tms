package com.corebits.ericsson.tms.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Journal.class)
public abstract class Journal_ {

	public static volatile SingularAttribute<Journal, String> date;
	public static volatile SingularAttribute<Journal, String> note;
	public static volatile SingularAttribute<Journal, Double> amount;
	public static volatile SingularAttribute<Journal, String> month;
	public static volatile SingularAttribute<Journal, String> year;
	public static volatile SingularAttribute<Journal, Date> actualDate;
	public static volatile SingularAttribute<Journal, Integer> id;
	public static volatile SingularAttribute<Journal, Double> credit;
	public static volatile SingularAttribute<Journal, Double> debit;
	public static volatile SingularAttribute<Journal, Accounts> account;

}

