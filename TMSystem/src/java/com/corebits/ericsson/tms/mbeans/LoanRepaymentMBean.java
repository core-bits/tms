
package com.corebits.ericsson.tms.mbeans;

import com.corebits.ericsson.tms.controllers.LoanRepaymentController;
import com.corebits.ericsson.tms.models.LoanApplication;
import com.corebits.ericsson.tms.models.LoanRepayment;
import com.corebits.ericsson.tms.utils.LoanRepaymentStatusType;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author xtphere
 */
@ManagedBean(name="loanRepaymentMBean")
@ViewScoped
public class LoanRepaymentMBean extends AbstractMBean<LoanRepayment> implements Serializable{
    @EJB
    LoanRepaymentController ejbFacade;
    
    public LoanRepaymentMBean(){
        super(LoanRepayment.class);
    }
    
    @PostConstruct
    public void init(){
        super.setFacade(ejbFacade);
    }
    
    public List<LoanRepayment> getLoanRepaymentEntries(LoanApplication loanId, LoanRepaymentStatusType statusType){
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("loanId", loanId);
        params.put("repaymentStatus", statusType.toString());
        return ejbFacade.findWithNamedQuery(LoanRepaymentController.NAMED_QUERY_FIND_BY_LOAN_ID_AND_STATUS, params);
    }
    
}
