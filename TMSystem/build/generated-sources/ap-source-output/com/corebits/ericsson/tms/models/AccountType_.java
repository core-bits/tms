package com.corebits.ericsson.tms.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountType.class)
public abstract class AccountType_ {

	public static volatile ListAttribute<AccountType, Accounts> accountsList;
	public static volatile SingularAttribute<AccountType, String> accountTypeCode;
	public static volatile SingularAttribute<AccountType, String> name;
	public static volatile SingularAttribute<AccountType, String> description;
	public static volatile SingularAttribute<AccountType, Integer> id;

}

