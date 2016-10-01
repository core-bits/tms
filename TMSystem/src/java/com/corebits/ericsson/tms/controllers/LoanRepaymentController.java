
package com.corebits.ericsson.tms.controllers;

import com.corebits.ericsson.tms.models.LoanRepayment;
import com.corebits.ericsson.tms.utils.Utility;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xtphere
 */
@Stateless
public class LoanRepaymentController extends AbstractController<LoanRepayment>{

    @PersistenceContext(unitName=Utility.PERSISTENCE_CONTEXT_UNIT_NAME)
    EntityManager em;
    public static final String NAMED_QUERY_FIND_ALL = "LoanRepayment.findAll";
    public static final String NAMED_QUERY_FIND_BY_LOAN_ID_AND_STATUS = "LoanRepayment.findByLoanIdNStatus";

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public LoanRepaymentController() {
        super(LoanRepayment.class);
    }

}
