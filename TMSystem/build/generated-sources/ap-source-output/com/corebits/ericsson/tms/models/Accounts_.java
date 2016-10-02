package com.corebits.ericsson.tms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Accounts.class)
public abstract class Accounts_ {

	public static volatile SingularAttribute<Accounts, String> accountName;
	public static volatile ListAttribute<Accounts, Journal> journalList;
	public static volatile SingularAttribute<Accounts, AccountType> accountType;
	public static volatile SingularAttribute<Accounts, String> description;
	public static volatile SingularAttribute<Accounts, Integer> id;
	public static volatile SingularAttribute<Accounts, String> accountNumber;

}

